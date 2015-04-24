
```java
package com.ohnosequences.xml.api.model;


import org.jdom2.Attribute;
import org.jdom2.Namespace;

import com.ohnosequences.xml.api.interfaces.IAttribute;
import com.ohnosequences.xml.api.interfaces.INameSpace;

public class XMLAttribute implements IAttribute {

	protected org.jdom2.Attribute attribute;
	
	/**
	 * 
	 * @param attr
	 */
	public XMLAttribute(org.jdom2.Attribute attr){
		this.attribute = attr;
	}
	/**
	 * 
	 * @param name Attribute name
	 * @param value Attribute value
	 */
	public XMLAttribute(String name, String value){
		this.attribute = new org.jdom2.Attribute(name,value);
	}
	
	/**
	 * 
	 * @param name Attribute name
	 * @param value Attribute value
	 * @param ns Namespace
	 */
	public XMLAttribute(String name, String value, INameSpace ns){
		this.attribute = new org.jdom2.Attribute(name,value,Namespace.getNamespace(ns.getPrefix(),ns.getUri()));
	}
	
//	/**
//	 * 
//	 * @param value
//	 * @throws XMLElementException 
//	 * @throws IOException 
//	 * @throws JDOMException 
//	 * @throws ParserConfigurationException 
//	 */
//	public XMLAttribute(XmlValue value) throws XMLElementException{
//		try {
//			if(value.getNodeType() == XmlValue.ATTRIBUTE_NODE){
//				this.attribute = new org.jdom2.Attribute(value.getNodeName(),
//														value.getNodeValue(),
//														Namespace.getNamespace(value.getPrefix(), value.getNamespaceURI()));
//			}else{
//				throw new XMLElementException(XMLElementException.WRONG_CONSTRUCTOR_PARAMETER,null);
//			}
//		} catch (XmlException e) {
//			e.printStackTrace();
//			throw new XMLElementException(XMLElementException.WRONG_CONSTRUCTOR_PARAMETER,null);
//		}
//	}

	@Override
	public String getName() {
		return this.attribute.getName();
	}

	@Override
	public INameSpace getNameSpace() {
		return new NameSpace(this.attribute.getNamespace());
	}

	@Override
	public String getText() {
		return this.attribute.getValue();
	}

	@Override
	public void setName(String newValue) {
		this.attribute.setName(newValue);
	}

	@Override
	public void setNameSpace(INameSpace newValue) {
		this.attribute.setNamespace(Namespace.getNamespace(newValue.getPrefix(), newValue.getUri()));
	}

	@Override
	public void setText(String newValue) {
		this.attribute.setValue(newValue);
	}
	@Override
	public Attribute asJdomAttribute() {
		return this.attribute;
	}
```


	@Override
	public XmlValue asXmlvalue() throws XmlException {
		XmlValue temp = new XmlValue(XmlValue.ATTRIBUTE_NODE,this.getName());
		
		return temp;
	}

```java
}

```


------

### Index

