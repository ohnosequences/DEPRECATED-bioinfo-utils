
 * To change this template, choose Tools | Templates
 * and open the template in the editor.


```java
package com.ohnosequences.util.blast;

import com.ohnosequences.xml.model.BlastOutput;
import com.ohnosequences.xml.model.Iteration;
import com.ohnosequences.xml.model.uniprot.ProteinXML;
import com.ohnosequences.xml.model.ContigXML;
import com.ohnosequences.xml.model.Hit;
import com.ohnosequences.xml.model.Hsp;
import com.ohnosequences.xml.api.model.XMLElement;
import com.ohnosequences.xml.api.model.XMLElementException;
import com.ohnosequences.xml.api.util.XMLUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author <a href="mailto:ppareja@ohnosequences.com">Pablo Pareja-Tobes</a>
 */
public class BlastExporter {

    public static final int TRUNCATE_STRING_LENGTH = 30;

    public static void main(String[] args) throws FileNotFoundException, Exception {

        BlastExporter.exportBlastXMLtoIsotigsCoverage(new BufferedReader(new FileReader(new File("PPIN_Coverage.xml"))));

    }
    
    public static String prettyPrintBlast(String blastSt, int indentAmount) throws TransformerConfigurationException, TransformerException{
        return XMLUtil.prettyPrintXML(blastSt, indentAmount);
    }

    public static String exportBlastXMLtoIsotigsCoverage(BufferedReader blastOutput) throws Exception {


        StringBuilder stBuilder = new StringBuilder();
        stBuilder.append("<proteins>\n");

        String line = null;
        StringBuilder iterationStBuilder = new StringBuilder();

        HashMap<String, ArrayList<ContigXML>> proteinContigs = new HashMap<String, ArrayList<ContigXML>>();
        //Protein info map
        HashMap<String, ProteinXML> proteinInfoMap = new HashMap<String, ProteinXML>();

        do {
            line = blastOutput.readLine();
        } while (!line.trim().startsWith("<" + Iteration.TAG_NAME + ">"));

        while (line != null) {

            iterationStBuilder.append(line);
            line = blastOutput.readLine();
            while (line != null && !line.trim().startsWith("<" + Iteration.TAG_NAME + ">")
                    && !line.trim().startsWith("</BlastOutput_iterations>")) {
                iterationStBuilder.append(line);
                line = blastOutput.readLine();
            }

            if (!line.trim().startsWith("</BlastOutput_iterations>")) {

                XMLElement entryXMLElem = new XMLElement(iterationStBuilder.toString());

                iterationStBuilder.delete(0, iterationStBuilder.length());
                Iteration iteration = new Iteration(entryXMLElem.asJDomElement());
                parseIteration(iteration, proteinContigs, proteinInfoMap);
            }

        }

        blastOutput.close();

        parseAndExportProteins(proteinContigs, proteinInfoMap, stBuilder);

        stBuilder.append("</proteins>\n");

        return stBuilder.toString();



    }

    public static String exportBlastXMLtoIsotigsCoverage(BlastOutput blastOutput) throws XMLElementException {

        StringBuilder stBuilder = new StringBuilder();

        stBuilder.append("<proteins>\n");

        ArrayList<Iteration> iterations = blastOutput.getBlastOutputIterations();
        //Map with isotigs/contigs per protein
        HashMap<String, ArrayList<ContigXML>> proteinContigs = new HashMap<String, ArrayList<ContigXML>>();
        //Protein info map
        HashMap<String, ProteinXML> proteinInfoMap = new HashMap<String, ProteinXML>();

        for (Iteration iteration : iterations) {
            parseIteration(iteration, proteinContigs, proteinInfoMap);
        }

        parseAndExportProteins(proteinContigs, proteinInfoMap, stBuilder);

        stBuilder.append("</proteins>\n");


        return stBuilder.toString();
    }

    private static void parseAndExportProteins(HashMap<String, ArrayList<ContigXML>> proteinContigs,
            HashMap<String, ProteinXML> proteinInfoMap,
            StringBuilder stBuilder) {

        System.out.println("holaaa");

        for (String proteinKey : proteinInfoMap.keySet()) {
            //---calculating coverage and creating output xml----

            ProteinXML proteinXML = proteinInfoMap.get(proteinKey);

            ArrayList<ContigXML> contigs = proteinContigs.get(proteinKey);
            for (ContigXML contigXML : contigs) {
                proteinXML.addChild(contigXML);
            }

            proteinXML.setNumberOfIsotigs(contigs.size());

            int coveredPositions = 0;
            for (int i = 1; i <= proteinXML.getLength(); i++) {
                for (ContigXML contigXML : contigs) {
                    if (i >= contigXML.getBegin() && i <= contigXML.getEnd()) {
                        coveredPositions++;
                        break;
                    }
                }
            }

            proteinXML.setProteinCoverageAbsolute(coveredPositions);
            proteinXML.setProteinCoveragePercentage((coveredPositions * 100.0) / proteinXML.getLength());

            stBuilder.append((proteinXML.toString() + "\n"));

        }

    }

    private static void parseIteration(Iteration iteration,
            HashMap<String, ArrayList<ContigXML>> proteinContigs,
            HashMap<String, ProteinXML> proteinInfoMap) throws XMLElementException {

        //String contigNameSt = iteration.getUniprotIdFromQueryDef();
        String contigNameSt = iteration.getQueryDef();

        //---In the case where query def is too long it is truncated
        if(contigNameSt.length() > TRUNCATE_STRING_LENGTH){
            contigNameSt = contigNameSt.substring(0, TRUNCATE_STRING_LENGTH);
        }
        ContigXML contig = new ContigXML();
        contig.setId(contigNameSt);

        ArrayList<Hit> hits = iteration.getIterationHits();
        for (Hit hit : hits) {
            String proteinIdSt = hit.getHitDef().split("\\|")[1];

            ArrayList<ContigXML> contigsArray = proteinContigs.get(proteinIdSt);


            if (contigsArray == null) {
                //Creating contigs array
                contigsArray = new ArrayList<ContigXML>();
                proteinContigs.put(proteinIdSt, contigsArray);
                //Creating protein info
                ProteinXML proteinXML = new ProteinXML();
                proteinXML.setId(proteinIdSt);
                proteinXML.setLength(hit.getHitLen());
                proteinInfoMap.put(proteinIdSt, proteinXML);
            }

            ArrayList<Hsp> hsps = hit.getHitHsps();
            int hspMinHitFrom = 1000000000;
            int hspMaxHitTo = -1;

            //---Figuring out the isotig/contig positions
            for (Hsp hsp : hsps) {
                int hspFrom = hsp.getHitFrom();
                int hspTo = hsp.getHitTo();
//                            System.out.println("hsp = " + hsp);
//                            System.out.println("hsp.getHitFrame() = " + hsp.getHitFrame());
//                            if (hsp.getQueryFrame() < 0) {
//                                hspFrom = hsp.getHitTo();
//                                hspTo = hsp.getHitFrom();
//                            }

                if (hspFrom < hspMinHitFrom) {
                    hspMinHitFrom = hspFrom;
                }
                if (hspTo > hspMaxHitTo) {
                    hspMaxHitTo = hspTo;
                }

                //adding hsps to contig
                hsp.detach();
                contig.addHsp(hsp);
            }
            //-------------------

            contig.setBegin(hspMinHitFrom);
            contig.setEnd(hspMaxHitTo);
            if (contig.getBegin() > contig.getEnd()) {
                contig.setBegin(hspMaxHitTo);
                contig.setEnd(hspMinHitFrom);
            }

            contigsArray.add(contig);

        }

    }
}

```




