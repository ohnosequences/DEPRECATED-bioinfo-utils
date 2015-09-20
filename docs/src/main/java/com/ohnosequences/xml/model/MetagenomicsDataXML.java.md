
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
package com.ohnosequences.xml.model;

import com.ohnosequences.xml.api.model.XMLElement;
import com.ohnosequences.xml.api.model.XMLElementException;
import org.jdom2.Element;

/**
 *
 * @author <a href="mailto:ppareja@ohnosequences.com">Pablo Pareja-Tobes</a>
 */
public class MetagenomicsDataXML extends XMLElement{

    public static final String TAG_NAME = "metagenomica_data";
    
    public static final String QUEUE_NAME_TAG_NAME = "queue_name";
    public static final String READS_PER_FILE_TAG_NAME = "reads_per_file";
    public static final String READ_PACKAGES_BUCKET_FOLDER_TAG_NAME = "read_packages_bucket_folder";
    public static final String BUCKET_TAG_NAME = "bucket";
    public static final String READS_BUCKET_FOLDER_TAG_NAME = "reads_bucket_folder";
    public static final String RESULTS_BUCKET_FOLDER_TAG_NAME = "results_bucket_folder";
    public static final String ERRORS_BUCKET_FOLDER_TAG_NAME = "errors_bucket_folder";
    
    public static final String INSTANCES_IDLE_TIME_IN_SECONDS_TAG_NAME = "instances_idle_time_in_seconds";
    public static final String INSTANCE_TYPE_TAG_NAME = "instance_type";
    
    public static final String BASE_FOLDER_TAG_NAME = "base_folder";
    public static final String BLAST_PATH_TAG_NAME = "blast_path";
    
    public static final String MD5_FILE_SUFFIX_TAG_NAME = "md5_file_suffix";
    public static final String ERROR_FILE_SUFFIX_TAG_NAME = "error_file_suffix";
    
    public static final String PROJECT_PREFIX_TAG_NAME = "project_prefix";
    
    public static final String SNAPSHOT_ID_TAG_NAME = "snapshot_id";
    public static final String VOLUME_SIZE_IN_GB_TAG_NAME = "volume_size_in_gb";
    public static final String VOLUME_DEVICE_TAG_NAME = "volume_device";
    
    public static final String MOUNTING_DIR_TAG_NAME = "mounting_dir";
    public static final String KEY_PAIR_NAME_TAG_NAME = "key_pair_name";
    
    public static final String NUMBER_OF_CONCURRENT_INSTANCES_TAG_NAME = "number_of_concurrent_instances";
    public static final String SPOT_INSTANCE_PRICE_TAG_NAME = "spot_instance_price";
    
    public static final String USER_DATA_FILE_TAG_NAME = "user_data_file";
    
    public static final String ERRORS_SNS_TOPIC_NAME_TAG_NAME = "errors_sns_topic_name";
    public static final String ERRORS_SNS_TOPIC_ARN_TAG_NAME = "errors_sns_topic_arn";
        
    public static final String RESULTS_DB_FOLDER = "results_db_folder";
    public static final String MLM_CONFIG_PROPS_TAG_NAME = "mlm_config_props";
    
    public MetagenomicsDataXML(){
        super(new Element(TAG_NAME));
    }
    public MetagenomicsDataXML(Element elem) throws XMLElementException{
        super(elem);
        if(!elem.getName().equals(TAG_NAME)){
            throw new XMLElementException(XMLElementException.WRONG_TAG_NAME,new XMLElement(elem));
        }
    }
    public MetagenomicsDataXML(String value) throws Exception{
        super(value);
        if(!root.getName().equals(TAG_NAME)){
            throw new XMLElementException(XMLElementException.WRONG_TAG_NAME,new XMLElement(value));
        }
    }
    
