
 * Copyright (C) 2010-2012  "Oh no sequences!"
 *
 * This is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>


```java
package com.ohnosequences.xml.model.bio4j;

import com.ohnosequences.xml.api.model.XMLElement;
import com.ohnosequences.xml.api.model.XMLElementException;
import org.jdom2.Element;

/**
 *
 * @author <a href="mailto:ppareja@ohnosequences.com">Pablo Pareja-Tobes</a>
 */
public class UniprotDataXML extends XMLElement{
    
    public static final String TAG_NAME = "uniprot_data";
    
    public static final String KEYWORDS_TAG_NAME = "keywords";
    public static final String INTERPRO_TAG_NAME = "interpro";
    public static final String PFAM_TAG_NAME = "pfam";
    public static final String CITATIONS_TAG_NAME = "citations";
    public static final String ARTICLES_TAG_NAME = "articles";
    public static final String PATENT_TAG_NAME = "patent";
    public static final String ONLINE_ARTICLES_TAG_NAME = "online_articles";
    public static final String THESIS_TAG_NAME = "thesis";
    public static final String BOOKS_TAG_NAME = "books";
    public static final String SUBMISSIONS_TAG_NAME = "submissions";
    public static final String UNPUBLISHED_OBSERVATIONS_TAG_NAME = "unpublished_observations";
    public static final String COMMENTS_TAG_NAME = "comments";
    public static final String FEATURES_TAG_NAME = "features";
    public static final String REACTOME_TAG_NAME = "reactome";
    public static final String ISOFORMS_TAG_NAME = "isoforms";
    public static final String SUBCELLULAR_LOCATIONS_TAG_NAME = "subcellular_locations";
    public static final String ENZYME_DB_TAG_NAME = "enzyme_db";
    public static final String GENE_ONTOLOGY_TAG_NAME = "gene_ontology";
    public static final String REFSEQ_TAG_NAME = "refseq";
    
    public UniprotDataXML(){
        super(new Element(TAG_NAME));
    }
    public UniprotDataXML(Element elem) throws XMLElementException{
        super(elem);
        if(!elem.getName().equals(TAG_NAME)){
            throw new XMLElementException(XMLElementException.WRONG_TAG_NAME,new XMLElement(elem));
        }
    }
    public UniprotDataXML(String value) throws Exception{
        super(value);
        if(!root.getName().equals(TAG_NAME)){
            throw new XMLElementException(XMLElementException.WRONG_TAG_NAME,new XMLElement(value));
        }
    }
        
    //----------------GETTERS---------------------
    public boolean getKeywords( ){  return Boolean.parseBoolean(getNodeText(KEYWORDS_TAG_NAME));}
    public boolean getInterpro( ){  return Boolean.parseBoolean(getNodeText(INTERPRO_TAG_NAME));}
    public boolean getPfam( ){  return Boolean.parseBoolean(getNodeText(PFAM_TAG_NAME));}
    public boolean getCitations( ){  return Boolean.parseBoolean(getNodeText(CITATIONS_TAG_NAME));}
    public boolean getArticles( ){  return Boolean.parseBoolean(getNodeText(ARTICLES_TAG_NAME));}
    public boolean getOnlineArticles( ){  return Boolean.parseBoolean(getNodeText(ONLINE_ARTICLES_TAG_NAME));}
    public boolean getPatents(){    return Boolean.parseBoolean(getNodeText(PATENT_TAG_NAME));}
    public boolean getThesis( ){  return Boolean.parseBoolean(getNodeText(THESIS_TAG_NAME));}
    public boolean getBooks( ){  return Boolean.parseBoolean(getNodeText(BOOKS_TAG_NAME));}
    public boolean getUnpublishedObservations( ){  return Boolean.parseBoolean(getNodeText(UNPUBLISHED_OBSERVATIONS_TAG_NAME));}
    public boolean getSubmissions( ){  return Boolean.parseBoolean(getNodeText(SUBMISSIONS_TAG_NAME));}
    public boolean getComments( ){  return Boolean.parseBoolean(getNodeText(COMMENTS_TAG_NAME));}
    public boolean getFeatures( ){  return Boolean.parseBoolean(getNodeText(FEATURES_TAG_NAME));}
    public boolean getReactome( ){  return Boolean.parseBoolean(getNodeText(REACTOME_TAG_NAME));}
    public boolean getIsoforms( ){  return Boolean.parseBoolean(getNodeText(ISOFORMS_TAG_NAME));}
    public boolean getSubcellularLocations( ){  return Boolean.parseBoolean(getNodeText(SUBCELLULAR_LOCATIONS_TAG_NAME));}
    public boolean getEnzymeDb( ){  return Boolean.parseBoolean(getNodeText(ENZYME_DB_TAG_NAME));}
    public boolean getGeneOntology( ){  return Boolean.parseBoolean(getNodeText(GENE_ONTOLOGY_TAG_NAME));}
    public boolean getRefseq( ){  return Boolean.parseBoolean(getNodeText(REFSEQ_TAG_NAME));}
    
    
    //----------------SETTERS-------------------
    public void setKeywords(boolean value){  setNodeText(KEYWORDS_TAG_NAME, String.valueOf(value));}
    public void setInterpro(boolean value){  setNodeText(INTERPRO_TAG_NAME, String.valueOf(value));}
    public void setPfam(boolean value){  setNodeText(PFAM_TAG_NAME, String.valueOf(value));}
    public void setCitations(boolean value){  setNodeText(CITATIONS_TAG_NAME, String.valueOf(value));}
    public void setArticless(boolean value){  setNodeText(ARTICLES_TAG_NAME, String.valueOf(value));}
    public void setOnlineArticles(boolean value){  setNodeText(ONLINE_ARTICLES_TAG_NAME, String.valueOf(value));}
    public void setPatents(boolean value){  setNodeText(PATENT_TAG_NAME, String.valueOf(value));}
    public void setThesis(boolean value){  setNodeText(THESIS_TAG_NAME, String.valueOf(value));}
    public void setBooks(boolean value){  setNodeText(BOOKS_TAG_NAME, String.valueOf(value));}
    public void setUnpublishedObservations(boolean value){  setNodeText(UNPUBLISHED_OBSERVATIONS_TAG_NAME, String.valueOf(value));}
    public void setSubmissions(boolean value){  setNodeText(SUBMISSIONS_TAG_NAME, String.valueOf(value));}
    public void setComments(boolean value){  setNodeText(COMMENTS_TAG_NAME, String.valueOf(value));}
    public void setFeatures(boolean value){  setNodeText(FEATURES_TAG_NAME, String.valueOf(value));}
    public void setReactome(boolean value){  setNodeText(REACTOME_TAG_NAME, String.valueOf(value));}
    public void setIsoforms(boolean value){  setNodeText(ISOFORMS_TAG_NAME, String.valueOf(value));}
    public void setSubcellularLocations(boolean value){  setNodeText(SUBCELLULAR_LOCATIONS_TAG_NAME, String.valueOf(value));}
    public void setEnzymeDb(boolean value){  setNodeText(ENZYME_DB_TAG_NAME, String.valueOf(value));}
    public void setGeneOntology(boolean value){  setNodeText(GENE_ONTOLOGY_TAG_NAME, String.valueOf(value));}
    public void setRefseq(boolean value){  setNodeText(REFSEQ_TAG_NAME, String.valueOf(value));}
    
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
[main/java/com/ohnosequences/xml/api/interfaces/IAttribute.java]: ../../api/interfaces/IAttribute.java.md
[main/java/com/ohnosequences/xml/api/interfaces/IElement.java]: ../../api/interfaces/IElement.java.md
[main/java/com/ohnosequences/xml/api/interfaces/INameSpace.java]: ../../api/interfaces/INameSpace.java.md
[main/java/com/ohnosequences/xml/api/interfaces/IXmlThing.java]: ../../api/interfaces/IXmlThing.java.md
[main/java/com/ohnosequences/xml/api/interfaces/package-info.java]: ../../api/interfaces/package-info.java.md
[main/java/com/ohnosequences/xml/api/model/NameSpace.java]: ../../api/model/NameSpace.java.md
[main/java/com/ohnosequences/xml/api/model/package-info.java]: ../../api/model/package-info.java.md
[main/java/com/ohnosequences/xml/api/model/XMLAttribute.java]: ../../api/model/XMLAttribute.java.md
[main/java/com/ohnosequences/xml/api/model/XMLElement.java]: ../../api/model/XMLElement.java.md
[main/java/com/ohnosequences/xml/api/model/XMLElementException.java]: ../../api/model/XMLElementException.java.md
[main/java/com/ohnosequences/xml/api/util/XMLUtil.java]: ../../api/util/XMLUtil.java.md
[main/java/com/ohnosequences/xml/model/Annotation.java]: ../Annotation.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jNodeIndexXML.java]: Bio4jNodeIndexXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jNodeXML.java]: Bio4jNodeXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jPropertyXML.java]: Bio4jPropertyXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jRelationshipIndexXML.java]: Bio4jRelationshipIndexXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jRelationshipXML.java]: Bio4jRelationshipXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/UniprotDataXML.java]: UniprotDataXML.java.md
[main/java/com/ohnosequences/xml/model/BlastOutput.java]: ../BlastOutput.java.md
[main/java/com/ohnosequences/xml/model/BlastOutputParam.java]: ../BlastOutputParam.java.md
[main/java/com/ohnosequences/xml/model/Codon.java]: ../Codon.java.md
[main/java/com/ohnosequences/xml/model/ContigXML.java]: ../ContigXML.java.md
[main/java/com/ohnosequences/xml/model/cufflinks/CuffLinksElement.java]: ../cufflinks/CuffLinksElement.java.md
[main/java/com/ohnosequences/xml/model/embl/EmblXML.java]: ../embl/EmblXML.java.md
[main/java/com/ohnosequences/xml/model/Frameshift.java]: ../Frameshift.java.md
[main/java/com/ohnosequences/xml/model/Gap.java]: ../Gap.java.md
[main/java/com/ohnosequences/xml/model/gb/GenBankXML.java]: ../gb/GenBankXML.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/Feature.java]: ../genome/feature/Feature.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/Intergenic.java]: ../genome/feature/Intergenic.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/MisRNA.java]: ../genome/feature/MisRNA.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/ORF.java]: ../genome/feature/ORF.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/RNA.java]: ../genome/feature/RNA.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/RRNA.java]: ../genome/feature/RRNA.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/TRNA.java]: ../genome/feature/TRNA.java.md
[main/java/com/ohnosequences/xml/model/genome/GenomeElement.java]: ../genome/GenomeElement.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttributesXML.java]: ../gexf/AttributesXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttributeXML.java]: ../gexf/AttributeXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttValuesXML.java]: ../gexf/AttValuesXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttValueXML.java]: ../gexf/AttValueXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/EdgesXML.java]: ../gexf/EdgesXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/EdgeXML.java]: ../gexf/EdgeXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/GexfXML.java]: ../gexf/GexfXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/GraphXML.java]: ../gexf/GraphXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/NodesXML.java]: ../gexf/NodesXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/NodeXML.java]: ../gexf/NodeXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/SpellsXML.java]: ../gexf/SpellsXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/SpellXML.java]: ../gexf/SpellXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/viz/VizColorXML.java]: ../gexf/viz/VizColorXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/viz/VizPositionXML.java]: ../gexf/viz/VizPositionXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/viz/VizSizeXML.java]: ../gexf/viz/VizSizeXML.java.md
[main/java/com/ohnosequences/xml/model/go/GoAnnotationXML.java]: ../go/GoAnnotationXML.java.md
[main/java/com/ohnosequences/xml/model/go/GOSlimXML.java]: ../go/GOSlimXML.java.md
[main/java/com/ohnosequences/xml/model/go/GoTermXML.java]: ../go/GoTermXML.java.md
[main/java/com/ohnosequences/xml/model/go/SlimSetXML.java]: ../go/SlimSetXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/DataXML.java]: ../graphml/DataXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/EdgeXML.java]: ../graphml/EdgeXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/GraphmlXML.java]: ../graphml/GraphmlXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/GraphXML.java]: ../graphml/GraphXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/KeyXML.java]: ../graphml/KeyXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/NodeXML.java]: ../graphml/NodeXML.java.md
[main/java/com/ohnosequences/xml/model/Hit.java]: ../Hit.java.md
[main/java/com/ohnosequences/xml/model/Hsp.java]: ../Hsp.java.md
[main/java/com/ohnosequences/xml/model/HspSet.java]: ../HspSet.java.md
[main/java/com/ohnosequences/xml/model/Iteration.java]: ../Iteration.java.md
[main/java/com/ohnosequences/xml/model/logs/LogRecordXML.java]: ../logs/LogRecordXML.java.md
[main/java/com/ohnosequences/xml/model/metagenomics/ReadResultXML.java]: ../metagenomics/ReadResultXML.java.md
[main/java/com/ohnosequences/xml/model/metagenomics/ReadXML.java]: ../metagenomics/ReadXML.java.md
[main/java/com/ohnosequences/xml/model/metagenomics/SampleXML.java]: ../metagenomics/SampleXML.java.md
[main/java/com/ohnosequences/xml/model/MetagenomicsDataXML.java]: ../MetagenomicsDataXML.java.md
[main/java/com/ohnosequences/xml/model/mg7/MG7DataXML.java]: ../mg7/MG7DataXML.java.md
[main/java/com/ohnosequences/xml/model/mg7/ReadResultXML.java]: ../mg7/ReadResultXML.java.md
[main/java/com/ohnosequences/xml/model/mg7/SampleXML.java]: ../mg7/SampleXML.java.md
[main/java/com/ohnosequences/xml/model/ncbi/NCBITaxonomyNodeXML.java]: ../ncbi/NCBITaxonomyNodeXML.java.md
[main/java/com/ohnosequences/xml/model/oric/Oric.java]: ../oric/Oric.java.md
[main/java/com/ohnosequences/xml/model/Overlap.java]: ../Overlap.java.md
[main/java/com/ohnosequences/xml/model/pal/PalindromicityResultXML.java]: ../pal/PalindromicityResultXML.java.md
[main/java/com/ohnosequences/xml/model/pg/Primer.java]: ../pg/Primer.java.md
[main/java/com/ohnosequences/xml/model/PredictedGene.java]: ../PredictedGene.java.md
[main/java/com/ohnosequences/xml/model/PredictedGenes.java]: ../PredictedGenes.java.md
[main/java/com/ohnosequences/xml/model/PredictedRna.java]: ../PredictedRna.java.md
[main/java/com/ohnosequences/xml/model/PredictedRnas.java]: ../PredictedRnas.java.md
[main/java/com/ohnosequences/xml/model/uniprot/ArticleXML.java]: ../uniprot/ArticleXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/CommentXML.java]: ../uniprot/CommentXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/FeatureXML.java]: ../uniprot/FeatureXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/InterproXML.java]: ../uniprot/InterproXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/IsoformXML.java]: ../uniprot/IsoformXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/KeywordXML.java]: ../uniprot/KeywordXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/ProteinXML.java]: ../uniprot/ProteinXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/SubcellularLocationXML.java]: ../uniprot/SubcellularLocationXML.java.md
[main/java/com/ohnosequences/xml/model/util/Argument.java]: ../util/Argument.java.md
[main/java/com/ohnosequences/xml/model/util/Arguments.java]: ../util/Arguments.java.md
[main/java/com/ohnosequences/xml/model/util/Error.java]: ../util/Error.java.md
[main/java/com/ohnosequences/xml/model/util/Execution.java]: ../util/Execution.java.md
[main/java/com/ohnosequences/xml/model/util/FlexXMLWrapperClassCreator.java]: ../util/FlexXMLWrapperClassCreator.java.md
[main/java/com/ohnosequences/xml/model/util/ScheduledExecutions.java]: ../util/ScheduledExecutions.java.md
[main/java/com/ohnosequences/xml/model/util/XMLWrapperClass.java]: ../util/XMLWrapperClass.java.md
[main/java/com/ohnosequences/xml/model/util/XMLWrapperClassCreator.java]: ../util/XMLWrapperClassCreator.java.md
[main/java/com/ohnosequences/xml/model/wip/Region.java]: ../wip/Region.java.md
[main/java/com/ohnosequences/xml/model/wip/WipPosition.java]: ../wip/WipPosition.java.md
[main/java/com/ohnosequences/xml/model/wip/WipResult.java]: ../wip/WipResult.java.md