
 * To change this template, choose Tools | Templates
 * and open the template in the editor.


```java
package com.ohnosequences.util.gephi;

import com.ohnosequences.xml.model.uniprot.ProteinXML;
import com.ohnosequences.xml.model.gexf.AttValueXML;
import com.ohnosequences.xml.model.gexf.AttValuesXML;
import com.ohnosequences.xml.model.gexf.AttributeXML;
import com.ohnosequences.xml.model.gexf.AttributesXML;
import com.ohnosequences.xml.model.gexf.EdgeXML;
import com.ohnosequences.xml.model.gexf.GexfXML;
import com.ohnosequences.xml.model.gexf.GraphXML;
import com.ohnosequences.xml.model.gexf.NodeXML;
import com.ohnosequences.xml.model.gexf.viz.VizColorXML;
import com.ohnosequences.xml.model.gexf.viz.VizPositionXML;
import com.ohnosequences.xml.model.gexf.viz.VizSizeXML;
import com.ohnosequences.xml.model.go.GoAnnotationXML;
import com.ohnosequences.xml.model.go.GoTermXML;
import com.ohnosequences.xml.api.model.XMLElementException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Element;

/**
 *
 * @author <a href="mailto:ppareja@ohnosequences.com">Pablo Pareja-Tobes</a>
 */
public class GephiExporter {

    public static double DEFAULT_GO_SIZE = 5.0;
    public static double DEFAULT_PROTEIN_SIZE = 15.0;
    public static final String ALL_SUB_ONTOLOGIES = "all";
    public static final String MOLECULAR_FUNCTION_SUB_ONTOLOGY = GoTermXML.ASPECT_FUNCTION;
    public static final String BIOLOGICAL_PROCESS_SUB_ONTOLOGY = GoTermXML.ASPECT_PROCESS;
    public static final String CELLULAR_COMPONENT_SUB_ONTOLOGY = GoTermXML.ASPECT_COMPONENT;

    public static String exportGoAnnotationToGexf(GoAnnotationXML goAnnotationXML,
            VizColorXML proteinColor,
            VizColorXML goColor,
            Boolean proportionalSize,
            Boolean proteinsWithoutConnectionsIncluded,
            String subOntologyIncluded) throws XMLElementException {


        StringBuilder stBuilder = new StringBuilder();

        StringBuilder nodesXMLStBuilder = new StringBuilder("<nodes>\n");
        StringBuilder edgesXMLStBuilder = new StringBuilder("<edges>\n");

        int edgesIdCounter = 1;

        stBuilder.append("<" + GexfXML.TAG_NAME + ">\n");
        stBuilder.append("<" + GraphXML.TAG_NAME + " defaultedgetype=\"directed\">\n");

        AttributesXML attributesXML = new AttributesXML();
        attributesXML.setClass(AttributesXML.NODE_CLASS);
        AttributeXML idAttributeXML = new AttributeXML();
        idAttributeXML.setId("0");
        idAttributeXML.setTitle("ID");
        idAttributeXML.setType("string");
        attributesXML.addAttribute(idAttributeXML);
        AttributeXML nameAttributeXML = new AttributeXML();
        nameAttributeXML.setId("1");
        nameAttributeXML.setTitle("Name");
        nameAttributeXML.setType("string");
        attributesXML.addAttribute(nameAttributeXML);
        AttributeXML nodeTypeAttributeXML = new AttributeXML();
        nodeTypeAttributeXML.setId("2");
        nodeTypeAttributeXML.setTitle("Node type");
        nodeTypeAttributeXML.setType("string");
        attributesXML.addAttribute(nodeTypeAttributeXML);

        stBuilder.append((attributesXML.toString() + "\n"));


        List<GoTermXML> goTerms = goAnnotationXML.getAnnotatorGoTerms();
        Element proteinAnnotations = goAnnotationXML.getProteinAnnotations();
        List<Element> proteins = proteinAnnotations.getChildren(ProteinXML.TAG_NAME);


        //-----go terms-------------
        for (GoTermXML goTerm : goTerms) {

            boolean includeTerm = false;
            String termAspect = goTerm.getAspect();

            if (subOntologyIncluded.equals(GephiExporter.ALL_SUB_ONTOLOGIES)
                    || termAspect.equals(subOntologyIncluded)) {
                includeTerm = true;
            }

            if (includeTerm) {

                NodeXML nodeXML = new NodeXML();
                nodeXML.setId(goTerm.getId());
                nodeXML.setLabel(goTerm.getGoName());
                nodeXML.setColor(new VizColorXML((Element) goColor.asJDomElement().clone()));
                //nodeXML.setSize(new VizSizeXML((Element) goSize.asJDomElement().clone()));

                //---------size---------------------
                if (proportionalSize) {
                    nodeXML.setSize(new VizSizeXML(goTerm.getAnnotationsCount() * 5.0));
                } else {
                    nodeXML.setSize(new VizSizeXML(DEFAULT_GO_SIZE));
                }

                //---------position--------------------
                nodeXML.setPosition(new VizPositionXML(0, 0, 0));

                AttValuesXML attValuesXML = new AttValuesXML();
                AttValueXML nameAttValue = new AttValueXML();
                nameAttValue.setFor(1);
                nameAttValue.setValue(goTerm.getGoName());
                attValuesXML.addAttValue(nameAttValue);
                AttValueXML nodeTypeAttValue = new AttValueXML();
                nodeTypeAttValue.setFor(2);
                nodeTypeAttValue.setValue("GOTerm");
                attValuesXML.addAttValue(nodeTypeAttValue);
                nodeXML.setAttvalues(attValuesXML);


                nodesXMLStBuilder.append((nodeXML.toString() + "\n"));

            }



        }

        //-----------proteins-------------
        for (Element protElem : proteins) {

            ProteinXML proteinXML = new ProteinXML(protElem);
            NodeXML nodeXML = new NodeXML();
            nodeXML.setId(proteinXML.getId());
            nodeXML.setLabel(proteinXML.getId());
            nodeXML.setColor(new VizColorXML((Element) proteinColor.asJDomElement().clone()));
            nodeXML.setSize(new VizSizeXML(DEFAULT_PROTEIN_SIZE));
            //---------position--------------------
            nodeXML.setPosition(new VizPositionXML(0, 0, 0));

            AttValuesXML attValuesXML = new AttValuesXML();
            AttValueXML nameAttValue = new AttValueXML();
            nameAttValue.setFor(1);
            nameAttValue.setValue(proteinXML.getId());
            attValuesXML.addAttValue(nameAttValue);
            AttValueXML nodeTypeAttValue = new AttValueXML();
            nodeTypeAttValue.setFor(2);
            nodeTypeAttValue.setValue("Protein");
            attValuesXML.addAttValue(nodeTypeAttValue);
            nodeXML.setAttvalues(attValuesXML);


            //----edges----
            List<GoTermXML> proteinTerms = new ArrayList<GoTermXML>();
            List<GoTermXML> bioTerms = proteinXML.getBiologicalProcessGoTerms();
            List<GoTermXML> cellTerms = proteinXML.getCellularComponentGoTerms();
            List<GoTermXML> molTerms = proteinXML.getMolecularFunctionGoTerms();

            if (subOntologyIncluded.equals(GephiExporter.ALL_SUB_ONTOLOGIES)) {
                if (bioTerms != null) {
                    proteinTerms.addAll(bioTerms);
                }
                if (cellTerms != null) {
                    proteinTerms.addAll(cellTerms);
                }
                if (molTerms != null) {
                    proteinTerms.addAll(molTerms);
                }
            } else if (subOntologyIncluded.equals(GephiExporter.MOLECULAR_FUNCTION_SUB_ONTOLOGY)) {
                if (molTerms != null) {
                    proteinTerms.addAll(molTerms);
                }
            } else if (subOntologyIncluded.equals(GephiExporter.BIOLOGICAL_PROCESS_SUB_ONTOLOGY)) {
                if (bioTerms != null) {
                    proteinTerms.addAll(bioTerms);
                }
            } else if (subOntologyIncluded.equals(GephiExporter.CELLULAR_COMPONENT_SUB_ONTOLOGY)) {
                if (cellTerms != null) {
                    proteinTerms.addAll(cellTerms);
                }
            }



            for (GoTermXML goTermXML : proteinTerms) {
                EdgeXML edge = new EdgeXML();
                edge.setId(String.valueOf(edgesIdCounter++));
                edge.setTarget(proteinXML.getId());
                edge.setSource(goTermXML.getId());
                edge.setType(EdgeXML.DIRECTED_TYPE);

                edgesXMLStBuilder.append((edge.toString() + "\n"));
            }

            if (proteinsWithoutConnectionsIncluded || proteinTerms.size() > 0) {
                nodesXMLStBuilder.append((nodeXML.toString() + "\n"));
            }

        }


        stBuilder.append((nodesXMLStBuilder.toString() + "</nodes>\n"));
        stBuilder.append((edgesXMLStBuilder.toString() + "</edges>\n"));

        stBuilder.append("</" + GraphXML.TAG_NAME + ">\n");
        stBuilder.append("</" + GexfXML.TAG_NAME + ">\n");

        return stBuilder.toString();
    }
}

```




[main/java/com/ohnosequences/BioinfoUtil.java]: ../../BioinfoUtil.java.md
[main/java/com/ohnosequences/util/BitOperations.java]: ../BitOperations.java.md
[main/java/com/ohnosequences/util/blast/BlastExporter.java]: ../blast/BlastExporter.java.md
[main/java/com/ohnosequences/util/blast/BlastSubset.java]: ../blast/BlastSubset.java.md
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
[main/java/com/ohnosequences/util/gephi/GephiExporter.java]: GephiExporter.java.md
[main/java/com/ohnosequences/util/gephi/GexfToDotExporter.java]: GexfToDotExporter.java.md
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