+ src
  + test
    + java
    + scala
  + main
    + java
      + com
        + era7
          + era7xmlapi
            + util
              + [XMLUtil.java][main/java/com/era7/era7xmlapi/util/XMLUtil.java]
            + model
              + [NameSpace.java][main/java/com/era7/era7xmlapi/model/NameSpace.java]
              + [XMLElement.java][main/java/com/era7/era7xmlapi/model/XMLElement.java]
              + [XMLElementException.java][main/java/com/era7/era7xmlapi/model/XMLElementException.java]
              + [XMLAttribute.java][main/java/com/era7/era7xmlapi/model/XMLAttribute.java]
              + [package-info.java][main/java/com/era7/era7xmlapi/model/package-info.java]
            + interfaces
              + [IAttribute.java][main/java/com/era7/era7xmlapi/interfaces/IAttribute.java]
              + [IElement.java][main/java/com/era7/era7xmlapi/interfaces/IElement.java]
              + [IXmlThing.java][main/java/com/era7/era7xmlapi/interfaces/IXmlThing.java]
              + [INameSpace.java][main/java/com/era7/era7xmlapi/interfaces/INameSpace.java]
              + [package-info.java][main/java/com/era7/era7xmlapi/interfaces/package-info.java]
          + bioinfo
            + bioinfoaws
              + util
                + [AvailabilityZones.java][main/java/com/era7/bioinfo/bioinfoaws/util/AvailabilityZones.java]
                + [CredentialsRetriever.java][main/java/com/era7/bioinfo/bioinfoaws/util/CredentialsRetriever.java]
                + [Endpoints.java][main/java/com/era7/bioinfo/bioinfoaws/util/Endpoints.java]
                + [InstanceTypes.java][main/java/com/era7/bioinfo/bioinfoaws/util/InstanceTypes.java]
                + [AMITypes.java][main/java/com/era7/bioinfo/bioinfoaws/util/AMITypes.java]
              + s3
                + [S3FileUploader.java][main/java/com/era7/bioinfo/bioinfoaws/s3/S3FileUploader.java]
                + [S3FileDownloader.java][main/java/com/era7/bioinfo/bioinfoaws/s3/S3FileDownloader.java]
              + ec2
                + [SpotUtil.java][main/java/com/era7/bioinfo/bioinfoaws/ec2/SpotUtil.java]
                + [InstanceUtil.java][main/java/com/era7/bioinfo/bioinfoaws/ec2/InstanceUtil.java]
            + bioinfoneo4j
              + [Neo4jManager.java][main/java/com/era7/bioinfo/bioinfoneo4j/Neo4jManager.java]
              + [BasicRelationship.java][main/java/com/era7/bioinfo/bioinfoneo4j/BasicRelationship.java]
              + [BasicEntity.java][main/java/com/era7/bioinfo/bioinfoneo4j/BasicEntity.java]
            + bioinfoutil
              + fasta
                + [FastaUtil.java][main/java/com/era7/bioinfo/bioinfoutil/fasta/FastaUtil.java]
              + file
                + [GenomeFilesParser.java][main/java/com/era7/bioinfo/bioinfoutil/file/GenomeFilesParser.java]
                + [FnaFileFilter.java][main/java/com/era7/bioinfo/bioinfoutil/file/FnaFileFilter.java]
                + [RntFileFilter.java][main/java/com/era7/bioinfo/bioinfoutil/file/RntFileFilter.java]
                + [PttFileFilter.java][main/java/com/era7/bioinfo/bioinfoutil/file/PttFileFilter.java]
                + [FileUtil.java][main/java/com/era7/bioinfo/bioinfoutil/file/FileUtil.java]
              + statistics
                + [StatisticalValues.java][main/java/com/era7/bioinfo/bioinfoutil/statistics/StatisticalValues.java]
              + [Pair.java][main/java/com/era7/bioinfo/bioinfoutil/Pair.java]
              + pal
                + [PalindromicityAnalyzer.java][main/java/com/era7/bioinfo/bioinfoutil/pal/PalindromicityAnalyzer.java]
              + [Entry.java][main/java/com/era7/bioinfo/bioinfoutil/Entry.java]
              + go
                + [GOExporter.java][main/java/com/era7/bioinfo/bioinfoutil/go/GOExporter.java]
              + uniprot
                + [UniprotProteinRetreiver.java][main/java/com/era7/bioinfo/bioinfoutil/uniprot/UniprotProteinRetreiver.java]
              + oric
                + [OricDataRetriever.java][main/java/com/era7/bioinfo/bioinfoutil/oric/OricDataRetriever.java]
              + blast
                + [BlastSubset.java][main/java/com/era7/bioinfo/bioinfoutil/blast/BlastSubset.java]
                + [BlastExporter.java][main/java/com/era7/bioinfo/bioinfoutil/blast/BlastExporter.java]
              + model
                + [PalindromicityResult.java][main/java/com/era7/bioinfo/bioinfoutil/model/PalindromicityResult.java]
                + [Intergenic.java][main/java/com/era7/bioinfo/bioinfoutil/model/Intergenic.java]
                + [Feature.java][main/java/com/era7/bioinfo/bioinfoutil/model/Feature.java]
              + genbank
                + [GBCommon.java][main/java/com/era7/bioinfo/bioinfoutil/genbank/GBCommon.java]
              + [CodonUtil.java][main/java/com/era7/bioinfo/bioinfoutil/CodonUtil.java]
              + security
                + [MD5.java][main/java/com/era7/bioinfo/bioinfoutil/security/MD5.java]
              + [Executable.java][main/java/com/era7/bioinfo/bioinfoutil/Executable.java]
              + [ExecuteFromFile.java][main/java/com/era7/bioinfo/bioinfoutil/ExecuteFromFile.java]
              + seq
                + [SeqUtil.java][main/java/com/era7/bioinfo/bioinfoutil/seq/SeqUtil.java]
              + [BitOperations.java][main/java/com/era7/bioinfo/bioinfoutil/BitOperations.java]
              + ncbi
                + [TaxonomyLoader.java][main/java/com/era7/bioinfo/bioinfoutil/ncbi/TaxonomyLoader.java]
              + gephi
                + [GephiExporter.java][main/java/com/era7/bioinfo/bioinfoutil/gephi/GephiExporter.java]
                + [GexfToDotExpoerter.java][main/java/com/era7/bioinfo/bioinfoutil/gephi/GexfToDotExpoerter.java]
          + bioinfoxml
            + gexf
              + [GraphXML.java][main/java/com/era7/bioinfoxml/gexf/GraphXML.java]
              + viz
                + [VizSizeXML.java][main/java/com/era7/bioinfoxml/gexf/viz/VizSizeXML.java]
                + [VizPositionXML.java][main/java/com/era7/bioinfoxml/gexf/viz/VizPositionXML.java]
                + [VizColorXML.java][main/java/com/era7/bioinfoxml/gexf/viz/VizColorXML.java]
              + [GexfXML.java][main/java/com/era7/bioinfoxml/gexf/GexfXML.java]
              + [NodeXML.java][main/java/com/era7/bioinfoxml/gexf/NodeXML.java]
              + [SpellXML.java][main/java/com/era7/bioinfoxml/gexf/SpellXML.java]
              + [EdgeXML.java][main/java/com/era7/bioinfoxml/gexf/EdgeXML.java]
              + [NodesXML.java][main/java/com/era7/bioinfoxml/gexf/NodesXML.java]
              + [AttributeXML.java][main/java/com/era7/bioinfoxml/gexf/AttributeXML.java]
              + [AttValuesXML.java][main/java/com/era7/bioinfoxml/gexf/AttValuesXML.java]
              + [AttValueXML.java][main/java/com/era7/bioinfoxml/gexf/AttValueXML.java]
              + [EdgesXML.java][main/java/com/era7/bioinfoxml/gexf/EdgesXML.java]
              + [SpellsXML.java][main/java/com/era7/bioinfoxml/gexf/SpellsXML.java]
              + [AttributesXML.java][main/java/com/era7/bioinfoxml/gexf/AttributesXML.java]
            + pal
              + [PalindromicityResultXML.java][main/java/com/era7/bioinfoxml/pal/PalindromicityResultXML.java]
            + [Annotation.java][main/java/com/era7/bioinfoxml/Annotation.java]
            + [PredictedRna.java][main/java/com/era7/bioinfoxml/PredictedRna.java]
            + mg7
              + [MG7DataXML.java][main/java/com/era7/bioinfoxml/mg7/MG7DataXML.java]
              + [ReadResultXML.java][main/java/com/era7/bioinfoxml/mg7/ReadResultXML.java]
              + [SampleXML.java][main/java/com/era7/bioinfoxml/mg7/SampleXML.java]
            + [PredictedRnas.java][main/java/com/era7/bioinfoxml/PredictedRnas.java]
            + genome
              + feature
                + [MisRNA.java][main/java/com/era7/bioinfoxml/genome/feature/MisRNA.java]
                + [RRNA.java][main/java/com/era7/bioinfoxml/genome/feature/RRNA.java]
                + [Intergenic.java][main/java/com/era7/bioinfoxml/genome/feature/Intergenic.java]
                + [Feature.java][main/java/com/era7/bioinfoxml/genome/feature/Feature.java]
                + [ORF.java][main/java/com/era7/bioinfoxml/genome/feature/ORF.java]
                + [TRNA.java][main/java/com/era7/bioinfoxml/genome/feature/TRNA.java]
                + [RNA.java][main/java/com/era7/bioinfoxml/genome/feature/RNA.java]
              + [GenomeElement.java][main/java/com/era7/bioinfoxml/genome/GenomeElement.java]
            + wip
              + [WipPosition.java][main/java/com/era7/bioinfoxml/wip/WipPosition.java]
              + [WipResult.java][main/java/com/era7/bioinfoxml/wip/WipResult.java]
              + [Region.java][main/java/com/era7/bioinfoxml/wip/Region.java]
            + [Hsp.java][main/java/com/era7/bioinfoxml/Hsp.java]
            + [Gap.java][main/java/com/era7/bioinfoxml/Gap.java]
            + [MetagenomicsDataXML.java][main/java/com/era7/bioinfoxml/MetagenomicsDataXML.java]
            + gb
              + [GenBankXML.java][main/java/com/era7/bioinfoxml/gb/GenBankXML.java]
            + go
              + [GOSlimXML.java][main/java/com/era7/bioinfoxml/go/GOSlimXML.java]
              + [SlimSetXML.java][main/java/com/era7/bioinfoxml/go/SlimSetXML.java]
              + [GoTermXML.java][main/java/com/era7/bioinfoxml/go/GoTermXML.java]
              + [GoAnnotationXML.java][main/java/com/era7/bioinfoxml/go/GoAnnotationXML.java]
            + util
              + [XMLWrapperClassCreator.java][main/java/com/era7/bioinfoxml/util/XMLWrapperClassCreator.java]
              + [Execution.java][main/java/com/era7/bioinfoxml/util/Execution.java]
              + [Error.java][main/java/com/era7/bioinfoxml/util/Error.java]
              + [XMLWrapperClass.java][main/java/com/era7/bioinfoxml/util/XMLWrapperClass.java]
              + [FlexXMLWrapperClassCreator.java][main/java/com/era7/bioinfoxml/util/FlexXMLWrapperClassCreator.java]
              + [Arguments.java][main/java/com/era7/bioinfoxml/util/Arguments.java]
              + [ScheduledExecutions.java][main/java/com/era7/bioinfoxml/util/ScheduledExecutions.java]
              + [Argument.java][main/java/com/era7/bioinfoxml/util/Argument.java]
            + metagenomics
              + [ReadResultXML.java][main/java/com/era7/bioinfoxml/metagenomics/ReadResultXML.java]
              + [ReadXML.java][main/java/com/era7/bioinfoxml/metagenomics/ReadXML.java]
              + [SampleXML.java][main/java/com/era7/bioinfoxml/metagenomics/SampleXML.java]
            + uniprot
              + [ProteinXML.java][main/java/com/era7/bioinfoxml/uniprot/ProteinXML.java]
              + [ArticleXML.java][main/java/com/era7/bioinfoxml/uniprot/ArticleXML.java]
              + [FeatureXML.java][main/java/com/era7/bioinfoxml/uniprot/FeatureXML.java]
              + [IsoformXML.java][main/java/com/era7/bioinfoxml/uniprot/IsoformXML.java]
              + [SubcellularLocationXML.java][main/java/com/era7/bioinfoxml/uniprot/SubcellularLocationXML.java]
              + [InterproXML.java][main/java/com/era7/bioinfoxml/uniprot/InterproXML.java]
              + [CommentXML.java][main/java/com/era7/bioinfoxml/uniprot/CommentXML.java]
              + [KeywordXML.java][main/java/com/era7/bioinfoxml/uniprot/KeywordXML.java]
            + oric
              + [Oric.java][main/java/com/era7/bioinfoxml/oric/Oric.java]
            + [ContigXML.java][main/java/com/era7/bioinfoxml/ContigXML.java]
            + [BlastOutput.java][main/java/com/era7/bioinfoxml/BlastOutput.java]
            + pg
              + [Primer.java][main/java/com/era7/bioinfoxml/pg/Primer.java]
            + [Iteration.java][main/java/com/era7/bioinfoxml/Iteration.java]
            + cufflinks
              + [CuffLinksElement.java][main/java/com/era7/bioinfoxml/cufflinks/CuffLinksElement.java]
            + [PredictedGene.java][main/java/com/era7/bioinfoxml/PredictedGene.java]
            + logs
              + [LogRecordXML.java][main/java/com/era7/bioinfoxml/logs/LogRecordXML.java]
            + [Frameshift.java][main/java/com/era7/bioinfoxml/Frameshift.java]
            + embl
              + [EmblXML.java][main/java/com/era7/bioinfoxml/embl/EmblXML.java]
            + [Hit.java][main/java/com/era7/bioinfoxml/Hit.java]
            + [BlastOutputParam.java][main/java/com/era7/bioinfoxml/BlastOutputParam.java]
            + [Overlap.java][main/java/com/era7/bioinfoxml/Overlap.java]
            + [HspSet.java][main/java/com/era7/bioinfoxml/HspSet.java]
            + bio4j
              + [UniprotDataXML.java][main/java/com/era7/bioinfoxml/bio4j/UniprotDataXML.java]
              + [Bio4jPropertyXML.java][main/java/com/era7/bioinfoxml/bio4j/Bio4jPropertyXML.java]
              + [Bio4jRelationshipIndexXML.java][main/java/com/era7/bioinfoxml/bio4j/Bio4jRelationshipIndexXML.java]
              + [Bio4jNodeXML.java][main/java/com/era7/bioinfoxml/bio4j/Bio4jNodeXML.java]
              + [Bio4jNodeIndexXML.java][main/java/com/era7/bioinfoxml/bio4j/Bio4jNodeIndexXML.java]
              + [Bio4jRelationshipXML.java][main/java/com/era7/bioinfoxml/bio4j/Bio4jRelationshipXML.java]
            + [PredictedGenes.java][main/java/com/era7/bioinfoxml/PredictedGenes.java]
            + ncbi
              + [NCBITaxonomyNodeXML.java][main/java/com/era7/bioinfoxml/ncbi/NCBITaxonomyNodeXML.java]
            + graphml
              + [GraphmlXML.java][main/java/com/era7/bioinfoxml/graphml/GraphmlXML.java]
              + [GraphXML.java][main/java/com/era7/bioinfoxml/graphml/GraphXML.java]
              + [KeyXML.java][main/java/com/era7/bioinfoxml/graphml/KeyXML.java]
              + [NodeXML.java][main/java/com/era7/bioinfoxml/graphml/NodeXML.java]
              + [EdgeXML.java][main/java/com/era7/bioinfoxml/graphml/EdgeXML.java]
              + [DataXML.java][main/java/com/era7/bioinfoxml/graphml/DataXML.java]
            + [Codon.java][main/java/com/era7/bioinfoxml/Codon.java]
        + ohnosequences
          + xml
            + api
              + util
                + [XMLUtil.java][main/java/com/ohnosequences/xml/api/util/XMLUtil.java]
              + model
                + [NameSpace.java][main/java/com/ohnosequences/xml/api/model/NameSpace.java]
                + [XMLElement.java][main/java/com/ohnosequences/xml/api/model/XMLElement.java]
                + [XMLElementException.java][main/java/com/ohnosequences/xml/api/model/XMLElementException.java]
                + [XMLAttribute.java][main/java/com/ohnosequences/xml/api/model/XMLAttribute.java]
                + [package-info.java][main/java/com/ohnosequences/xml/api/model/package-info.java]
              + interfaces
                + [IAttribute.java][main/java/com/ohnosequences/xml/api/interfaces/IAttribute.java]
                + [IElement.java][main/java/com/ohnosequences/xml/api/interfaces/IElement.java]
                + [IXmlThing.java][main/java/com/ohnosequences/xml/api/interfaces/IXmlThing.java]
                + [INameSpace.java][main/java/com/ohnosequences/xml/api/interfaces/INameSpace.java]
                + [package-info.java][main/java/com/ohnosequences/xml/api/interfaces/package-info.java]
            + model
              + gexf
                + [GraphXML.java][main/java/com/ohnosequences/xml/model/gexf/GraphXML.java]
                + viz
                  + [VizSizeXML.java][main/java/com/ohnosequences/xml/model/gexf/viz/VizSizeXML.java]
                  + [VizPositionXML.java][main/java/com/ohnosequences/xml/model/gexf/viz/VizPositionXML.java]
                  + [VizColorXML.java][main/java/com/ohnosequences/xml/model/gexf/viz/VizColorXML.java]
                + [GexfXML.java][main/java/com/ohnosequences/xml/model/gexf/GexfXML.java]
                + [NodeXML.java][main/java/com/ohnosequences/xml/model/gexf/NodeXML.java]
                + [SpellXML.java][main/java/com/ohnosequences/xml/model/gexf/SpellXML.java]
                + [EdgeXML.java][main/java/com/ohnosequences/xml/model/gexf/EdgeXML.java]
                + [NodesXML.java][main/java/com/ohnosequences/xml/model/gexf/NodesXML.java]
                + [AttributeXML.java][main/java/com/ohnosequences/xml/model/gexf/AttributeXML.java]
                + [AttValuesXML.java][main/java/com/ohnosequences/xml/model/gexf/AttValuesXML.java]
                + [AttValueXML.java][main/java/com/ohnosequences/xml/model/gexf/AttValueXML.java]
                + [EdgesXML.java][main/java/com/ohnosequences/xml/model/gexf/EdgesXML.java]
                + [SpellsXML.java][main/java/com/ohnosequences/xml/model/gexf/SpellsXML.java]
                + [AttributesXML.java][main/java/com/ohnosequences/xml/model/gexf/AttributesXML.java]
              + pal
                + [PalindromicityResultXML.java][main/java/com/ohnosequences/xml/model/pal/PalindromicityResultXML.java]
              + [Annotation.java][main/java/com/ohnosequences/xml/model/Annotation.java]
              + [PredictedRna.java][main/java/com/ohnosequences/xml/model/PredictedRna.java]
              + mg7
                + [MG7DataXML.java][main/java/com/ohnosequences/xml/model/mg7/MG7DataXML.java]
                + [ReadResultXML.java][main/java/com/ohnosequences/xml/model/mg7/ReadResultXML.java]
                + [SampleXML.java][main/java/com/ohnosequences/xml/model/mg7/SampleXML.java]
              + [PredictedRnas.java][main/java/com/ohnosequences/xml/model/PredictedRnas.java]
              + genome
                + feature
                  + [MisRNA.java][main/java/com/ohnosequences/xml/model/genome/feature/MisRNA.java]
                  + [RRNA.java][main/java/com/ohnosequences/xml/model/genome/feature/RRNA.java]
                  + [Intergenic.java][main/java/com/ohnosequences/xml/model/genome/feature/Intergenic.java]
                  + [Feature.java][main/java/com/ohnosequences/xml/model/genome/feature/Feature.java]
                  + [ORF.java][main/java/com/ohnosequences/xml/model/genome/feature/ORF.java]
                  + [TRNA.java][main/java/com/ohnosequences/xml/model/genome/feature/TRNA.java]
                  + [RNA.java][main/java/com/ohnosequences/xml/model/genome/feature/RNA.java]
                + [GenomeElement.java][main/java/com/ohnosequences/xml/model/genome/GenomeElement.java]
              + wip
                + [WipPosition.java][main/java/com/ohnosequences/xml/model/wip/WipPosition.java]
                + [WipResult.java][main/java/com/ohnosequences/xml/model/wip/WipResult.java]
                + [Region.java][main/java/com/ohnosequences/xml/model/wip/Region.java]
              + [Hsp.java][main/java/com/ohnosequences/xml/model/Hsp.java]
              + [Gap.java][main/java/com/ohnosequences/xml/model/Gap.java]
              + [MetagenomicsDataXML.java][main/java/com/ohnosequences/xml/model/MetagenomicsDataXML.java]
              + gb
                + [GenBankXML.java][main/java/com/ohnosequences/xml/model/gb/GenBankXML.java]
              + go
                + [GOSlimXML.java][main/java/com/ohnosequences/xml/model/go/GOSlimXML.java]
                + [SlimSetXML.java][main/java/com/ohnosequences/xml/model/go/SlimSetXML.java]
                + [GoTermXML.java][main/java/com/ohnosequences/xml/model/go/GoTermXML.java]
                + [GoAnnotationXML.java][main/java/com/ohnosequences/xml/model/go/GoAnnotationXML.java]
              + util
                + [XMLWrapperClassCreator.java][main/java/com/ohnosequences/xml/model/util/XMLWrapperClassCreator.java]
                + [Execution.java][main/java/com/ohnosequences/xml/model/util/Execution.java]
                + [Error.java][main/java/com/ohnosequences/xml/model/util/Error.java]
                + [XMLWrapperClass.java][main/java/com/ohnosequences/xml/model/util/XMLWrapperClass.java]
                + [FlexXMLWrapperClassCreator.java][main/java/com/ohnosequences/xml/model/util/FlexXMLWrapperClassCreator.java]
                + [Arguments.java][main/java/com/ohnosequences/xml/model/util/Arguments.java]
                + [ScheduledExecutions.java][main/java/com/ohnosequences/xml/model/util/ScheduledExecutions.java]
                + [Argument.java][main/java/com/ohnosequences/xml/model/util/Argument.java]
              + metagenomics
                + [ReadResultXML.java][main/java/com/ohnosequences/xml/model/metagenomics/ReadResultXML.java]
                + [ReadXML.java][main/java/com/ohnosequences/xml/model/metagenomics/ReadXML.java]
                + [SampleXML.java][main/java/com/ohnosequences/xml/model/metagenomics/SampleXML.java]
              + uniprot
                + [ProteinXML.java][main/java/com/ohnosequences/xml/model/uniprot/ProteinXML.java]
                + [ArticleXML.java][main/java/com/ohnosequences/xml/model/uniprot/ArticleXML.java]
                + [FeatureXML.java][main/java/com/ohnosequences/xml/model/uniprot/FeatureXML.java]
                + [IsoformXML.java][main/java/com/ohnosequences/xml/model/uniprot/IsoformXML.java]
                + [SubcellularLocationXML.java][main/java/com/ohnosequences/xml/model/uniprot/SubcellularLocationXML.java]
                + [InterproXML.java][main/java/com/ohnosequences/xml/model/uniprot/InterproXML.java]
                + [CommentXML.java][main/java/com/ohnosequences/xml/model/uniprot/CommentXML.java]
                + [KeywordXML.java][main/java/com/ohnosequences/xml/model/uniprot/KeywordXML.java]
              + oric
                + [Oric.java][main/java/com/ohnosequences/xml/model/oric/Oric.java]
              + [ContigXML.java][main/java/com/ohnosequences/xml/model/ContigXML.java]
              + [BlastOutput.java][main/java/com/ohnosequences/xml/model/BlastOutput.java]
              + pg
                + [Primer.java][main/java/com/ohnosequences/xml/model/pg/Primer.java]
              + [Iteration.java][main/java/com/ohnosequences/xml/model/Iteration.java]
              + cufflinks
                + [CuffLinksElement.java][main/java/com/ohnosequences/xml/model/cufflinks/CuffLinksElement.java]
              + [PredictedGene.java][main/java/com/ohnosequences/xml/model/PredictedGene.java]
              + logs
                + [LogRecordXML.java][main/java/com/ohnosequences/xml/model/logs/LogRecordXML.java]
              + [Frameshift.java][main/java/com/ohnosequences/xml/model/Frameshift.java]
              + embl
                + [EmblXML.java][main/java/com/ohnosequences/xml/model/embl/EmblXML.java]
              + [Hit.java][main/java/com/ohnosequences/xml/model/Hit.java]
              + [BlastOutputParam.java][main/java/com/ohnosequences/xml/model/BlastOutputParam.java]
              + [Overlap.java][main/java/com/ohnosequences/xml/model/Overlap.java]
              + [HspSet.java][main/java/com/ohnosequences/xml/model/HspSet.java]
              + bio4j
                + [UniprotDataXML.java][main/java/com/ohnosequences/xml/model/bio4j/UniprotDataXML.java]
                + [Bio4jPropertyXML.java][main/java/com/ohnosequences/xml/model/bio4j/Bio4jPropertyXML.java]
                + [Bio4jRelationshipIndexXML.java][main/java/com/ohnosequences/xml/model/bio4j/Bio4jRelationshipIndexXML.java]
                + [Bio4jNodeXML.java][main/java/com/ohnosequences/xml/model/bio4j/Bio4jNodeXML.java]
                + [Bio4jNodeIndexXML.java][main/java/com/ohnosequences/xml/model/bio4j/Bio4jNodeIndexXML.java]
                + [Bio4jRelationshipXML.java][main/java/com/ohnosequences/xml/model/bio4j/Bio4jRelationshipXML.java]
              + [PredictedGenes.java][main/java/com/ohnosequences/xml/model/PredictedGenes.java]
              + ncbi
                + [NCBITaxonomyNodeXML.java][main/java/com/ohnosequences/xml/model/ncbi/NCBITaxonomyNodeXML.java]
              + graphml
                + [GraphmlXML.java][main/java/com/ohnosequences/xml/model/graphml/GraphmlXML.java]
                + [GraphXML.java][main/java/com/ohnosequences/xml/model/graphml/GraphXML.java]
                + [KeyXML.java][main/java/com/ohnosequences/xml/model/graphml/KeyXML.java]
                + [NodeXML.java][main/java/com/ohnosequences/xml/model/graphml/NodeXML.java]
                + [EdgeXML.java][main/java/com/ohnosequences/xml/model/graphml/EdgeXML.java]
                + [DataXML.java][main/java/com/ohnosequences/xml/model/graphml/DataXML.java]
              + [Codon.java][main/java/com/ohnosequences/xml/model/Codon.java]
          + util
            + fasta
              + [FastaUtil.java][main/java/com/ohnosequences/util/fasta/FastaUtil.java]
            + file
              + [GenomeFilesParser.java][main/java/com/ohnosequences/util/file/GenomeFilesParser.java]
              + [FnaFileFilter.java][main/java/com/ohnosequences/util/file/FnaFileFilter.java]
              + [RntFileFilter.java][main/java/com/ohnosequences/util/file/RntFileFilter.java]
              + [PttFileFilter.java][main/java/com/ohnosequences/util/file/PttFileFilter.java]
              + [FileUtil.java][main/java/com/ohnosequences/util/file/FileUtil.java]
            + statistics
              + [StatisticalValues.java][main/java/com/ohnosequences/util/statistics/StatisticalValues.java]
            + [Pair.java][main/java/com/ohnosequences/util/Pair.java]
            + pal
              + [PalindromicityAnalyzer.java][main/java/com/ohnosequences/util/pal/PalindromicityAnalyzer.java]
            + [Entry.java][main/java/com/ohnosequences/util/Entry.java]
            + go
              + [GOExporter.java][main/java/com/ohnosequences/util/go/GOExporter.java]
            + uniprot
              + [UniprotProteinRetreiver.java][main/java/com/ohnosequences/util/uniprot/UniprotProteinRetreiver.java]
            + oric
              + [OricDataRetriever.java][main/java/com/ohnosequences/util/oric/OricDataRetriever.java]
            + blast
              + [BlastSubset.java][main/java/com/ohnosequences/util/blast/BlastSubset.java]
              + [BlastExporter.java][main/java/com/ohnosequences/util/blast/BlastExporter.java]
            + model
              + [PalindromicityResult.java][main/java/com/ohnosequences/util/model/PalindromicityResult.java]
              + [Intergenic.java][main/java/com/ohnosequences/util/model/Intergenic.java]
              + [Feature.java][main/java/com/ohnosequences/util/model/Feature.java]
            + genbank
              + [GBCommon.java][main/java/com/ohnosequences/util/genbank/GBCommon.java]
            + [CodonUtil.java][main/java/com/ohnosequences/util/CodonUtil.java]
            + security
              + [MD5.java][main/java/com/ohnosequences/util/security/MD5.java]
            + [Executable.java][main/java/com/ohnosequences/util/Executable.java]
            + [ExecuteFromFile.java][main/java/com/ohnosequences/util/ExecuteFromFile.java]
            + seq
              + [SeqUtil.java][main/java/com/ohnosequences/util/seq/SeqUtil.java]
            + [BitOperations.java][main/java/com/ohnosequences/util/BitOperations.java]
            + ncbi
              + [TaxonomyLoader.java][main/java/com/ohnosequences/util/ncbi/TaxonomyLoader.java]
            + gephi
              + [GephiExporter.java][main/java/com/ohnosequences/util/gephi/GephiExporter.java]
              + [GexfToDotExporter.java][main/java/com/ohnosequences/util/gephi/GexfToDotExporter.java]
          + aws
            + util
              + [AvailabilityZones.java][main/java/com/ohnosequences/aws/util/AvailabilityZones.java]
              + [CredentialsRetriever.java][main/java/com/ohnosequences/aws/util/CredentialsRetriever.java]
              + [Endpoints.java][main/java/com/ohnosequences/aws/util/Endpoints.java]
              + [InstanceTypes.java][main/java/com/ohnosequences/aws/util/InstanceTypes.java]
              + [AMITypes.java][main/java/com/ohnosequences/aws/util/AMITypes.java]
            + s3
              + [S3FileUploader.java][main/java/com/ohnosequences/aws/s3/S3FileUploader.java]
              + [S3FileDownloader.java][main/java/com/ohnosequences/aws/s3/S3FileDownloader.java]
            + ec2
              + [SpotUtil.java][main/java/com/ohnosequences/aws/ec2/SpotUtil.java]
              + [InstanceUtil.java][main/java/com/ohnosequences/aws/ec2/InstanceUtil.java]
    + scala