    //----------------SETTERS-------------------
    public void setQueueName(String value){    setNodeText(QUEUE_NAME_TAG_NAME, value);}
    public void setReadsPerFile(String value){  setNodeText(READS_PER_FILE_TAG_NAME, value);}
    public void setReadPackagesBucketFolder(String value){    setNodeText(READ_PACKAGES_BUCKET_FOLDER_TAG_NAME, value);}
    public void setBucket(String value){    setNodeText(BUCKET_TAG_NAME, value);}
    public void setReadsBucketFolder(String value){ setNodeText(READS_BUCKET_FOLDER_TAG_NAME, value);}
    public void setResultsBucketFolder(String value){   setNodeText(RESULTS_BUCKET_FOLDER_TAG_NAME, value);}
    public void setErrorsBucketFolder(String value){   setNodeText(ERRORS_BUCKET_FOLDER_TAG_NAME, value);}
    public void setInstancesIdleTimeInSeconds(String value){    setNodeText(INSTANCES_IDLE_TIME_IN_SECONDS_TAG_NAME, value);}
    public void setInstanceType(String value){  setNodeText(INSTANCE_TYPE_TAG_NAME, value);}
    public void setBaseFolder(String value){    setNodeText(BASE_FOLDER_TAG_NAME, value);}
    public void setBlastPath(String value){ setNodeText(BLAST_PATH_TAG_NAME, value);}
    public void setMD5FileSufix(String value){  setNodeText(MD5_FILE_SUFFIX_TAG_NAME, value);}
    public void setErrorFileSufix(String value){    setNodeText(ERROR_FILE_SUFFIX_TAG_NAME, value);}
    public void setProjectPrefix(String value){ setNodeText(PROJECT_PREFIX_TAG_NAME, value);}
    public void setSnapshotId(String value){ setNodeText(SNAPSHOT_ID_TAG_NAME, value);}
    public void setVolumeSizeInGB(String value){ setNodeText(VOLUME_SIZE_IN_GB_TAG_NAME, value);}
    public void setVolumeDevice(String value){ setNodeText(VOLUME_DEVICE_TAG_NAME, value);}
    public void setMountingDir(String value){ setNodeText(MOUNTING_DIR_TAG_NAME, value);}
    public void setKeyPairName(String value){ setNodeText(KEY_PAIR_NAME_TAG_NAME, value);}
    public void setNumberOfConcurrentInstances(String value){ setNodeText(NUMBER_OF_CONCURRENT_INSTANCES_TAG_NAME, value);}
    public void setSpotInstancePrice(String value){ setNodeText(SPOT_INSTANCE_PRICE_TAG_NAME, value);}
    public void setUserDataFile(String value){  setNodeText(USER_DATA_FILE_TAG_NAME, value);}
    public void setErrorsSNSTopicName(String value){    setNodeText(ERRORS_SNS_TOPIC_NAME_TAG_NAME, value);}
    public void setErrorsSNSTopicArn(String value){ setNodeText(ERRORS_SNS_TOPIC_ARN_TAG_NAME, value);}
    public void setResultsDBFolder(String value){   setNodeText(RESULTS_DB_FOLDER, value);}
    public void setMLMConfigProps(String value){   setNodeText(MLM_CONFIG_PROPS_TAG_NAME, value);}
    
