
 * To change this template, choose Tools | Templates
 * and open the template in the editor.


```java
package com.ohnosequences.xml.api.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.InputSource;

/**
 *
 * @author <a href="mailto:ppareja@ohnosequences.com">Pablo Pareja-Tobes</a>
 */
public class XMLUtil {

    public static String prettyPrintXML(String xml, int indentAmount) throws TransformerConfigurationException, TransformerException {

        Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();

        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", String.valueOf(indentAmount));
        Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
        StreamResult res = new StreamResult(new ByteArrayOutputStream());
        serializer.transform(xmlSource, res);

        return new String(((ByteArrayOutputStream) res.getOutputStream()).toByteArray());
    }
}

```




[main/java/com/ohnosequences/BioinfoUtil.java]: ../../../BioinfoUtil.java.md
[main/java/com/ohnosequences/util/BitOperations.java]: ../../../util/BitOperations.java.md
[main/java/com/ohnosequences/util/blast/BlastExporter.java]: ../../../util/blast/BlastExporter.java.md
[main/java/com/ohnosequences/util/blast/BlastSubset.java]: ../../../util/blast/BlastSubset.java.md
[main/java/com/ohnosequences/util/CodonUtil.java]: ../../../util/CodonUtil.java.md
[main/java/com/ohnosequences/util/Entry.java]: ../../../util/Entry.java.md
[main/java/com/ohnosequences/util/Executable.java]: ../../../util/Executable.java.md
[main/java/com/ohnosequences/util/ExecuteFromFile.java]: ../../../util/ExecuteFromFile.java.md
[main/java/com/ohnosequences/util/fasta/FastaSubSeq.java]: ../../../util/fasta/FastaSubSeq.java.md
[main/java/com/ohnosequences/util/fasta/FastaUtil.java]: ../../../util/fasta/FastaUtil.java.md
[main/java/com/ohnosequences/util/fasta/MultifastaSelector.java]: ../../../util/fasta/MultifastaSelector.java.md
[main/java/com/ohnosequences/util/fasta/SearchFastaHeaders.java]: ../../../util/fasta/SearchFastaHeaders.java.md
[main/java/com/ohnosequences/util/fasta/SearchFastaSequence.java]: ../../../util/fasta/SearchFastaSequence.java.md
[main/java/com/ohnosequences/util/file/FileUtil.java]: ../../../util/file/FileUtil.java.md
[main/java/com/ohnosequences/util/file/FnaFileFilter.java]: ../../../util/file/FnaFileFilter.java.md
[main/java/com/ohnosequences/util/file/GenomeFilesParser.java]: ../../../util/file/GenomeFilesParser.java.md
[main/java/com/ohnosequences/util/file/PttFileFilter.java]: ../../../util/file/PttFileFilter.java.md
[main/java/com/ohnosequences/util/file/RntFileFilter.java]: ../../../util/file/RntFileFilter.java.md
[main/java/com/ohnosequences/util/genbank/GBCommon.java]: ../../../util/genbank/GBCommon.java.md
[main/java/com/ohnosequences/util/gephi/GephiExporter.java]: ../../../util/gephi/GephiExporter.java.md
[main/java/com/ohnosequences/util/gephi/GexfToDotExporter.java]: ../../../util/gephi/GexfToDotExporter.java.md
[main/java/com/ohnosequences/util/go/GOExporter.java]: ../../../util/go/GOExporter.java.md
[main/java/com/ohnosequences/util/model/Feature.java]: ../../../util/model/Feature.java.md
[main/java/com/ohnosequences/util/model/Intergenic.java]: ../../../util/model/Intergenic.java.md
[main/java/com/ohnosequences/util/model/PalindromicityResult.java]: ../../../util/model/PalindromicityResult.java.md
[main/java/com/ohnosequences/util/ncbi/TaxonomyLoader.java]: ../../../util/ncbi/TaxonomyLoader.java.md
[main/java/com/ohnosequences/util/oric/OricDataRetriever.java]: ../../../util/oric/OricDataRetriever.java.md
[main/java/com/ohnosequences/util/Pair.java]: ../../../util/Pair.java.md
[main/java/com/ohnosequences/util/pal/PalindromicityAnalyzer.java]: ../../../util/pal/PalindromicityAnalyzer.java.md
[main/java/com/ohnosequences/util/security/MD5.java]: ../../../util/security/MD5.java.md
[main/java/com/ohnosequences/util/seq/SeqUtil.java]: ../../../util/seq/SeqUtil.java.md
[main/java/com/ohnosequences/util/statistics/StatisticalValues.java]: ../../../util/statistics/StatisticalValues.java.md
[main/java/com/ohnosequences/util/uniprot/UniprotProteinRetreiver.java]: ../../../util/uniprot/UniprotProteinRetreiver.java.md
[main/java/com/ohnosequences/xml/api/interfaces/IAttribute.java]: ../interfaces/IAttribute.java.md
[main/java/com/ohnosequences/xml/api/interfaces/IElement.java]: ../interfaces/IElement.java.md
[main/java/com/ohnosequences/xml/api/interfaces/INameSpace.java]: ../interfaces/INameSpace.java.md
[main/java/com/ohnosequences/xml/api/interfaces/IXmlThing.java]: ../interfaces/IXmlThing.java.md
[main/java/com/ohnosequences/xml/api/interfaces/package-info.java]: ../interfaces/package-info.java.md
[main/java/com/ohnosequences/xml/api/model/NameSpace.java]: ../model/NameSpace.java.md
[main/java/com/ohnosequences/xml/api/model/package-info.java]: ../model/package-info.java.md
[main/java/com/ohnosequences/xml/api/model/XMLAttribute.java]: ../model/XMLAttribute.java.md
[main/java/com/ohnosequences/xml/api/model/XMLElement.java]: ../model/XMLElement.java.md
[main/java/com/ohnosequences/xml/api/model/XMLElementException.java]: ../model/XMLElementException.java.md
[main/java/com/ohnosequences/xml/api/util/XMLUtil.java]: XMLUtil.java.md
[main/java/com/ohnosequences/xml/model/Annotation.java]: ../../model/Annotation.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jNodeIndexXML.java]: ../../model/bio4j/Bio4jNodeIndexXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jNodeXML.java]: ../../model/bio4j/Bio4jNodeXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jPropertyXML.java]: ../../model/bio4j/Bio4jPropertyXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jRelationshipIndexXML.java]: ../../model/bio4j/Bio4jRelationshipIndexXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jRelationshipXML.java]: ../../model/bio4j/Bio4jRelationshipXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/UniprotDataXML.java]: ../../model/bio4j/UniprotDataXML.java.md
[main/java/com/ohnosequences/xml/model/BlastOutput.java]: ../../model/BlastOutput.java.md
[main/java/com/ohnosequences/xml/model/BlastOutputParam.java]: ../../model/BlastOutputParam.java.md
[main/java/com/ohnosequences/xml/model/Codon.java]: ../../model/Codon.java.md
[main/java/com/ohnosequences/xml/model/ContigXML.java]: ../../model/ContigXML.java.md
[main/java/com/ohnosequences/xml/model/cufflinks/CuffLinksElement.java]: ../../model/cufflinks/CuffLinksElement.java.md
[main/java/com/ohnosequences/xml/model/embl/EmblXML.java]: ../../model/embl/EmblXML.java.md
[main/java/com/ohnosequences/xml/model/Frameshift.java]: ../../model/Frameshift.java.md
[main/java/com/ohnosequences/xml/model/Gap.java]: ../../model/Gap.java.md
[main/java/com/ohnosequences/xml/model/gb/GenBankXML.java]: ../../model/gb/GenBankXML.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/Feature.java]: ../../model/genome/feature/Feature.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/Intergenic.java]: ../../model/genome/feature/Intergenic.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/MisRNA.java]: ../../model/genome/feature/MisRNA.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/ORF.java]: ../../model/genome/feature/ORF.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/RNA.java]: ../../model/genome/feature/RNA.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/RRNA.java]: ../../model/genome/feature/RRNA.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/TRNA.java]: ../../model/genome/feature/TRNA.java.md
[main/java/com/ohnosequences/xml/model/genome/GenomeElement.java]: ../../model/genome/GenomeElement.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttributesXML.java]: ../../model/gexf/AttributesXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttributeXML.java]: ../../model/gexf/AttributeXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttValuesXML.java]: ../../model/gexf/AttValuesXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttValueXML.java]: ../../model/gexf/AttValueXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/EdgesXML.java]: ../../model/gexf/EdgesXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/EdgeXML.java]: ../../model/gexf/EdgeXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/GexfXML.java]: ../../model/gexf/GexfXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/GraphXML.java]: ../../model/gexf/GraphXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/NodesXML.java]: ../../model/gexf/NodesXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/NodeXML.java]: ../../model/gexf/NodeXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/SpellsXML.java]: ../../model/gexf/SpellsXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/SpellXML.java]: ../../model/gexf/SpellXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/viz/VizColorXML.java]: ../../model/gexf/viz/VizColorXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/viz/VizPositionXML.java]: ../../model/gexf/viz/VizPositionXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/viz/VizSizeXML.java]: ../../model/gexf/viz/VizSizeXML.java.md
[main/java/com/ohnosequences/xml/model/go/GoAnnotationXML.java]: ../../model/go/GoAnnotationXML.java.md
[main/java/com/ohnosequences/xml/model/go/GOSlimXML.java]: ../../model/go/GOSlimXML.java.md
[main/java/com/ohnosequences/xml/model/go/GoTermXML.java]: ../../model/go/GoTermXML.java.md
[main/java/com/ohnosequences/xml/model/go/SlimSetXML.java]: ../../model/go/SlimSetXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/DataXML.java]: ../../model/graphml/DataXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/EdgeXML.java]: ../../model/graphml/EdgeXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/GraphmlXML.java]: ../../model/graphml/GraphmlXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/GraphXML.java]: ../../model/graphml/GraphXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/KeyXML.java]: ../../model/graphml/KeyXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/NodeXML.java]: ../../model/graphml/NodeXML.java.md
[main/java/com/ohnosequences/xml/model/Hit.java]: ../../model/Hit.java.md
[main/java/com/ohnosequences/xml/model/Hsp.java]: ../../model/Hsp.java.md
[main/java/com/ohnosequences/xml/model/HspSet.java]: ../../model/HspSet.java.md
[main/java/com/ohnosequences/xml/model/Iteration.java]: ../../model/Iteration.java.md
[main/java/com/ohnosequences/xml/model/logs/LogRecordXML.java]: ../../model/logs/LogRecordXML.java.md
[main/java/com/ohnosequences/xml/model/metagenomics/ReadResultXML.java]: ../../model/metagenomics/ReadResultXML.java.md
[main/java/com/ohnosequences/xml/model/metagenomics/ReadXML.java]: ../../model/metagenomics/ReadXML.java.md
[main/java/com/ohnosequences/xml/model/metagenomics/SampleXML.java]: ../../model/metagenomics/SampleXML.java.md
[main/java/com/ohnosequences/xml/model/MetagenomicsDataXML.java]: ../../model/MetagenomicsDataXML.java.md
[main/java/com/ohnosequences/xml/model/mg7/MG7DataXML.java]: ../../model/mg7/MG7DataXML.java.md
[main/java/com/ohnosequences/xml/model/mg7/ReadResultXML.java]: ../../model/mg7/ReadResultXML.java.md
[main/java/com/ohnosequences/xml/model/mg7/SampleXML.java]: ../../model/mg7/SampleXML.java.md
[main/java/com/ohnosequences/xml/model/ncbi/NCBITaxonomyNodeXML.java]: ../../model/ncbi/NCBITaxonomyNodeXML.java.md
[main/java/com/ohnosequences/xml/model/oric/Oric.java]: ../../model/oric/Oric.java.md
[main/java/com/ohnosequences/xml/model/Overlap.java]: ../../model/Overlap.java.md
[main/java/com/ohnosequences/xml/model/pal/PalindromicityResultXML.java]: ../../model/pal/PalindromicityResultXML.java.md
[main/java/com/ohnosequences/xml/model/pg/Primer.java]: ../../model/pg/Primer.java.md
[main/java/com/ohnosequences/xml/model/PredictedGene.java]: ../../model/PredictedGene.java.md
[main/java/com/ohnosequences/xml/model/PredictedGenes.java]: ../../model/PredictedGenes.java.md
[main/java/com/ohnosequences/xml/model/PredictedRna.java]: ../../model/PredictedRna.java.md
[main/java/com/ohnosequences/xml/model/PredictedRnas.java]: ../../model/PredictedRnas.java.md
[main/java/com/ohnosequences/xml/model/uniprot/ArticleXML.java]: ../../model/uniprot/ArticleXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/CommentXML.java]: ../../model/uniprot/CommentXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/FeatureXML.java]: ../../model/uniprot/FeatureXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/InterproXML.java]: ../../model/uniprot/InterproXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/IsoformXML.java]: ../../model/uniprot/IsoformXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/KeywordXML.java]: ../../model/uniprot/KeywordXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/ProteinXML.java]: ../../model/uniprot/ProteinXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/SubcellularLocationXML.java]: ../../model/uniprot/SubcellularLocationXML.java.md
[main/java/com/ohnosequences/xml/model/util/Argument.java]: ../../model/util/Argument.java.md
[main/java/com/ohnosequences/xml/model/util/Arguments.java]: ../../model/util/Arguments.java.md
[main/java/com/ohnosequences/xml/model/util/Error.java]: ../../model/util/Error.java.md
[main/java/com/ohnosequences/xml/model/util/Execution.java]: ../../model/util/Execution.java.md
[main/java/com/ohnosequences/xml/model/util/FlexXMLWrapperClassCreator.java]: ../../model/util/FlexXMLWrapperClassCreator.java.md
[main/java/com/ohnosequences/xml/model/util/ScheduledExecutions.java]: ../../model/util/ScheduledExecutions.java.md
[main/java/com/ohnosequences/xml/model/util/XMLWrapperClass.java]: ../../model/util/XMLWrapperClass.java.md
[main/java/com/ohnosequences/xml/model/util/XMLWrapperClassCreator.java]: ../../model/util/XMLWrapperClassCreator.java.md
[main/java/com/ohnosequences/xml/model/wip/Region.java]: ../../model/wip/Region.java.md
[main/java/com/ohnosequences/xml/model/wip/WipPosition.java]: ../../model/wip/WipPosition.java.md
[main/java/com/ohnosequences/xml/model/wip/WipResult.java]: ../../model/wip/WipResult.java.md