[main/java/com/era7/era7xmlapi/util/XMLUtil.java]: ../../../../era7/era7xmlapi/util/XMLUtil.java.md
[main/java/com/era7/era7xmlapi/model/NameSpace.java]: ../../../../era7/era7xmlapi/model/NameSpace.java.md
[main/java/com/era7/era7xmlapi/model/XMLElement.java]: ../../../../era7/era7xmlapi/model/XMLElement.java.md
[main/java/com/era7/era7xmlapi/model/XMLElementException.java]: ../../../../era7/era7xmlapi/model/XMLElementException.java.md
[main/java/com/era7/era7xmlapi/model/XMLAttribute.java]: ../../../../era7/era7xmlapi/model/XMLAttribute.java.md
[main/java/com/era7/era7xmlapi/model/package-info.java]: ../../../../era7/era7xmlapi/model/package-info.java.md
[main/java/com/era7/era7xmlapi/interfaces/IAttribute.java]: ../../../../era7/era7xmlapi/interfaces/IAttribute.java.md
[main/java/com/era7/era7xmlapi/interfaces/IElement.java]: ../../../../era7/era7xmlapi/interfaces/IElement.java.md
[main/java/com/era7/era7xmlapi/interfaces/IXmlThing.java]: ../../../../era7/era7xmlapi/interfaces/IXmlThing.java.md
[main/java/com/era7/era7xmlapi/interfaces/INameSpace.java]: ../../../../era7/era7xmlapi/interfaces/INameSpace.java.md
[main/java/com/era7/era7xmlapi/interfaces/package-info.java]: ../../../../era7/era7xmlapi/interfaces/package-info.java.md
[main/java/com/era7/bioinfo/bioinfoaws/util/AvailabilityZones.java]: ../../../../era7/bioinfo/bioinfoaws/util/AvailabilityZones.java.md
[main/java/com/era7/bioinfo/bioinfoaws/util/CredentialsRetriever.java]: ../../../../era7/bioinfo/bioinfoaws/util/CredentialsRetriever.java.md
[main/java/com/era7/bioinfo/bioinfoaws/util/Endpoints.java]: ../../../../era7/bioinfo/bioinfoaws/util/Endpoints.java.md
[main/java/com/era7/bioinfo/bioinfoaws/util/InstanceTypes.java]: ../../../../era7/bioinfo/bioinfoaws/util/InstanceTypes.java.md
[main/java/com/era7/bioinfo/bioinfoaws/util/AMITypes.java]: ../../../../era7/bioinfo/bioinfoaws/util/AMITypes.java.md
[main/java/com/era7/bioinfo/bioinfoaws/s3/S3FileUploader.java]: ../../../../era7/bioinfo/bioinfoaws/s3/S3FileUploader.java.md
[main/java/com/era7/bioinfo/bioinfoaws/s3/S3FileDownloader.java]: ../../../../era7/bioinfo/bioinfoaws/s3/S3FileDownloader.java.md
[main/java/com/era7/bioinfo/bioinfoaws/ec2/SpotUtil.java]: ../../../../era7/bioinfo/bioinfoaws/ec2/SpotUtil.java.md
[main/java/com/era7/bioinfo/bioinfoaws/ec2/InstanceUtil.java]: ../../../../era7/bioinfo/bioinfoaws/ec2/InstanceUtil.java.md
[main/java/com/era7/bioinfo/bioinfoneo4j/Neo4jManager.java]: ../../../../era7/bioinfo/bioinfoneo4j/Neo4jManager.java.md
[main/java/com/era7/bioinfo/bioinfoneo4j/BasicRelationship.java]: ../../../../era7/bioinfo/bioinfoneo4j/BasicRelationship.java.md
[main/java/com/era7/bioinfo/bioinfoneo4j/BasicEntity.java]: ../../../../era7/bioinfo/bioinfoneo4j/BasicEntity.java.md
[main/java/com/era7/bioinfo/bioinfoutil/fasta/FastaUtil.java]: ../../../../era7/bioinfo/bioinfoutil/fasta/FastaUtil.java.md
[main/java/com/era7/bioinfo/bioinfoutil/file/GenomeFilesParser.java]: ../../../../era7/bioinfo/bioinfoutil/file/GenomeFilesParser.java.md
[main/java/com/era7/bioinfo/bioinfoutil/file/FnaFileFilter.java]: ../../../../era7/bioinfo/bioinfoutil/file/FnaFileFilter.java.md
[main/java/com/era7/bioinfo/bioinfoutil/file/RntFileFilter.java]: ../../../../era7/bioinfo/bioinfoutil/file/RntFileFilter.java.md
[main/java/com/era7/bioinfo/bioinfoutil/file/PttFileFilter.java]: ../../../../era7/bioinfo/bioinfoutil/file/PttFileFilter.java.md
[main/java/com/era7/bioinfo/bioinfoutil/file/FileUtil.java]: ../../../../era7/bioinfo/bioinfoutil/file/FileUtil.java.md
[main/java/com/era7/bioinfo/bioinfoutil/statistics/StatisticalValues.java]: ../../../../era7/bioinfo/bioinfoutil/statistics/StatisticalValues.java.md
[main/java/com/era7/bioinfo/bioinfoutil/Pair.java]: ../../../../era7/bioinfo/bioinfoutil/Pair.java.md
[main/java/com/era7/bioinfo/bioinfoutil/pal/PalindromicityAnalyzer.java]: ../../../../era7/bioinfo/bioinfoutil/pal/PalindromicityAnalyzer.java.md
[main/java/com/era7/bioinfo/bioinfoutil/Entry.java]: ../../../../era7/bioinfo/bioinfoutil/Entry.java.md
[main/java/com/era7/bioinfo/bioinfoutil/go/GOExporter.java]: ../../../../era7/bioinfo/bioinfoutil/go/GOExporter.java.md
[main/java/com/era7/bioinfo/bioinfoutil/uniprot/UniprotProteinRetreiver.java]: ../../../../era7/bioinfo/bioinfoutil/uniprot/UniprotProteinRetreiver.java.md
[main/java/com/era7/bioinfo/bioinfoutil/oric/OricDataRetriever.java]: ../../../../era7/bioinfo/bioinfoutil/oric/OricDataRetriever.java.md
[main/java/com/era7/bioinfo/bioinfoutil/blast/BlastSubset.java]: ../../../../era7/bioinfo/bioinfoutil/blast/BlastSubset.java.md
[main/java/com/era7/bioinfo/bioinfoutil/blast/BlastExporter.java]: ../../../../era7/bioinfo/bioinfoutil/blast/BlastExporter.java.md
[main/java/com/era7/bioinfo/bioinfoutil/model/PalindromicityResult.java]: ../../../../era7/bioinfo/bioinfoutil/model/PalindromicityResult.java.md
[main/java/com/era7/bioinfo/bioinfoutil/model/Intergenic.java]: ../../../../era7/bioinfo/bioinfoutil/model/Intergenic.java.md
[main/java/com/era7/bioinfo/bioinfoutil/model/Feature.java]: ../../../../era7/bioinfo/bioinfoutil/model/Feature.java.md
[main/java/com/era7/bioinfo/bioinfoutil/genbank/GBCommon.java]: ../../../../era7/bioinfo/bioinfoutil/genbank/GBCommon.java.md
[main/java/com/era7/bioinfo/bioinfoutil/CodonUtil.java]: ../../../../era7/bioinfo/bioinfoutil/CodonUtil.java.md
[main/java/com/era7/bioinfo/bioinfoutil/security/MD5.java]: ../../../../era7/bioinfo/bioinfoutil/security/MD5.java.md
[main/java/com/era7/bioinfo/bioinfoutil/Executable.java]: ../../../../era7/bioinfo/bioinfoutil/Executable.java.md
[main/java/com/era7/bioinfo/bioinfoutil/ExecuteFromFile.java]: ../../../../era7/bioinfo/bioinfoutil/ExecuteFromFile.java.md
[main/java/com/era7/bioinfo/bioinfoutil/seq/SeqUtil.java]: ../../../../era7/bioinfo/bioinfoutil/seq/SeqUtil.java.md
[main/java/com/era7/bioinfo/bioinfoutil/BitOperations.java]: ../../../../era7/bioinfo/bioinfoutil/BitOperations.java.md
[main/java/com/era7/bioinfo/bioinfoutil/ncbi/TaxonomyLoader.java]: ../../../../era7/bioinfo/bioinfoutil/ncbi/TaxonomyLoader.java.md
[main/java/com/era7/bioinfo/bioinfoutil/gephi/GephiExporter.java]: ../../../../era7/bioinfo/bioinfoutil/gephi/GephiExporter.java.md
[main/java/com/era7/bioinfo/bioinfoutil/gephi/GexfToDotExpoerter.java]: ../../../../era7/bioinfo/bioinfoutil/gephi/GexfToDotExpoerter.java.md
[main/java/com/era7/bioinfoxml/gexf/GraphXML.java]: ../../../../era7/bioinfoxml/gexf/GraphXML.java.md
[main/java/com/era7/bioinfoxml/gexf/viz/VizSizeXML.java]: ../../../../era7/bioinfoxml/gexf/viz/VizSizeXML.java.md
[main/java/com/era7/bioinfoxml/gexf/viz/VizPositionXML.java]: ../../../../era7/bioinfoxml/gexf/viz/VizPositionXML.java.md
[main/java/com/era7/bioinfoxml/gexf/viz/VizColorXML.java]: ../../../../era7/bioinfoxml/gexf/viz/VizColorXML.java.md
[main/java/com/era7/bioinfoxml/gexf/GexfXML.java]: ../../../../era7/bioinfoxml/gexf/GexfXML.java.md
[main/java/com/era7/bioinfoxml/gexf/NodeXML.java]: ../../../../era7/bioinfoxml/gexf/NodeXML.java.md
[main/java/com/era7/bioinfoxml/gexf/SpellXML.java]: ../../../../era7/bioinfoxml/gexf/SpellXML.java.md
[main/java/com/era7/bioinfoxml/gexf/EdgeXML.java]: ../../../../era7/bioinfoxml/gexf/EdgeXML.java.md
[main/java/com/era7/bioinfoxml/gexf/NodesXML.java]: ../../../../era7/bioinfoxml/gexf/NodesXML.java.md
[main/java/com/era7/bioinfoxml/gexf/AttributeXML.java]: ../../../../era7/bioinfoxml/gexf/AttributeXML.java.md
[main/java/com/era7/bioinfoxml/gexf/AttValuesXML.java]: ../../../../era7/bioinfoxml/gexf/AttValuesXML.java.md
[main/java/com/era7/bioinfoxml/gexf/AttValueXML.java]: ../../../../era7/bioinfoxml/gexf/AttValueXML.java.md
[main/java/com/era7/bioinfoxml/gexf/EdgesXML.java]: ../../../../era7/bioinfoxml/gexf/EdgesXML.java.md
[main/java/com/era7/bioinfoxml/gexf/SpellsXML.java]: ../../../../era7/bioinfoxml/gexf/SpellsXML.java.md
[main/java/com/era7/bioinfoxml/gexf/AttributesXML.java]: ../../../../era7/bioinfoxml/gexf/AttributesXML.java.md
[main/java/com/era7/bioinfoxml/pal/PalindromicityResultXML.java]: ../../../../era7/bioinfoxml/pal/PalindromicityResultXML.java.md
[main/java/com/era7/bioinfoxml/Annotation.java]: ../../../../era7/bioinfoxml/Annotation.java.md
[main/java/com/era7/bioinfoxml/PredictedRna.java]: ../../../../era7/bioinfoxml/PredictedRna.java.md
[main/java/com/era7/bioinfoxml/mg7/MG7DataXML.java]: ../../../../era7/bioinfoxml/mg7/MG7DataXML.java.md
[main/java/com/era7/bioinfoxml/mg7/ReadResultXML.java]: ../../../../era7/bioinfoxml/mg7/ReadResultXML.java.md
[main/java/com/era7/bioinfoxml/mg7/SampleXML.java]: ../../../../era7/bioinfoxml/mg7/SampleXML.java.md
[main/java/com/era7/bioinfoxml/PredictedRnas.java]: ../../../../era7/bioinfoxml/PredictedRnas.java.md
[main/java/com/era7/bioinfoxml/genome/feature/MisRNA.java]: ../../../../era7/bioinfoxml/genome/feature/MisRNA.java.md
[main/java/com/era7/bioinfoxml/genome/feature/RRNA.java]: ../../../../era7/bioinfoxml/genome/feature/RRNA.java.md
[main/java/com/era7/bioinfoxml/genome/feature/Intergenic.java]: ../../../../era7/bioinfoxml/genome/feature/Intergenic.java.md
[main/java/com/era7/bioinfoxml/genome/feature/Feature.java]: ../../../../era7/bioinfoxml/genome/feature/Feature.java.md
[main/java/com/era7/bioinfoxml/genome/feature/ORF.java]: ../../../../era7/bioinfoxml/genome/feature/ORF.java.md
[main/java/com/era7/bioinfoxml/genome/feature/TRNA.java]: ../../../../era7/bioinfoxml/genome/feature/TRNA.java.md
[main/java/com/era7/bioinfoxml/genome/feature/RNA.java]: ../../../../era7/bioinfoxml/genome/feature/RNA.java.md
[main/java/com/era7/bioinfoxml/genome/GenomeElement.java]: ../../../../era7/bioinfoxml/genome/GenomeElement.java.md
[main/java/com/era7/bioinfoxml/wip/WipPosition.java]: ../../../../era7/bioinfoxml/wip/WipPosition.java.md
[main/java/com/era7/bioinfoxml/wip/WipResult.java]: ../../../../era7/bioinfoxml/wip/WipResult.java.md
[main/java/com/era7/bioinfoxml/wip/Region.java]: ../../../../era7/bioinfoxml/wip/Region.java.md
[main/java/com/era7/bioinfoxml/Hsp.java]: ../../../../era7/bioinfoxml/Hsp.java.md
[main/java/com/era7/bioinfoxml/Gap.java]: ../../../../era7/bioinfoxml/Gap.java.md
[main/java/com/era7/bioinfoxml/MetagenomicsDataXML.java]: ../../../../era7/bioinfoxml/MetagenomicsDataXML.java.md
[main/java/com/era7/bioinfoxml/gb/GenBankXML.java]: ../../../../era7/bioinfoxml/gb/GenBankXML.java.md
[main/java/com/era7/bioinfoxml/go/GOSlimXML.java]: ../../../../era7/bioinfoxml/go/GOSlimXML.java.md
[main/java/com/era7/bioinfoxml/go/SlimSetXML.java]: ../../../../era7/bioinfoxml/go/SlimSetXML.java.md
[main/java/com/era7/bioinfoxml/go/GoTermXML.java]: ../../../../era7/bioinfoxml/go/GoTermXML.java.md
[main/java/com/era7/bioinfoxml/go/GoAnnotationXML.java]: ../../../../era7/bioinfoxml/go/GoAnnotationXML.java.md
[main/java/com/era7/bioinfoxml/util/XMLWrapperClassCreator.java]: ../../../../era7/bioinfoxml/util/XMLWrapperClassCreator.java.md
[main/java/com/era7/bioinfoxml/util/Execution.java]: ../../../../era7/bioinfoxml/util/Execution.java.md
[main/java/com/era7/bioinfoxml/util/Error.java]: ../../../../era7/bioinfoxml/util/Error.java.md
[main/java/com/era7/bioinfoxml/util/XMLWrapperClass.java]: ../../../../era7/bioinfoxml/util/XMLWrapperClass.java.md
[main/java/com/era7/bioinfoxml/util/FlexXMLWrapperClassCreator.java]: ../../../../era7/bioinfoxml/util/FlexXMLWrapperClassCreator.java.md
[main/java/com/era7/bioinfoxml/util/Arguments.java]: ../../../../era7/bioinfoxml/util/Arguments.java.md
[main/java/com/era7/bioinfoxml/util/ScheduledExecutions.java]: ../../../../era7/bioinfoxml/util/ScheduledExecutions.java.md
[main/java/com/era7/bioinfoxml/util/Argument.java]: ../../../../era7/bioinfoxml/util/Argument.java.md
[main/java/com/era7/bioinfoxml/metagenomics/ReadResultXML.java]: ../../../../era7/bioinfoxml/metagenomics/ReadResultXML.java.md
[main/java/com/era7/bioinfoxml/metagenomics/ReadXML.java]: ../../../../era7/bioinfoxml/metagenomics/ReadXML.java.md
[main/java/com/era7/bioinfoxml/metagenomics/SampleXML.java]: ../../../../era7/bioinfoxml/metagenomics/SampleXML.java.md
[main/java/com/era7/bioinfoxml/uniprot/ProteinXML.java]: ../../../../era7/bioinfoxml/uniprot/ProteinXML.java.md
[main/java/com/era7/bioinfoxml/uniprot/ArticleXML.java]: ../../../../era7/bioinfoxml/uniprot/ArticleXML.java.md
[main/java/com/era7/bioinfoxml/uniprot/FeatureXML.java]: ../../../../era7/bioinfoxml/uniprot/FeatureXML.java.md
[main/java/com/era7/bioinfoxml/uniprot/IsoformXML.java]: ../../../../era7/bioinfoxml/uniprot/IsoformXML.java.md
[main/java/com/era7/bioinfoxml/uniprot/SubcellularLocationXML.java]: ../../../../era7/bioinfoxml/uniprot/SubcellularLocationXML.java.md
[main/java/com/era7/bioinfoxml/uniprot/InterproXML.java]: ../../../../era7/bioinfoxml/uniprot/InterproXML.java.md
[main/java/com/era7/bioinfoxml/uniprot/CommentXML.java]: ../../../../era7/bioinfoxml/uniprot/CommentXML.java.md
[main/java/com/era7/bioinfoxml/uniprot/KeywordXML.java]: ../../../../era7/bioinfoxml/uniprot/KeywordXML.java.md
[main/java/com/era7/bioinfoxml/oric/Oric.java]: ../../../../era7/bioinfoxml/oric/Oric.java.md
[main/java/com/era7/bioinfoxml/ContigXML.java]: ../../../../era7/bioinfoxml/ContigXML.java.md
[main/java/com/era7/bioinfoxml/BlastOutput.java]: ../../../../era7/bioinfoxml/BlastOutput.java.md
[main/java/com/era7/bioinfoxml/pg/Primer.java]: ../../../../era7/bioinfoxml/pg/Primer.java.md
[main/java/com/era7/bioinfoxml/Iteration.java]: ../../../../era7/bioinfoxml/Iteration.java.md
[main/java/com/era7/bioinfoxml/cufflinks/CuffLinksElement.java]: ../../../../era7/bioinfoxml/cufflinks/CuffLinksElement.java.md
[main/java/com/era7/bioinfoxml/PredictedGene.java]: ../../../../era7/bioinfoxml/PredictedGene.java.md
[main/java/com/era7/bioinfoxml/logs/LogRecordXML.java]: ../../../../era7/bioinfoxml/logs/LogRecordXML.java.md
[main/java/com/era7/bioinfoxml/Frameshift.java]: ../../../../era7/bioinfoxml/Frameshift.java.md
[main/java/com/era7/bioinfoxml/embl/EmblXML.java]: ../../../../era7/bioinfoxml/embl/EmblXML.java.md
[main/java/com/era7/bioinfoxml/Hit.java]: ../../../../era7/bioinfoxml/Hit.java.md
[main/java/com/era7/bioinfoxml/BlastOutputParam.java]: ../../../../era7/bioinfoxml/BlastOutputParam.java.md
[main/java/com/era7/bioinfoxml/Overlap.java]: ../../../../era7/bioinfoxml/Overlap.java.md
[main/java/com/era7/bioinfoxml/HspSet.java]: ../../../../era7/bioinfoxml/HspSet.java.md
[main/java/com/era7/bioinfoxml/bio4j/UniprotDataXML.java]: ../../../../era7/bioinfoxml/bio4j/UniprotDataXML.java.md
[main/java/com/era7/bioinfoxml/bio4j/Bio4jPropertyXML.java]: ../../../../era7/bioinfoxml/bio4j/Bio4jPropertyXML.java.md
[main/java/com/era7/bioinfoxml/bio4j/Bio4jRelationshipIndexXML.java]: ../../../../era7/bioinfoxml/bio4j/Bio4jRelationshipIndexXML.java.md
[main/java/com/era7/bioinfoxml/bio4j/Bio4jNodeXML.java]: ../../../../era7/bioinfoxml/bio4j/Bio4jNodeXML.java.md
[main/java/com/era7/bioinfoxml/bio4j/Bio4jNodeIndexXML.java]: ../../../../era7/bioinfoxml/bio4j/Bio4jNodeIndexXML.java.md
[main/java/com/era7/bioinfoxml/bio4j/Bio4jRelationshipXML.java]: ../../../../era7/bioinfoxml/bio4j/Bio4jRelationshipXML.java.md
[main/java/com/era7/bioinfoxml/PredictedGenes.java]: ../../../../era7/bioinfoxml/PredictedGenes.java.md
[main/java/com/era7/bioinfoxml/ncbi/NCBITaxonomyNodeXML.java]: ../../../../era7/bioinfoxml/ncbi/NCBITaxonomyNodeXML.java.md
[main/java/com/era7/bioinfoxml/graphml/GraphmlXML.java]: ../../../../era7/bioinfoxml/graphml/GraphmlXML.java.md
[main/java/com/era7/bioinfoxml/graphml/GraphXML.java]: ../../../../era7/bioinfoxml/graphml/GraphXML.java.md
[main/java/com/era7/bioinfoxml/graphml/KeyXML.java]: ../../../../era7/bioinfoxml/graphml/KeyXML.java.md
[main/java/com/era7/bioinfoxml/graphml/NodeXML.java]: ../../../../era7/bioinfoxml/graphml/NodeXML.java.md
[main/java/com/era7/bioinfoxml/graphml/EdgeXML.java]: ../../../../era7/bioinfoxml/graphml/EdgeXML.java.md
[main/java/com/era7/bioinfoxml/graphml/DataXML.java]: ../../../../era7/bioinfoxml/graphml/DataXML.java.md
[main/java/com/era7/bioinfoxml/Codon.java]: ../../../../era7/bioinfoxml/Codon.java.md
[main/java/com/ohnosequences/xml/api/util/XMLUtil.java]: ../util/XMLUtil.java.md
[main/java/com/ohnosequences/xml/api/model/NameSpace.java]: NameSpace.java.md
[main/java/com/ohnosequences/xml/api/model/XMLElement.java]: XMLElement.java.md
[main/java/com/ohnosequences/xml/api/model/XMLElementException.java]: XMLElementException.java.md
[main/java/com/ohnosequences/xml/api/model/XMLAttribute.java]: XMLAttribute.java.md
[main/java/com/ohnosequences/xml/api/model/package-info.java]: package-info.java.md
[main/java/com/ohnosequences/xml/api/interfaces/IAttribute.java]: ../interfaces/IAttribute.java.md
[main/java/com/ohnosequences/xml/api/interfaces/IElement.java]: ../interfaces/IElement.java.md
[main/java/com/ohnosequences/xml/api/interfaces/IXmlThing.java]: ../interfaces/IXmlThing.java.md
[main/java/com/ohnosequences/xml/api/interfaces/INameSpace.java]: ../interfaces/INameSpace.java.md
[main/java/com/ohnosequences/xml/api/interfaces/package-info.java]: ../interfaces/package-info.java.md
[main/java/com/ohnosequences/xml/model/gexf/GraphXML.java]: ../../model/gexf/GraphXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/viz/VizSizeXML.java]: ../../model/gexf/viz/VizSizeXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/viz/VizPositionXML.java]: ../../model/gexf/viz/VizPositionXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/viz/VizColorXML.java]: ../../model/gexf/viz/VizColorXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/GexfXML.java]: ../../model/gexf/GexfXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/NodeXML.java]: ../../model/gexf/NodeXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/SpellXML.java]: ../../model/gexf/SpellXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/EdgeXML.java]: ../../model/gexf/EdgeXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/NodesXML.java]: ../../model/gexf/NodesXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttributeXML.java]: ../../model/gexf/AttributeXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttValuesXML.java]: ../../model/gexf/AttValuesXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttValueXML.java]: ../../model/gexf/AttValueXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/EdgesXML.java]: ../../model/gexf/EdgesXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/SpellsXML.java]: ../../model/gexf/SpellsXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttributesXML.java]: ../../model/gexf/AttributesXML.java.md
[main/java/com/ohnosequences/xml/model/pal/PalindromicityResultXML.java]: ../../model/pal/PalindromicityResultXML.java.md
[main/java/com/ohnosequences/xml/model/Annotation.java]: ../../model/Annotation.java.md
[main/java/com/ohnosequences/xml/model/PredictedRna.java]: ../../model/PredictedRna.java.md
[main/java/com/ohnosequences/xml/model/mg7/MG7DataXML.java]: ../../model/mg7/MG7DataXML.java.md
[main/java/com/ohnosequences/xml/model/mg7/ReadResultXML.java]: ../../model/mg7/ReadResultXML.java.md
[main/java/com/ohnosequences/xml/model/mg7/SampleXML.java]: ../../model/mg7/SampleXML.java.md
[main/java/com/ohnosequences/xml/model/PredictedRnas.java]: ../../model/PredictedRnas.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/MisRNA.java]: ../../model/genome/feature/MisRNA.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/RRNA.java]: ../../model/genome/feature/RRNA.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/Intergenic.java]: ../../model/genome/feature/Intergenic.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/Feature.java]: ../../model/genome/feature/Feature.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/ORF.java]: ../../model/genome/feature/ORF.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/TRNA.java]: ../../model/genome/feature/TRNA.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/RNA.java]: ../../model/genome/feature/RNA.java.md
[main/java/com/ohnosequences/xml/model/genome/GenomeElement.java]: ../../model/genome/GenomeElement.java.md
[main/java/com/ohnosequences/xml/model/wip/WipPosition.java]: ../../model/wip/WipPosition.java.md
[main/java/com/ohnosequences/xml/model/wip/WipResult.java]: ../../model/wip/WipResult.java.md
[main/java/com/ohnosequences/xml/model/wip/Region.java]: ../../model/wip/Region.java.md
[main/java/com/ohnosequences/xml/model/Hsp.java]: ../../model/Hsp.java.md
[main/java/com/ohnosequences/xml/model/Gap.java]: ../../model/Gap.java.md
[main/java/com/ohnosequences/xml/model/MetagenomicsDataXML.java]: ../../model/MetagenomicsDataXML.java.md
[main/java/com/ohnosequences/xml/model/gb/GenBankXML.java]: ../../model/gb/GenBankXML.java.md
[main/java/com/ohnosequences/xml/model/go/GOSlimXML.java]: ../../model/go/GOSlimXML.java.md
[main/java/com/ohnosequences/xml/model/go/SlimSetXML.java]: ../../model/go/SlimSetXML.java.md
[main/java/com/ohnosequences/xml/model/go/GoTermXML.java]: ../../model/go/GoTermXML.java.md
[main/java/com/ohnosequences/xml/model/go/GoAnnotationXML.java]: ../../model/go/GoAnnotationXML.java.md
[main/java/com/ohnosequences/xml/model/util/XMLWrapperClassCreator.java]: ../../model/util/XMLWrapperClassCreator.java.md
[main/java/com/ohnosequences/xml/model/util/Execution.java]: ../../model/util/Execution.java.md
[main/java/com/ohnosequences/xml/model/util/Error.java]: ../../model/util/Error.java.md
[main/java/com/ohnosequences/xml/model/util/XMLWrapperClass.java]: ../../model/util/XMLWrapperClass.java.md
[main/java/com/ohnosequences/xml/model/util/FlexXMLWrapperClassCreator.java]: ../../model/util/FlexXMLWrapperClassCreator.java.md
[main/java/com/ohnosequences/xml/model/util/Arguments.java]: ../../model/util/Arguments.java.md
[main/java/com/ohnosequences/xml/model/util/ScheduledExecutions.java]: ../../model/util/ScheduledExecutions.java.md
[main/java/com/ohnosequences/xml/model/util/Argument.java]: ../../model/util/Argument.java.md
[main/java/com/ohnosequences/xml/model/metagenomics/ReadResultXML.java]: ../../model/metagenomics/ReadResultXML.java.md
[main/java/com/ohnosequences/xml/model/metagenomics/ReadXML.java]: ../../model/metagenomics/ReadXML.java.md
[main/java/com/ohnosequences/xml/model/metagenomics/SampleXML.java]: ../../model/metagenomics/SampleXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/ProteinXML.java]: ../../model/uniprot/ProteinXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/ArticleXML.java]: ../../model/uniprot/ArticleXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/FeatureXML.java]: ../../model/uniprot/FeatureXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/IsoformXML.java]: ../../model/uniprot/IsoformXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/SubcellularLocationXML.java]: ../../model/uniprot/SubcellularLocationXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/InterproXML.java]: ../../model/uniprot/InterproXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/CommentXML.java]: ../../model/uniprot/CommentXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/KeywordXML.java]: ../../model/uniprot/KeywordXML.java.md
[main/java/com/ohnosequences/xml/model/oric/Oric.java]: ../../model/oric/Oric.java.md
[main/java/com/ohnosequences/xml/model/ContigXML.java]: ../../model/ContigXML.java.md
[main/java/com/ohnosequences/xml/model/BlastOutput.java]: ../../model/BlastOutput.java.md
[main/java/com/ohnosequences/xml/model/pg/Primer.java]: ../../model/pg/Primer.java.md
[main/java/com/ohnosequences/xml/model/Iteration.java]: ../../model/Iteration.java.md
[main/java/com/ohnosequences/xml/model/cufflinks/CuffLinksElement.java]: ../../model/cufflinks/CuffLinksElement.java.md
[main/java/com/ohnosequences/xml/model/PredictedGene.java]: ../../model/PredictedGene.java.md
[main/java/com/ohnosequences/xml/model/logs/LogRecordXML.java]: ../../model/logs/LogRecordXML.java.md
[main/java/com/ohnosequences/xml/model/Frameshift.java]: ../../model/Frameshift.java.md
[main/java/com/ohnosequences/xml/model/embl/EmblXML.java]: ../../model/embl/EmblXML.java.md
[main/java/com/ohnosequences/xml/model/Hit.java]: ../../model/Hit.java.md
[main/java/com/ohnosequences/xml/model/BlastOutputParam.java]: ../../model/BlastOutputParam.java.md
[main/java/com/ohnosequences/xml/model/Overlap.java]: ../../model/Overlap.java.md
[main/java/com/ohnosequences/xml/model/HspSet.java]: ../../model/HspSet.java.md
[main/java/com/ohnosequences/xml/model/bio4j/UniprotDataXML.java]: ../../model/bio4j/UniprotDataXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jPropertyXML.java]: ../../model/bio4j/Bio4jPropertyXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jRelationshipIndexXML.java]: ../../model/bio4j/Bio4jRelationshipIndexXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jNodeXML.java]: ../../model/bio4j/Bio4jNodeXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jNodeIndexXML.java]: ../../model/bio4j/Bio4jNodeIndexXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jRelationshipXML.java]: ../../model/bio4j/Bio4jRelationshipXML.java.md
[main/java/com/ohnosequences/xml/model/PredictedGenes.java]: ../../model/PredictedGenes.java.md
[main/java/com/ohnosequences/xml/model/ncbi/NCBITaxonomyNodeXML.java]: ../../model/ncbi/NCBITaxonomyNodeXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/GraphmlXML.java]: ../../model/graphml/GraphmlXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/GraphXML.java]: ../../model/graphml/GraphXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/KeyXML.java]: ../../model/graphml/KeyXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/NodeXML.java]: ../../model/graphml/NodeXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/EdgeXML.java]: ../../model/graphml/EdgeXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/DataXML.java]: ../../model/graphml/DataXML.java.md
[main/java/com/ohnosequences/xml/model/Codon.java]: ../../model/Codon.java.md
[main/java/com/ohnosequences/util/fasta/FastaUtil.java]: ../../../util/fasta/FastaUtil.java.md
[main/java/com/ohnosequences/util/file/GenomeFilesParser.java]: ../../../util/file/GenomeFilesParser.java.md
[main/java/com/ohnosequences/util/file/FnaFileFilter.java]: ../../../util/file/FnaFileFilter.java.md
[main/java/com/ohnosequences/util/file/RntFileFilter.java]: ../../../util/file/RntFileFilter.java.md
[main/java/com/ohnosequences/util/file/PttFileFilter.java]: ../../../util/file/PttFileFilter.java.md
[main/java/com/ohnosequences/util/file/FileUtil.java]: ../../../util/file/FileUtil.java.md
[main/java/com/ohnosequences/util/statistics/StatisticalValues.java]: ../../../util/statistics/StatisticalValues.java.md
[main/java/com/ohnosequences/util/Pair.java]: ../../../util/Pair.java.md
[main/java/com/ohnosequences/util/pal/PalindromicityAnalyzer.java]: ../../../util/pal/PalindromicityAnalyzer.java.md
[main/java/com/ohnosequences/util/Entry.java]: ../../../util/Entry.java.md
[main/java/com/ohnosequences/util/go/GOExporter.java]: ../../../util/go/GOExporter.java.md
[main/java/com/ohnosequences/util/uniprot/UniprotProteinRetreiver.java]: ../../../util/uniprot/UniprotProteinRetreiver.java.md
[main/java/com/ohnosequences/util/oric/OricDataRetriever.java]: ../../../util/oric/OricDataRetriever.java.md
[main/java/com/ohnosequences/util/blast/BlastSubset.java]: ../../../util/blast/BlastSubset.java.md
[main/java/com/ohnosequences/util/blast/BlastExporter.java]: ../../../util/blast/BlastExporter.java.md
[main/java/com/ohnosequences/util/model/PalindromicityResult.java]: ../../../util/model/PalindromicityResult.java.md
[main/java/com/ohnosequences/util/model/Intergenic.java]: ../../../util/model/Intergenic.java.md
[main/java/com/ohnosequences/util/model/Feature.java]: ../../../util/model/Feature.java.md
[main/java/com/ohnosequences/util/genbank/GBCommon.java]: ../../../util/genbank/GBCommon.java.md
[main/java/com/ohnosequences/util/CodonUtil.java]: ../../../util/CodonUtil.java.md
[main/java/com/ohnosequences/util/security/MD5.java]: ../../../util/security/MD5.java.md
[main/java/com/ohnosequences/util/Executable.java]: ../../../util/Executable.java.md
[main/java/com/ohnosequences/util/ExecuteFromFile.java]: ../../../util/ExecuteFromFile.java.md
[main/java/com/ohnosequences/util/seq/SeqUtil.java]: ../../../util/seq/SeqUtil.java.md
[main/java/com/ohnosequences/util/BitOperations.java]: ../../../util/BitOperations.java.md
[main/java/com/ohnosequences/util/ncbi/TaxonomyLoader.java]: ../../../util/ncbi/TaxonomyLoader.java.md
[main/java/com/ohnosequences/util/gephi/GephiExporter.java]: ../../../util/gephi/GephiExporter.java.md
[main/java/com/ohnosequences/util/gephi/GexfToDotExporter.java]: ../../../util/gephi/GexfToDotExporter.java.md
[main/java/com/ohnosequences/aws/util/AvailabilityZones.java]: ../../../aws/util/AvailabilityZones.java.md
[main/java/com/ohnosequences/aws/util/CredentialsRetriever.java]: ../../../aws/util/CredentialsRetriever.java.md
[main/java/com/ohnosequences/aws/util/Endpoints.java]: ../../../aws/util/Endpoints.java.md
[main/java/com/ohnosequences/aws/util/InstanceTypes.java]: ../../../aws/util/InstanceTypes.java.md
[main/java/com/ohnosequences/aws/util/AMITypes.java]: ../../../aws/util/AMITypes.java.md
[main/java/com/ohnosequences/aws/s3/S3FileUploader.java]: ../../../aws/s3/S3FileUploader.java.md
[main/java/com/ohnosequences/aws/s3/S3FileDownloader.java]: ../../../aws/s3/S3FileDownloader.java.md
[main/java/com/ohnosequences/aws/ec2/SpotUtil.java]: ../../../aws/ec2/SpotUtil.java.md
[main/java/com/ohnosequences/aws/ec2/InstanceUtil.java]: ../../../aws/ec2/InstanceUtil.java.md