    //----------------GETTERS---------------------
    public String getQueueName(){       return getNodeText(QUEUE_NAME_TAG_NAME);  }
    public int getReadsPerFile(){    return Integer.parseInt(getNodeText(READS_PER_FILE_TAG_NAME)); }
    public String getReadPackagesBucketFolder(){  return getNodeText(READ_PACKAGES_BUCKET_FOLDER_TAG_NAME);}
    public String getBucket(){  return getNodeText(BUCKET_TAG_NAME);}
    public String getResultsBucketFolder(){ return getNodeText(RESULTS_BUCKET_FOLDER_TAG_NAME);}
    public String getErrorsBucketFolder(){  return getNodeText(ERRORS_BUCKET_FOLDER_TAG_NAME);}
    public String getReadsBucketFolder(){   return getNodeText(READS_BUCKET_FOLDER_TAG_NAME);}
    public int getInstancesIdleTimeInSeconds(){  return Integer.parseInt(getNodeText(INSTANCES_IDLE_TIME_IN_SECONDS_TAG_NAME));}
    public String getInstanceType(){    return getNodeText(INSTANCE_TYPE_TAG_NAME);}
    public String getBaseFolder(){  return getNodeText(BASE_FOLDER_TAG_NAME);}
    public String getBlastPath(){   return getNodeText(BLAST_PATH_TAG_NAME);}
    public String getMD5FileSufix(){    return getNodeText(MD5_FILE_SUFFIX_TAG_NAME);}
    public String getProjectPrefix(){   return getNodeText(PROJECT_PREFIX_TAG_NAME);}
    public String getErroFileSufix(){   return getNodeText(ERROR_FILE_SUFFIX_TAG_NAME);}
    public String getSnapshotId(){   return getNodeText(SNAPSHOT_ID_TAG_NAME);}
    public int getVolumeSizeInGB(){   return Integer.parseInt(getNodeText(VOLUME_SIZE_IN_GB_TAG_NAME));}
    public String getVolumeDevice(){   return getNodeText(VOLUME_DEVICE_TAG_NAME);}
    public String getMountingDir(){   return getNodeText(MOUNTING_DIR_TAG_NAME);}
    public String getKeyPairName(){   return getNodeText(KEY_PAIR_NAME_TAG_NAME);}
    public int getNumberOfConcurrentInstances(){   return Integer.parseInt(getNodeText(NUMBER_OF_CONCURRENT_INSTANCES_TAG_NAME));}
    public String getSpotInstancePrice(){   return getNodeText(SPOT_INSTANCE_PRICE_TAG_NAME);}
    public String getUserDataFile(){    return getNodeText(USER_DATA_FILE_TAG_NAME);}
    public String getErrorsSNSTopicName(){  return getNodeText(ERRORS_SNS_TOPIC_NAME_TAG_NAME);}
    public String getErrorsSNSTopicArn(){  return getNodeText(ERRORS_SNS_TOPIC_ARN_TAG_NAME);}
    public String getResultsDBFolder(){ return getNodeText(RESULTS_DB_FOLDER);}
    public String getMLMConfigProps(){ return getNodeText(MLM_CONFIG_PROPS_TAG_NAME);}
    
}