[main/java/com/ohnosequences/BioinfoUtil.java]: ../../BioinfoUtil.java.md
[main/java/com/ohnosequences/util/BitOperations.java]: ../BitOperations.java.md
[main/java/com/ohnosequences/util/blast/BlastExporter.java]: BlastExporter.java.md
[main/java/com/ohnosequences/util/blast/BlastSubset.java]: BlastSubset.java.md
[main/java/com/ohnosequences/util/CodonUtil.java]: ../CodonUtil.java.md
[main/java/com/ohnosequences/util/Entry.java]: ../Entry.java.md
[main/java/com/ohnosequences/util/Executable.java]: ../Executable.java.md
[main/java/com/ohnosequences/util/ExecuteFromFile.java]: ../ExecuteFromFile.java.md
[main/java/com/ohnosequences/util/fasta/FastaSubSeq.java]: ../fasta/FastaSubSeq.java.md
[main/java/com/ohnosequences/util/fasta/FastaUtil.java]: ../fasta/FastaUtil.java.md
[main/java/com/ohnosequences/util/fasta/MultifastaSelector.java]: ../fasta/MultifastaSelector.java.md
[main/java/com/ohnosequences/util/fasta/SearchFastaHeaders.java]: ../fasta/SearchFastaHeaders.java.md
[main/java/com/ohnosequences/util/fasta/SearchFastaSequence.java]: ../fasta/SearchFastaSequence.java.md
[main/java/com/ohnosequences/util/file/FileUtil.java]: ../file/FileUtil.java.md
[main/java/com/ohnosequences/util/file/FnaFileFilter.java]: ../file/FnaFileFilter.java.md
[main/java/com/ohnosequences/util/file/GenomeFilesParser.java]: ../file/GenomeFilesParser.java.md
[main/java/com/ohnosequences/util/file/PttFileFilter.java]: ../file/PttFileFilter.java.md
[main/java/com/ohnosequences/util/file/RntFileFilter.java]: ../file/RntFileFilter.java.md
[main/java/com/ohnosequences/util/genbank/GBCommon.java]: ../genbank/GBCommon.java.md
[main/java/com/ohnosequences/util/gephi/GephiExporter.java]: ../gephi/GephiExporter.java.md
[main/java/com/ohnosequences/util/gephi/GexfToDotExporter.java]: ../gephi/GexfToDotExporter.java.md
[main/java/com/ohnosequences/util/go/GOExporter.java]: ../go/GOExporter.java.md
[main/java/com/ohnosequences/util/model/Feature.java]: ../model/Feature.java.md
[main/java/com/ohnosequences/util/model/Intergenic.java]: ../model/Intergenic.java.md
[main/java/com/ohnosequences/util/model/PalindromicityResult.java]: ../model/PalindromicityResult.java.md
[main/java/com/ohnosequences/util/ncbi/TaxonomyLoader.java]: ../ncbi/TaxonomyLoader.java.md
[main/java/com/ohnosequences/util/oric/OricDataRetriever.java]: ../oric/OricDataRetriever.java.md
[main/java/com/ohnosequences/util/Pair.java]: ../Pair.java.md
[main/java/com/ohnosequences/util/pal/PalindromicityAnalyzer.java]: ../pal/PalindromicityAnalyzer.java.md
[main/java/com/ohnosequences/util/security/MD5.java]: ../security/MD5.java.md
[main/java/com/ohnosequences/util/seq/SeqUtil.java]: ../seq/SeqUtil.java.md
[main/java/com/ohnosequences/util/statistics/StatisticalValues.java]: ../statistics/StatisticalValues.java.md
[main/java/com/ohnosequences/util/uniprot/UniprotProteinRetreiver.java]: ../uniprot/UniprotProteinRetreiver.java.md
[main/java/com/ohnosequences/xml/api/interfaces/IAttribute.java]: ../../xml/api/interfaces/IAttribute.java.md
[main/java/com/ohnosequences/xml/api/interfaces/IElement.java]: ../../xml/api/interfaces/IElement.java.md
[main/java/com/ohnosequences/xml/api/interfaces/INameSpace.java]: ../../xml/api/interfaces/INameSpace.java.md
[main/java/com/ohnosequences/xml/api/interfaces/IXmlThing.java]: ../../xml/api/interfaces/IXmlThing.java.md
[main/java/com/ohnosequences/xml/api/interfaces/package-info.java]: ../../xml/api/interfaces/package-info.java.md
[main/java/com/ohnosequences/xml/api/model/NameSpace.java]: ../../xml/api/model/NameSpace.java.md
[main/java/com/ohnosequences/xml/api/model/package-info.java]: ../../xml/api/model/package-info.java.md
[main/java/com/ohnosequences/xml/api/model/XMLAttribute.java]: ../../xml/api/model/XMLAttribute.java.md
[main/java/com/ohnosequences/xml/api/model/XMLElement.java]: ../../xml/api/model/XMLElement.java.md
[main/java/com/ohnosequences/xml/api/model/XMLElementException.java]: ../../xml/api/model/XMLElementException.java.md
[main/java/com/ohnosequences/xml/api/util/XMLUtil.java]: ../../xml/api/util/XMLUtil.java.md
[main/java/com/ohnosequences/xml/model/Annotation.java]: ../../xml/model/Annotation.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jNodeIndexXML.java]: ../../xml/model/bio4j/Bio4jNodeIndexXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jNodeXML.java]: ../../xml/model/bio4j/Bio4jNodeXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jPropertyXML.java]: ../../xml/model/bio4j/Bio4jPropertyXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jRelationshipIndexXML.java]: ../../xml/model/bio4j/Bio4jRelationshipIndexXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jRelationshipXML.java]: ../../xml/model/bio4j/Bio4jRelationshipXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/UniprotDataXML.java]: ../../xml/model/bio4j/UniprotDataXML.java.md
[main/java/com/ohnosequences/xml/model/BlastOutput.java]: ../../xml/model/BlastOutput.java.md
[main/java/com/ohnosequences/xml/model/BlastOutputParam.java]: ../../xml/model/BlastOutputParam.java.md
[main/java/com/ohnosequences/xml/model/Codon.java]: ../../xml/model/Codon.java.md
[main/java/com/ohnosequences/xml/model/ContigXML.java]: ../../xml/model/ContigXML.java.md
[main/java/com/ohnosequences/xml/model/cufflinks/CuffLinksElement.java]: ../../xml/model/cufflinks/CuffLinksElement.java.md
[main/java/com/ohnosequences/xml/model/embl/EmblXML.java]: ../../xml/model/embl/EmblXML.java.md
[main/java/com/ohnosequences/xml/model/Frameshift.java]: ../../xml/model/Frameshift.java.md
[main/java/com/ohnosequences/xml/model/Gap.java]: ../../xml/model/Gap.java.md
[main/java/com/ohnosequences/xml/model/gb/GenBankXML.java]: ../../xml/model/gb/GenBankXML.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/Feature.java]: ../../xml/model/genome/feature/Feature.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/Intergenic.java]: ../../xml/model/genome/feature/Intergenic.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/MisRNA.java]: ../../xml/model/genome/feature/MisRNA.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/ORF.java]: ../../xml/model/genome/feature/ORF.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/RNA.java]: ../../xml/model/genome/feature/RNA.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/RRNA.java]: ../../xml/model/genome/feature/RRNA.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/TRNA.java]: ../../xml/model/genome/feature/TRNA.java.md
[main/java/com/ohnosequences/xml/model/genome/GenomeElement.java]: ../../xml/model/genome/GenomeElement.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttributesXML.java]: ../../xml/model/gexf/AttributesXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttributeXML.java]: ../../xml/model/gexf/AttributeXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttValuesXML.java]: ../../xml/model/gexf/AttValuesXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttValueXML.java]: ../../xml/model/gexf/AttValueXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/EdgesXML.java]: ../../xml/model/gexf/EdgesXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/EdgeXML.java]: ../../xml/model/gexf/EdgeXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/GexfXML.java]: ../../xml/model/gexf/GexfXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/GraphXML.java]: ../../xml/model/gexf/GraphXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/NodesXML.java]: ../../xml/model/gexf/NodesXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/NodeXML.java]: ../../xml/model/gexf/NodeXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/SpellsXML.java]: ../../xml/model/gexf/SpellsXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/SpellXML.java]: ../../xml/model/gexf/SpellXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/viz/VizColorXML.java]: ../../xml/model/gexf/viz/VizColorXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/viz/VizPositionXML.java]: ../../xml/model/gexf/viz/VizPositionXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/viz/VizSizeXML.java]: ../../xml/model/gexf/viz/VizSizeXML.java.md
[main/java/com/ohnosequences/xml/model/go/GoAnnotationXML.java]: ../../xml/model/go/GoAnnotationXML.java.md
[main/java/com/ohnosequences/xml/model/go/GOSlimXML.java]: ../../xml/model/go/GOSlimXML.java.md
[main/java/com/ohnosequences/xml/model/go/GoTermXML.java]: ../../xml/model/go/GoTermXML.java.md
[main/java/com/ohnosequences/xml/model/go/SlimSetXML.java]: ../../xml/model/go/SlimSetXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/DataXML.java]: ../../xml/model/graphml/DataXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/EdgeXML.java]: ../../xml/model/graphml/EdgeXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/GraphmlXML.java]: ../../xml/model/graphml/GraphmlXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/GraphXML.java]: ../../xml/model/graphml/GraphXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/KeyXML.java]: ../../xml/model/graphml/KeyXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/NodeXML.java]: ../../xml/model/graphml/NodeXML.java.md
[main/java/com/ohnosequences/xml/model/Hit.java]: ../../xml/model/Hit.java.md
[main/java/com/ohnosequences/xml/model/Hsp.java]: ../../xml/model/Hsp.java.md
[main/java/com/ohnosequences/xml/model/HspSet.java]: ../../xml/model/HspSet.java.md
[main/java/com/ohnosequences/xml/model/Iteration.java]: ../../xml/model/Iteration.java.md
[main/java/com/ohnosequences/xml/model/logs/LogRecordXML.java]: ../../xml/model/logs/LogRecordXML.java.md
[main/java/com/ohnosequences/xml/model/metagenomics/ReadResultXML.java]: ../../xml/model/metagenomics/ReadResultXML.java.md
[main/java/com/ohnosequences/xml/model/metagenomics/ReadXML.java]: ../../xml/model/metagenomics/ReadXML.java.md
[main/java/com/ohnosequences/xml/model/metagenomics/SampleXML.java]: ../../xml/model/metagenomics/SampleXML.java.md
[main/java/com/ohnosequences/xml/model/MetagenomicsDataXML.java]: ../../xml/model/MetagenomicsDataXML.java.md
[main/java/com/ohnosequences/xml/model/mg7/MG7DataXML.java]: ../../xml/model/mg7/MG7DataXML.java.md
[main/java/com/ohnosequences/xml/model/mg7/ReadResultXML.java]: ../../xml/model/mg7/ReadResultXML.java.md
[main/java/com/ohnosequences/xml/model/mg7/SampleXML.java]: ../../xml/model/mg7/SampleXML.java.md
[main/java/com/ohnosequences/xml/model/ncbi/NCBITaxonomyNodeXML.java]: ../../xml/model/ncbi/NCBITaxonomyNodeXML.java.md
[main/java/com/ohnosequences/xml/model/oric/Oric.java]: ../../xml/model/oric/Oric.java.md
[main/java/com/ohnosequences/xml/model/Overlap.java]: ../../xml/model/Overlap.java.md
[main/java/com/ohnosequences/xml/model/pal/PalindromicityResultXML.java]: ../../xml/model/pal/PalindromicityResultXML.java.md
[main/java/com/ohnosequences/xml/model/pg/Primer.java]: ../../xml/model/pg/Primer.java.md
[main/java/com/ohnosequences/xml/model/PredictedGene.java]: ../../xml/model/PredictedGene.java.md
[main/java/com/ohnosequences/xml/model/PredictedGenes.java]: ../../xml/model/PredictedGenes.java.md
[main/java/com/ohnosequences/xml/model/PredictedRna.java]: ../../xml/model/PredictedRna.java.md
[main/java/com/ohnosequences/xml/model/PredictedRnas.java]: ../../xml/model/PredictedRnas.java.md
[main/java/com/ohnosequences/xml/model/uniprot/ArticleXML.java]: ../../xml/model/uniprot/ArticleXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/CommentXML.java]: ../../xml/model/uniprot/CommentXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/FeatureXML.java]: ../../xml/model/uniprot/FeatureXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/InterproXML.java]: ../../xml/model/uniprot/InterproXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/IsoformXML.java]: ../../xml/model/uniprot/IsoformXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/KeywordXML.java]: ../../xml/model/uniprot/KeywordXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/ProteinXML.java]: ../../xml/model/uniprot/ProteinXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/SubcellularLocationXML.java]: ../../xml/model/uniprot/SubcellularLocationXML.java.md
[main/java/com/ohnosequences/xml/model/util/Argument.java]: ../../xml/model/util/Argument.java.md
[main/java/com/ohnosequences/xml/model/util/Arguments.java]: ../../xml/model/util/Arguments.java.md
[main/java/com/ohnosequences/xml/model/util/Error.java]: ../../xml/model/util/Error.java.md
[main/java/com/ohnosequences/xml/model/util/Execution.java]: ../../xml/model/util/Execution.java.md
[main/java/com/ohnosequences/xml/model/util/FlexXMLWrapperClassCreator.java]: ../../xml/model/util/FlexXMLWrapperClassCreator.java.md
[main/java/com/ohnosequences/xml/model/util/ScheduledExecutions.java]: ../../xml/model/util/ScheduledExecutions.java.md
[main/java/com/ohnosequences/xml/model/util/XMLWrapperClass.java]: ../../xml/model/util/XMLWrapperClass.java.md
[main/java/com/ohnosequences/xml/model/util/XMLWrapperClassCreator.java]: ../../xml/model/util/XMLWrapperClassCreator.java.md
[main/java/com/ohnosequences/xml/model/wip/Region.java]: ../../xml/model/wip/Region.java.md
[main/java/com/ohnosequences/xml/model/wip/WipPosition.java]: ../../xml/model/wip/WipPosition.java.md
[main/java/com/ohnosequences/xml/model/wip/WipResult.java]: ../../xml/model/wip/WipResult.java.md