```




[main/java/com/ohnosequences/BioinfoUtil.java]: ../../BioinfoUtil.java.md
[main/java/com/ohnosequences/util/BitOperations.java]: ../../util/BitOperations.java.md
[main/java/com/ohnosequences/util/blast/BlastExporter.java]: ../../util/blast/BlastExporter.java.md
[main/java/com/ohnosequences/util/blast/BlastSubset.java]: ../../util/blast/BlastSubset.java.md
[main/java/com/ohnosequences/util/CodonUtil.java]: ../../util/CodonUtil.java.md
[main/java/com/ohnosequences/util/Entry.java]: ../../util/Entry.java.md
[main/java/com/ohnosequences/util/Executable.java]: ../../util/Executable.java.md
[main/java/com/ohnosequences/util/ExecuteFromFile.java]: ../../util/ExecuteFromFile.java.md
[main/java/com/ohnosequences/util/fasta/FastaSubSeq.java]: ../../util/fasta/FastaSubSeq.java.md
[main/java/com/ohnosequences/util/fasta/FastaUtil.java]: ../../util/fasta/FastaUtil.java.md
[main/java/com/ohnosequences/util/fasta/MultifastaSelector.java]: ../../util/fasta/MultifastaSelector.java.md
[main/java/com/ohnosequences/util/fasta/SearchFastaHeaders.java]: ../../util/fasta/SearchFastaHeaders.java.md
[main/java/com/ohnosequences/util/fasta/SearchFastaSequence.java]: ../../util/fasta/SearchFastaSequence.java.md
[main/java/com/ohnosequences/util/file/FileUtil.java]: ../../util/file/FileUtil.java.md
[main/java/com/ohnosequences/util/file/FnaFileFilter.java]: ../../util/file/FnaFileFilter.java.md
[main/java/com/ohnosequences/util/file/GenomeFilesParser.java]: ../../util/file/GenomeFilesParser.java.md
[main/java/com/ohnosequences/util/file/PttFileFilter.java]: ../../util/file/PttFileFilter.java.md
[main/java/com/ohnosequences/util/file/RntFileFilter.java]: ../../util/file/RntFileFilter.java.md
[main/java/com/ohnosequences/util/genbank/GBCommon.java]: ../../util/genbank/GBCommon.java.md
[main/java/com/ohnosequences/util/gephi/GephiExporter.java]: ../../util/gephi/GephiExporter.java.md
[main/java/com/ohnosequences/util/gephi/GexfToDotExporter.java]: ../../util/gephi/GexfToDotExporter.java.md
[main/java/com/ohnosequences/util/go/GOExporter.java]: ../../util/go/GOExporter.java.md
[main/java/com/ohnosequences/util/model/Feature.java]: ../../util/model/Feature.java.md
[main/java/com/ohnosequences/util/model/Intergenic.java]: ../../util/model/Intergenic.java.md
[main/java/com/ohnosequences/util/model/PalindromicityResult.java]: ../../util/model/PalindromicityResult.java.md
[main/java/com/ohnosequences/util/ncbi/TaxonomyLoader.java]: ../../util/ncbi/TaxonomyLoader.java.md
[main/java/com/ohnosequences/util/oric/OricDataRetriever.java]: ../../util/oric/OricDataRetriever.java.md
[main/java/com/ohnosequences/util/Pair.java]: ../../util/Pair.java.md
[main/java/com/ohnosequences/util/pal/PalindromicityAnalyzer.java]: ../../util/pal/PalindromicityAnalyzer.java.md
[main/java/com/ohnosequences/util/security/MD5.java]: ../../util/security/MD5.java.md
[main/java/com/ohnosequences/util/seq/SeqUtil.java]: ../../util/seq/SeqUtil.java.md
[main/java/com/ohnosequences/util/statistics/StatisticalValues.java]: ../../util/statistics/StatisticalValues.java.md
[main/java/com/ohnosequences/util/uniprot/UniprotProteinRetreiver.java]: ../../util/uniprot/UniprotProteinRetreiver.java.md
[main/java/com/ohnosequences/xml/api/interfaces/IAttribute.java]: ../api/interfaces/IAttribute.java.md
[main/java/com/ohnosequences/xml/api/interfaces/IElement.java]: ../api/interfaces/IElement.java.md
[main/java/com/ohnosequences/xml/api/interfaces/INameSpace.java]: ../api/interfaces/INameSpace.java.md
[main/java/com/ohnosequences/xml/api/interfaces/IXmlThing.java]: ../api/interfaces/IXmlThing.java.md
[main/java/com/ohnosequences/xml/api/interfaces/package-info.java]: ../api/interfaces/package-info.java.md
[main/java/com/ohnosequences/xml/api/model/NameSpace.java]: ../api/model/NameSpace.java.md
[main/java/com/ohnosequences/xml/api/model/package-info.java]: ../api/model/package-info.java.md
[main/java/com/ohnosequences/xml/api/model/XMLAttribute.java]: ../api/model/XMLAttribute.java.md
[main/java/com/ohnosequences/xml/api/model/XMLElement.java]: ../api/model/XMLElement.java.md
[main/java/com/ohnosequences/xml/api/model/XMLElementException.java]: ../api/model/XMLElementException.java.md
[main/java/com/ohnosequences/xml/api/util/XMLUtil.java]: ../api/util/XMLUtil.java.md
[main/java/com/ohnosequences/xml/model/Annotation.java]: Annotation.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jNodeIndexXML.java]: bio4j/Bio4jNodeIndexXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jNodeXML.java]: bio4j/Bio4jNodeXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jPropertyXML.java]: bio4j/Bio4jPropertyXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jRelationshipIndexXML.java]: bio4j/Bio4jRelationshipIndexXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jRelationshipXML.java]: bio4j/Bio4jRelationshipXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/UniprotDataXML.java]: bio4j/UniprotDataXML.java.md
[main/java/com/ohnosequences/xml/model/BlastOutput.java]: BlastOutput.java.md
[main/java/com/ohnosequences/xml/model/BlastOutputParam.java]: BlastOutputParam.java.md
[main/java/com/ohnosequences/xml/model/Codon.java]: Codon.java.md
[main/java/com/ohnosequences/xml/model/ContigXML.java]: ContigXML.java.md
[main/java/com/ohnosequences/xml/model/cufflinks/CuffLinksElement.java]: cufflinks/CuffLinksElement.java.md
[main/java/com/ohnosequences/xml/model/embl/EmblXML.java]: embl/EmblXML.java.md
[main/java/com/ohnosequences/xml/model/Frameshift.java]: Frameshift.java.md
[main/java/com/ohnosequences/xml/model/Gap.java]: Gap.java.md
[main/java/com/ohnosequences/xml/model/gb/GenBankXML.java]: gb/GenBankXML.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/Feature.java]: genome/feature/Feature.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/Intergenic.java]: genome/feature/Intergenic.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/MisRNA.java]: genome/feature/MisRNA.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/ORF.java]: genome/feature/ORF.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/RNA.java]: genome/feature/RNA.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/RRNA.java]: genome/feature/RRNA.java.md
[main/java/com/ohnosequences/xml/model/genome/feature/TRNA.java]: genome/feature/TRNA.java.md
[main/java/com/ohnosequences/xml/model/genome/GenomeElement.java]: genome/GenomeElement.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttributesXML.java]: gexf/AttributesXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttributeXML.java]: gexf/AttributeXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttValuesXML.java]: gexf/AttValuesXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/AttValueXML.java]: gexf/AttValueXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/EdgesXML.java]: gexf/EdgesXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/EdgeXML.java]: gexf/EdgeXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/GexfXML.java]: gexf/GexfXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/GraphXML.java]: gexf/GraphXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/NodesXML.java]: gexf/NodesXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/NodeXML.java]: gexf/NodeXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/SpellsXML.java]: gexf/SpellsXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/SpellXML.java]: gexf/SpellXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/viz/VizColorXML.java]: gexf/viz/VizColorXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/viz/VizPositionXML.java]: gexf/viz/VizPositionXML.java.md
[main/java/com/ohnosequences/xml/model/gexf/viz/VizSizeXML.java]: gexf/viz/VizSizeXML.java.md
[main/java/com/ohnosequences/xml/model/go/GoAnnotationXML.java]: go/GoAnnotationXML.java.md
[main/java/com/ohnosequences/xml/model/go/GOSlimXML.java]: go/GOSlimXML.java.md
[main/java/com/ohnosequences/xml/model/go/GoTermXML.java]: go/GoTermXML.java.md
[main/java/com/ohnosequences/xml/model/go/SlimSetXML.java]: go/SlimSetXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/DataXML.java]: graphml/DataXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/EdgeXML.java]: graphml/EdgeXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/GraphmlXML.java]: graphml/GraphmlXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/GraphXML.java]: graphml/GraphXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/KeyXML.java]: graphml/KeyXML.java.md
[main/java/com/ohnosequences/xml/model/graphml/NodeXML.java]: graphml/NodeXML.java.md
[main/java/com/ohnosequences/xml/model/Hit.java]: Hit.java.md
[main/java/com/ohnosequences/xml/model/Hsp.java]: Hsp.java.md
[main/java/com/ohnosequences/xml/model/HspSet.java]: HspSet.java.md
[main/java/com/ohnosequences/xml/model/Iteration.java]: Iteration.java.md
[main/java/com/ohnosequences/xml/model/logs/LogRecordXML.java]: logs/LogRecordXML.java.md
[main/java/com/ohnosequences/xml/model/metagenomics/ReadResultXML.java]: metagenomics/ReadResultXML.java.md
[main/java/com/ohnosequences/xml/model/metagenomics/ReadXML.java]: metagenomics/ReadXML.java.md
[main/java/com/ohnosequences/xml/model/metagenomics/SampleXML.java]: metagenomics/SampleXML.java.md
[main/java/com/ohnosequences/xml/model/MetagenomicsDataXML.java]: MetagenomicsDataXML.java.md
[main/java/com/ohnosequences/xml/model/mg7/MG7DataXML.java]: mg7/MG7DataXML.java.md
[main/java/com/ohnosequences/xml/model/mg7/ReadResultXML.java]: mg7/ReadResultXML.java.md
[main/java/com/ohnosequences/xml/model/mg7/SampleXML.java]: mg7/SampleXML.java.md
[main/java/com/ohnosequences/xml/model/ncbi/NCBITaxonomyNodeXML.java]: ncbi/NCBITaxonomyNodeXML.java.md
[main/java/com/ohnosequences/xml/model/oric/Oric.java]: oric/Oric.java.md
[main/java/com/ohnosequences/xml/model/Overlap.java]: Overlap.java.md
[main/java/com/ohnosequences/xml/model/pal/PalindromicityResultXML.java]: pal/PalindromicityResultXML.java.md
[main/java/com/ohnosequences/xml/model/pg/Primer.java]: pg/Primer.java.md
[main/java/com/ohnosequences/xml/model/PredictedGene.java]: PredictedGene.java.md
[main/java/com/ohnosequences/xml/model/PredictedGenes.java]: PredictedGenes.java.md
[main/java/com/ohnosequences/xml/model/PredictedRna.java]: PredictedRna.java.md
[main/java/com/ohnosequences/xml/model/PredictedRnas.java]: PredictedRnas.java.md
[main/java/com/ohnosequences/xml/model/uniprot/ArticleXML.java]: uniprot/ArticleXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/CommentXML.java]: uniprot/CommentXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/FeatureXML.java]: uniprot/FeatureXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/InterproXML.java]: uniprot/InterproXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/IsoformXML.java]: uniprot/IsoformXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/KeywordXML.java]: uniprot/KeywordXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/ProteinXML.java]: uniprot/ProteinXML.java.md
[main/java/com/ohnosequences/xml/model/uniprot/SubcellularLocationXML.java]: uniprot/SubcellularLocationXML.java.md
[main/java/com/ohnosequences/xml/model/util/Argument.java]: util/Argument.java.md
[main/java/com/ohnosequences/xml/model/util/Arguments.java]: util/Arguments.java.md
[main/java/com/ohnosequences/xml/model/util/Error.java]: util/Error.java.md
[main/java/com/ohnosequences/xml/model/util/Execution.java]: util/Execution.java.md
[main/java/com/ohnosequences/xml/model/util/FlexXMLWrapperClassCreator.java]: util/FlexXMLWrapperClassCreator.java.md
[main/java/com/ohnosequences/xml/model/util/ScheduledExecutions.java]: util/ScheduledExecutions.java.md
[main/java/com/ohnosequences/xml/model/util/XMLWrapperClass.java]: util/XMLWrapperClass.java.md
[main/java/com/ohnosequences/xml/model/util/XMLWrapperClassCreator.java]: util/XMLWrapperClassCreator.java.md
[main/java/com/ohnosequences/xml/model/wip/Region.java]: wip/Region.java.md
[main/java/com/ohnosequences/xml/model/wip/WipPosition.java]: wip/WipPosition.java.md
[main/java/com/ohnosequences/xml/model/wip/WipResult.java]: wip/WipResult.java.md