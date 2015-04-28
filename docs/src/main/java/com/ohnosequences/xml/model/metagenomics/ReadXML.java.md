
 * To change this template, choose Tools | Templates
 * and open the template in the editor.


```java
package com.ohnosequences.xml.model.metagenomics;

import com.ohnosequences.xml.api.model.XMLElement;
import com.ohnosequences.xml.api.model.XMLElementException;
import org.jdom2.Element;

/**
 *
 * @author <a href="mailto:ppareja@ohnosequences.com">Pablo Pareja-Tobes</a>
 */
public class ReadXML extends XMLElement{
    
    public static final String TAG_NAME = "read";   

    public static final String ID_TAG_NAME = "id";
    public static final String SEQUENCE_TAG_NAME = "sequence";
    
    public ReadXML(){
        super(new Element(TAG_NAME));
    }
    public ReadXML(Element elem) throws XMLElementException{
        super(elem);
        if(!elem.getName().equals(TAG_NAME)){
            throw new XMLElementException(XMLElementException.WRONG_TAG_NAME,new XMLElement(elem));
        }
    }
    public ReadXML(String value) throws Exception{
        super(value);
        if(!root.getName().equals(TAG_NAME)){
            throw new XMLElementException(XMLElementException.WRONG_TAG_NAME,new XMLElement(value));
        }
    }
    
    //----------------GETTERS---------------------
    public String getId(){       return getNodeText(ID_TAG_NAME);  }
    public String getSequence(){       return getNodeText(SEQUENCE_TAG_NAME);  }

    //----------------SETTERS---------------------
    public void setId(String type){    setNodeText(ID_TAG_NAME, type);}
    public void setSequence(String type){    setNodeText(SEQUENCE_TAG_NAME, type);}
    
}

```


------

### Index

+ src
  + main
    + java
      + com
        + ohnosequences
          + aws
            + ec2
              + [InstanceUtil.java][main\java\com\ohnosequences\aws\ec2\InstanceUtil.java]
              + [SpotUtil.java][main\java\com\ohnosequences\aws\ec2\SpotUtil.java]
            + s3
              + [S3FileDownloader.java][main\java\com\ohnosequences\aws\s3\S3FileDownloader.java]
              + [S3FileUploader.java][main\java\com\ohnosequences\aws\s3\S3FileUploader.java]
            + util
              + [AMITypes.java][main\java\com\ohnosequences\aws\util\AMITypes.java]
              + [AvailabilityZones.java][main\java\com\ohnosequences\aws\util\AvailabilityZones.java]
              + [CredentialsRetriever.java][main\java\com\ohnosequences\aws\util\CredentialsRetriever.java]
              + [Endpoints.java][main\java\com\ohnosequences\aws\util\Endpoints.java]
              + [InstanceTypes.java][main\java\com\ohnosequences\aws\util\InstanceTypes.java]
          + [BioinfoUtil.java][main\java\com\ohnosequences\BioinfoUtil.java]
          + util
            + [BitOperations.java][main\java\com\ohnosequences\util\BitOperations.java]
            + blast
              + [BlastExporter.java][main\java\com\ohnosequences\util\blast\BlastExporter.java]
              + [BlastSubset.java][main\java\com\ohnosequences\util\blast\BlastSubset.java]
            + [CodonUtil.java][main\java\com\ohnosequences\util\CodonUtil.java]
            + [Entry.java][main\java\com\ohnosequences\util\Entry.java]
            + [Executable.java][main\java\com\ohnosequences\util\Executable.java]
            + [ExecuteFromFile.java][main\java\com\ohnosequences\util\ExecuteFromFile.java]
            + fasta
              + [FastaSubSeq.java][main\java\com\ohnosequences\util\fasta\FastaSubSeq.java]
              + [FastaUtil.java][main\java\com\ohnosequences\util\fasta\FastaUtil.java]
              + [MultifastaSelector.java][main\java\com\ohnosequences\util\fasta\MultifastaSelector.java]
              + [SearchFastaHeaders.java][main\java\com\ohnosequences\util\fasta\SearchFastaHeaders.java]
            + file
              + [FileUtil.java][main\java\com\ohnosequences\util\file\FileUtil.java]
              + [FnaFileFilter.java][main\java\com\ohnosequences\util\file\FnaFileFilter.java]
              + [GenomeFilesParser.java][main\java\com\ohnosequences\util\file\GenomeFilesParser.java]
              + [PttFileFilter.java][main\java\com\ohnosequences\util\file\PttFileFilter.java]
              + [RntFileFilter.java][main\java\com\ohnosequences\util\file\RntFileFilter.java]
            + genbank
              + [GBCommon.java][main\java\com\ohnosequences\util\genbank\GBCommon.java]
            + gephi
              + [GephiExporter.java][main\java\com\ohnosequences\util\gephi\GephiExporter.java]
              + [GexfToDotExporter.java][main\java\com\ohnosequences\util\gephi\GexfToDotExporter.java]
            + go
              + [GOExporter.java][main\java\com\ohnosequences\util\go\GOExporter.java]
            + model
              + [Feature.java][main\java\com\ohnosequences\util\model\Feature.java]
              + [Intergenic.java][main\java\com\ohnosequences\util\model\Intergenic.java]
              + [PalindromicityResult.java][main\java\com\ohnosequences\util\model\PalindromicityResult.java]
            + ncbi
              + [TaxonomyLoader.java][main\java\com\ohnosequences\util\ncbi\TaxonomyLoader.java]
            + oric
              + [OricDataRetriever.java][main\java\com\ohnosequences\util\oric\OricDataRetriever.java]
            + [Pair.java][main\java\com\ohnosequences\util\Pair.java]
            + pal
              + [PalindromicityAnalyzer.java][main\java\com\ohnosequences\util\pal\PalindromicityAnalyzer.java]
            + security
              + [MD5.java][main\java\com\ohnosequences\util\security\MD5.java]
            + seq
              + [SeqUtil.java][main\java\com\ohnosequences\util\seq\SeqUtil.java]
            + statistics
              + [StatisticalValues.java][main\java\com\ohnosequences\util\statistics\StatisticalValues.java]
            + uniprot
              + [UniprotProteinRetreiver.java][main\java\com\ohnosequences\util\uniprot\UniprotProteinRetreiver.java]
          + xml
            + api
              + interfaces
                + [IAttribute.java][main\java\com\ohnosequences\xml\api\interfaces\IAttribute.java]
                + [IElement.java][main\java\com\ohnosequences\xml\api\interfaces\IElement.java]
                + [INameSpace.java][main\java\com\ohnosequences\xml\api\interfaces\INameSpace.java]
                + [IXmlThing.java][main\java\com\ohnosequences\xml\api\interfaces\IXmlThing.java]
                + [package-info.java][main\java\com\ohnosequences\xml\api\interfaces\package-info.java]
              + model
                + [NameSpace.java][main\java\com\ohnosequences\xml\api\model\NameSpace.java]
                + [package-info.java][main\java\com\ohnosequences\xml\api\model\package-info.java]
                + [XMLAttribute.java][main\java\com\ohnosequences\xml\api\model\XMLAttribute.java]
                + [XMLElement.java][main\java\com\ohnosequences\xml\api\model\XMLElement.java]
                + [XMLElementException.java][main\java\com\ohnosequences\xml\api\model\XMLElementException.java]
              + util
                + [XMLUtil.java][main\java\com\ohnosequences\xml\api\util\XMLUtil.java]
            + model
              + [Annotation.java][main\java\com\ohnosequences\xml\model\Annotation.java]
              + bio4j
                + [Bio4jNodeIndexXML.java][main\java\com\ohnosequences\xml\model\bio4j\Bio4jNodeIndexXML.java]
                + [Bio4jNodeXML.java][main\java\com\ohnosequences\xml\model\bio4j\Bio4jNodeXML.java]
                + [Bio4jPropertyXML.java][main\java\com\ohnosequences\xml\model\bio4j\Bio4jPropertyXML.java]
                + [Bio4jRelationshipIndexXML.java][main\java\com\ohnosequences\xml\model\bio4j\Bio4jRelationshipIndexXML.java]
                + [Bio4jRelationshipXML.java][main\java\com\ohnosequences\xml\model\bio4j\Bio4jRelationshipXML.java]
                + [UniprotDataXML.java][main\java\com\ohnosequences\xml\model\bio4j\UniprotDataXML.java]
              + [BlastOutput.java][main\java\com\ohnosequences\xml\model\BlastOutput.java]
              + [BlastOutputParam.java][main\java\com\ohnosequences\xml\model\BlastOutputParam.java]
              + [Codon.java][main\java\com\ohnosequences\xml\model\Codon.java]
              + [ContigXML.java][main\java\com\ohnosequences\xml\model\ContigXML.java]
              + cufflinks
                + [CuffLinksElement.java][main\java\com\ohnosequences\xml\model\cufflinks\CuffLinksElement.java]
              + embl
                + [EmblXML.java][main\java\com\ohnosequences\xml\model\embl\EmblXML.java]
              + [Frameshift.java][main\java\com\ohnosequences\xml\model\Frameshift.java]
              + [Gap.java][main\java\com\ohnosequences\xml\model\Gap.java]
              + gb
                + [GenBankXML.java][main\java\com\ohnosequences\xml\model\gb\GenBankXML.java]
              + genome
                + feature
                  + [Feature.java][main\java\com\ohnosequences\xml\model\genome\feature\Feature.java]
                  + [Intergenic.java][main\java\com\ohnosequences\xml\model\genome\feature\Intergenic.java]
                  + [MisRNA.java][main\java\com\ohnosequences\xml\model\genome\feature\MisRNA.java]
                  + [ORF.java][main\java\com\ohnosequences\xml\model\genome\feature\ORF.java]
                  + [RNA.java][main\java\com\ohnosequences\xml\model\genome\feature\RNA.java]
                  + [RRNA.java][main\java\com\ohnosequences\xml\model\genome\feature\RRNA.java]
                  + [TRNA.java][main\java\com\ohnosequences\xml\model\genome\feature\TRNA.java]
                + [GenomeElement.java][main\java\com\ohnosequences\xml\model\genome\GenomeElement.java]
              + gexf
                + [AttributesXML.java][main\java\com\ohnosequences\xml\model\gexf\AttributesXML.java]
                + [AttributeXML.java][main\java\com\ohnosequences\xml\model\gexf\AttributeXML.java]
                + [AttValuesXML.java][main\java\com\ohnosequences\xml\model\gexf\AttValuesXML.java]
                + [AttValueXML.java][main\java\com\ohnosequences\xml\model\gexf\AttValueXML.java]
                + [EdgesXML.java][main\java\com\ohnosequences\xml\model\gexf\EdgesXML.java]
                + [EdgeXML.java][main\java\com\ohnosequences\xml\model\gexf\EdgeXML.java]
                + [GexfXML.java][main\java\com\ohnosequences\xml\model\gexf\GexfXML.java]
                + [GraphXML.java][main\java\com\ohnosequences\xml\model\gexf\GraphXML.java]
                + [NodesXML.java][main\java\com\ohnosequences\xml\model\gexf\NodesXML.java]
                + [NodeXML.java][main\java\com\ohnosequences\xml\model\gexf\NodeXML.java]
                + [SpellsXML.java][main\java\com\ohnosequences\xml\model\gexf\SpellsXML.java]
                + [SpellXML.java][main\java\com\ohnosequences\xml\model\gexf\SpellXML.java]
                + viz
                  + [VizColorXML.java][main\java\com\ohnosequences\xml\model\gexf\viz\VizColorXML.java]
                  + [VizPositionXML.java][main\java\com\ohnosequences\xml\model\gexf\viz\VizPositionXML.java]
                  + [VizSizeXML.java][main\java\com\ohnosequences\xml\model\gexf\viz\VizSizeXML.java]
              + go
                + [GoAnnotationXML.java][main\java\com\ohnosequences\xml\model\go\GoAnnotationXML.java]
                + [GOSlimXML.java][main\java\com\ohnosequences\xml\model\go\GOSlimXML.java]
                + [GoTermXML.java][main\java\com\ohnosequences\xml\model\go\GoTermXML.java]
                + [SlimSetXML.java][main\java\com\ohnosequences\xml\model\go\SlimSetXML.java]
              + graphml
                + [DataXML.java][main\java\com\ohnosequences\xml\model\graphml\DataXML.java]
                + [EdgeXML.java][main\java\com\ohnosequences\xml\model\graphml\EdgeXML.java]
                + [GraphmlXML.java][main\java\com\ohnosequences\xml\model\graphml\GraphmlXML.java]
                + [GraphXML.java][main\java\com\ohnosequences\xml\model\graphml\GraphXML.java]
                + [KeyXML.java][main\java\com\ohnosequences\xml\model\graphml\KeyXML.java]
                + [NodeXML.java][main\java\com\ohnosequences\xml\model\graphml\NodeXML.java]
              + [Hit.java][main\java\com\ohnosequences\xml\model\Hit.java]
              + [Hsp.java][main\java\com\ohnosequences\xml\model\Hsp.java]
              + [HspSet.java][main\java\com\ohnosequences\xml\model\HspSet.java]
              + [Iteration.java][main\java\com\ohnosequences\xml\model\Iteration.java]
              + logs
                + [LogRecordXML.java][main\java\com\ohnosequences\xml\model\logs\LogRecordXML.java]
              + metagenomics
                + [ReadResultXML.java][main\java\com\ohnosequences\xml\model\metagenomics\ReadResultXML.java]
                + [ReadXML.java][main\java\com\ohnosequences\xml\model\metagenomics\ReadXML.java]
                + [SampleXML.java][main\java\com\ohnosequences\xml\model\metagenomics\SampleXML.java]
              + [MetagenomicsDataXML.java][main\java\com\ohnosequences\xml\model\MetagenomicsDataXML.java]
              + mg7
                + [MG7DataXML.java][main\java\com\ohnosequences\xml\model\mg7\MG7DataXML.java]
                + [ReadResultXML.java][main\java\com\ohnosequences\xml\model\mg7\ReadResultXML.java]
                + [SampleXML.java][main\java\com\ohnosequences\xml\model\mg7\SampleXML.java]
              + ncbi
                + [NCBITaxonomyNodeXML.java][main\java\com\ohnosequences\xml\model\ncbi\NCBITaxonomyNodeXML.java]
              + oric
                + [Oric.java][main\java\com\ohnosequences\xml\model\oric\Oric.java]
              + [Overlap.java][main\java\com\ohnosequences\xml\model\Overlap.java]
              + pal
                + [PalindromicityResultXML.java][main\java\com\ohnosequences\xml\model\pal\PalindromicityResultXML.java]
              + pg
                + [Primer.java][main\java\com\ohnosequences\xml\model\pg\Primer.java]
              + [PredictedGene.java][main\java\com\ohnosequences\xml\model\PredictedGene.java]
              + [PredictedGenes.java][main\java\com\ohnosequences\xml\model\PredictedGenes.java]
              + [PredictedRna.java][main\java\com\ohnosequences\xml\model\PredictedRna.java]
              + [PredictedRnas.java][main\java\com\ohnosequences\xml\model\PredictedRnas.java]
              + uniprot
                + [ArticleXML.java][main\java\com\ohnosequences\xml\model\uniprot\ArticleXML.java]
                + [CommentXML.java][main\java\com\ohnosequences\xml\model\uniprot\CommentXML.java]
                + [FeatureXML.java][main\java\com\ohnosequences\xml\model\uniprot\FeatureXML.java]
                + [InterproXML.java][main\java\com\ohnosequences\xml\model\uniprot\InterproXML.java]
                + [IsoformXML.java][main\java\com\ohnosequences\xml\model\uniprot\IsoformXML.java]
                + [KeywordXML.java][main\java\com\ohnosequences\xml\model\uniprot\KeywordXML.java]
                + [ProteinXML.java][main\java\com\ohnosequences\xml\model\uniprot\ProteinXML.java]
                + [SubcellularLocationXML.java][main\java\com\ohnosequences\xml\model\uniprot\SubcellularLocationXML.java]
              + util
                + [Argument.java][main\java\com\ohnosequences\xml\model\util\Argument.java]
                + [Arguments.java][main\java\com\ohnosequences\xml\model\util\Arguments.java]
                + [Error.java][main\java\com\ohnosequences\xml\model\util\Error.java]
                + [Execution.java][main\java\com\ohnosequences\xml\model\util\Execution.java]
                + [FlexXMLWrapperClassCreator.java][main\java\com\ohnosequences\xml\model\util\FlexXMLWrapperClassCreator.java]
                + [ScheduledExecutions.java][main\java\com\ohnosequences\xml\model\util\ScheduledExecutions.java]
                + [XMLWrapperClass.java][main\java\com\ohnosequences\xml\model\util\XMLWrapperClass.java]
                + [XMLWrapperClassCreator.java][main\java\com\ohnosequences\xml\model\util\XMLWrapperClassCreator.java]
              + wip
                + [Region.java][main\java\com\ohnosequences\xml\model\wip\Region.java]
                + [WipPosition.java][main\java\com\ohnosequences\xml\model\wip\WipPosition.java]
                + [WipResult.java][main\java\com\ohnosequences\xml\model\wip\WipResult.java]
    + scala
  + test
    + java
    + scala

[main\java\com\ohnosequences\aws\ec2\InstanceUtil.java]: ..\..\..\aws\ec2\InstanceUtil.java.md
[main\java\com\ohnosequences\aws\ec2\SpotUtil.java]: ..\..\..\aws\ec2\SpotUtil.java.md
[main\java\com\ohnosequences\aws\s3\S3FileDownloader.java]: ..\..\..\aws\s3\S3FileDownloader.java.md
[main\java\com\ohnosequences\aws\s3\S3FileUploader.java]: ..\..\..\aws\s3\S3FileUploader.java.md
[main\java\com\ohnosequences\aws\util\AMITypes.java]: ..\..\..\aws\util\AMITypes.java.md
[main\java\com\ohnosequences\aws\util\AvailabilityZones.java]: ..\..\..\aws\util\AvailabilityZones.java.md
[main\java\com\ohnosequences\aws\util\CredentialsRetriever.java]: ..\..\..\aws\util\CredentialsRetriever.java.md
[main\java\com\ohnosequences\aws\util\Endpoints.java]: ..\..\..\aws\util\Endpoints.java.md
[main\java\com\ohnosequences\aws\util\InstanceTypes.java]: ..\..\..\aws\util\InstanceTypes.java.md
[main\java\com\ohnosequences\BioinfoUtil.java]: ..\..\..\BioinfoUtil.java.md
[main\java\com\ohnosequences\util\BitOperations.java]: ..\..\..\util\BitOperations.java.md
[main\java\com\ohnosequences\util\blast\BlastExporter.java]: ..\..\..\util\blast\BlastExporter.java.md
[main\java\com\ohnosequences\util\blast\BlastSubset.java]: ..\..\..\util\blast\BlastSubset.java.md
[main\java\com\ohnosequences\util\CodonUtil.java]: ..\..\..\util\CodonUtil.java.md
[main\java\com\ohnosequences\util\Entry.java]: ..\..\..\util\Entry.java.md
[main\java\com\ohnosequences\util\Executable.java]: ..\..\..\util\Executable.java.md
[main\java\com\ohnosequences\util\ExecuteFromFile.java]: ..\..\..\util\ExecuteFromFile.java.md
[main\java\com\ohnosequences\util\fasta\FastaSubSeq.java]: ..\..\..\util\fasta\FastaSubSeq.java.md
[main\java\com\ohnosequences\util\fasta\FastaUtil.java]: ..\..\..\util\fasta\FastaUtil.java.md
[main\java\com\ohnosequences\util\fasta\MultifastaSelector.java]: ..\..\..\util\fasta\MultifastaSelector.java.md
[main\java\com\ohnosequences\util\fasta\SearchFastaHeaders.java]: ..\..\..\util\fasta\SearchFastaHeaders.java.md
[main\java\com\ohnosequences\util\file\FileUtil.java]: ..\..\..\util\file\FileUtil.java.md
[main\java\com\ohnosequences\util\file\FnaFileFilter.java]: ..\..\..\util\file\FnaFileFilter.java.md
[main\java\com\ohnosequences\util\file\GenomeFilesParser.java]: ..\..\..\util\file\GenomeFilesParser.java.md
[main\java\com\ohnosequences\util\file\PttFileFilter.java]: ..\..\..\util\file\PttFileFilter.java.md
[main\java\com\ohnosequences\util\file\RntFileFilter.java]: ..\..\..\util\file\RntFileFilter.java.md
[main\java\com\ohnosequences\util\genbank\GBCommon.java]: ..\..\..\util\genbank\GBCommon.java.md
[main\java\com\ohnosequences\util\gephi\GephiExporter.java]: ..\..\..\util\gephi\GephiExporter.java.md
[main\java\com\ohnosequences\util\gephi\GexfToDotExporter.java]: ..\..\..\util\gephi\GexfToDotExporter.java.md
[main\java\com\ohnosequences\util\go\GOExporter.java]: ..\..\..\util\go\GOExporter.java.md
[main\java\com\ohnosequences\util\model\Feature.java]: ..\..\..\util\model\Feature.java.md
[main\java\com\ohnosequences\util\model\Intergenic.java]: ..\..\..\util\model\Intergenic.java.md
[main\java\com\ohnosequences\util\model\PalindromicityResult.java]: ..\..\..\util\model\PalindromicityResult.java.md
[main\java\com\ohnosequences\util\ncbi\TaxonomyLoader.java]: ..\..\..\util\ncbi\TaxonomyLoader.java.md
[main\java\com\ohnosequences\util\oric\OricDataRetriever.java]: ..\..\..\util\oric\OricDataRetriever.java.md
[main\java\com\ohnosequences\util\Pair.java]: ..\..\..\util\Pair.java.md
[main\java\com\ohnosequences\util\pal\PalindromicityAnalyzer.java]: ..\..\..\util\pal\PalindromicityAnalyzer.java.md
[main\java\com\ohnosequences\util\security\MD5.java]: ..\..\..\util\security\MD5.java.md
[main\java\com\ohnosequences\util\seq\SeqUtil.java]: ..\..\..\util\seq\SeqUtil.java.md
[main\java\com\ohnosequences\util\statistics\StatisticalValues.java]: ..\..\..\util\statistics\StatisticalValues.java.md
[main\java\com\ohnosequences\util\uniprot\UniprotProteinRetreiver.java]: ..\..\..\util\uniprot\UniprotProteinRetreiver.java.md
[main\java\com\ohnosequences\xml\api\interfaces\IAttribute.java]: ..\..\api\interfaces\IAttribute.java.md
[main\java\com\ohnosequences\xml\api\interfaces\IElement.java]: ..\..\api\interfaces\IElement.java.md
[main\java\com\ohnosequences\xml\api\interfaces\INameSpace.java]: ..\..\api\interfaces\INameSpace.java.md
[main\java\com\ohnosequences\xml\api\interfaces\IXmlThing.java]: ..\..\api\interfaces\IXmlThing.java.md
[main\java\com\ohnosequences\xml\api\interfaces\package-info.java]: ..\..\api\interfaces\package-info.java.md
[main\java\com\ohnosequences\xml\api\model\NameSpace.java]: ..\..\api\model\NameSpace.java.md
[main\java\com\ohnosequences\xml\api\model\package-info.java]: ..\..\api\model\package-info.java.md
[main\java\com\ohnosequences\xml\api\model\XMLAttribute.java]: ..\..\api\model\XMLAttribute.java.md
[main\java\com\ohnosequences\xml\api\model\XMLElement.java]: ..\..\api\model\XMLElement.java.md
[main\java\com\ohnosequences\xml\api\model\XMLElementException.java]: ..\..\api\model\XMLElementException.java.md
[main\java\com\ohnosequences\xml\api\util\XMLUtil.java]: ..\..\api\util\XMLUtil.java.md
[main\java\com\ohnosequences\xml\model\Annotation.java]: ..\Annotation.java.md
[main\java\com\ohnosequences\xml\model\bio4j\Bio4jNodeIndexXML.java]: ..\bio4j\Bio4jNodeIndexXML.java.md
[main\java\com\ohnosequences\xml\model\bio4j\Bio4jNodeXML.java]: ..\bio4j\Bio4jNodeXML.java.md
[main\java\com\ohnosequences\xml\model\bio4j\Bio4jPropertyXML.java]: ..\bio4j\Bio4jPropertyXML.java.md
[main\java\com\ohnosequences\xml\model\bio4j\Bio4jRelationshipIndexXML.java]: ..\bio4j\Bio4jRelationshipIndexXML.java.md
[main\java\com\ohnosequences\xml\model\bio4j\Bio4jRelationshipXML.java]: ..\bio4j\Bio4jRelationshipXML.java.md
[main\java\com\ohnosequences\xml\model\bio4j\UniprotDataXML.java]: ..\bio4j\UniprotDataXML.java.md
[main\java\com\ohnosequences\xml\model\BlastOutput.java]: ..\BlastOutput.java.md
[main\java\com\ohnosequences\xml\model\BlastOutputParam.java]: ..\BlastOutputParam.java.md
[main\java\com\ohnosequences\xml\model\Codon.java]: ..\Codon.java.md
[main\java\com\ohnosequences\xml\model\ContigXML.java]: ..\ContigXML.java.md
[main\java\com\ohnosequences\xml\model\cufflinks\CuffLinksElement.java]: ..\cufflinks\CuffLinksElement.java.md
[main\java\com\ohnosequences\xml\model\embl\EmblXML.java]: ..\embl\EmblXML.java.md
[main\java\com\ohnosequences\xml\model\Frameshift.java]: ..\Frameshift.java.md
[main\java\com\ohnosequences\xml\model\Gap.java]: ..\Gap.java.md
[main\java\com\ohnosequences\xml\model\gb\GenBankXML.java]: ..\gb\GenBankXML.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\Feature.java]: ..\genome\feature\Feature.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\Intergenic.java]: ..\genome\feature\Intergenic.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\MisRNA.java]: ..\genome\feature\MisRNA.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\ORF.java]: ..\genome\feature\ORF.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\RNA.java]: ..\genome\feature\RNA.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\RRNA.java]: ..\genome\feature\RRNA.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\TRNA.java]: ..\genome\feature\TRNA.java.md
[main\java\com\ohnosequences\xml\model\genome\GenomeElement.java]: ..\genome\GenomeElement.java.md
[main\java\com\ohnosequences\xml\model\gexf\AttributesXML.java]: ..\gexf\AttributesXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\AttributeXML.java]: ..\gexf\AttributeXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\AttValuesXML.java]: ..\gexf\AttValuesXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\AttValueXML.java]: ..\gexf\AttValueXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\EdgesXML.java]: ..\gexf\EdgesXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\EdgeXML.java]: ..\gexf\EdgeXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\GexfXML.java]: ..\gexf\GexfXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\GraphXML.java]: ..\gexf\GraphXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\NodesXML.java]: ..\gexf\NodesXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\NodeXML.java]: ..\gexf\NodeXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\SpellsXML.java]: ..\gexf\SpellsXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\SpellXML.java]: ..\gexf\SpellXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\viz\VizColorXML.java]: ..\gexf\viz\VizColorXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\viz\VizPositionXML.java]: ..\gexf\viz\VizPositionXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\viz\VizSizeXML.java]: ..\gexf\viz\VizSizeXML.java.md
[main\java\com\ohnosequences\xml\model\go\GoAnnotationXML.java]: ..\go\GoAnnotationXML.java.md
[main\java\com\ohnosequences\xml\model\go\GOSlimXML.java]: ..\go\GOSlimXML.java.md
[main\java\com\ohnosequences\xml\model\go\GoTermXML.java]: ..\go\GoTermXML.java.md
[main\java\com\ohnosequences\xml\model\go\SlimSetXML.java]: ..\go\SlimSetXML.java.md
[main\java\com\ohnosequences\xml\model\graphml\DataXML.java]: ..\graphml\DataXML.java.md
[main\java\com\ohnosequences\xml\model\graphml\EdgeXML.java]: ..\graphml\EdgeXML.java.md
[main\java\com\ohnosequences\xml\model\graphml\GraphmlXML.java]: ..\graphml\GraphmlXML.java.md
[main\java\com\ohnosequences\xml\model\graphml\GraphXML.java]: ..\graphml\GraphXML.java.md
[main\java\com\ohnosequences\xml\model\graphml\KeyXML.java]: ..\graphml\KeyXML.java.md
[main\java\com\ohnosequences\xml\model\graphml\NodeXML.java]: ..\graphml\NodeXML.java.md
[main\java\com\ohnosequences\xml\model\Hit.java]: ..\Hit.java.md
[main\java\com\ohnosequences\xml\model\Hsp.java]: ..\Hsp.java.md
[main\java\com\ohnosequences\xml\model\HspSet.java]: ..\HspSet.java.md
[main\java\com\ohnosequences\xml\model\Iteration.java]: ..\Iteration.java.md
[main\java\com\ohnosequences\xml\model\logs\LogRecordXML.java]: ..\logs\LogRecordXML.java.md
[main\java\com\ohnosequences\xml\model\metagenomics\ReadResultXML.java]: ReadResultXML.java.md
[main\java\com\ohnosequences\xml\model\metagenomics\ReadXML.java]: ReadXML.java.md
[main\java\com\ohnosequences\xml\model\metagenomics\SampleXML.java]: SampleXML.java.md
[main\java\com\ohnosequences\xml\model\MetagenomicsDataXML.java]: ..\MetagenomicsDataXML.java.md
[main\java\com\ohnosequences\xml\model\mg7\MG7DataXML.java]: ..\mg7\MG7DataXML.java.md
[main\java\com\ohnosequences\xml\model\mg7\ReadResultXML.java]: ..\mg7\ReadResultXML.java.md
[main\java\com\ohnosequences\xml\model\mg7\SampleXML.java]: ..\mg7\SampleXML.java.md
[main\java\com\ohnosequences\xml\model\ncbi\NCBITaxonomyNodeXML.java]: ..\ncbi\NCBITaxonomyNodeXML.java.md
[main\java\com\ohnosequences\xml\model\oric\Oric.java]: ..\oric\Oric.java.md
[main\java\com\ohnosequences\xml\model\Overlap.java]: ..\Overlap.java.md
[main\java\com\ohnosequences\xml\model\pal\PalindromicityResultXML.java]: ..\pal\PalindromicityResultXML.java.md
[main\java\com\ohnosequences\xml\model\pg\Primer.java]: ..\pg\Primer.java.md
[main\java\com\ohnosequences\xml\model\PredictedGene.java]: ..\PredictedGene.java.md
[main\java\com\ohnosequences\xml\model\PredictedGenes.java]: ..\PredictedGenes.java.md
[main\java\com\ohnosequences\xml\model\PredictedRna.java]: ..\PredictedRna.java.md
[main\java\com\ohnosequences\xml\model\PredictedRnas.java]: ..\PredictedRnas.java.md
[main\java\com\ohnosequences\xml\model\uniprot\ArticleXML.java]: ..\uniprot\ArticleXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\CommentXML.java]: ..\uniprot\CommentXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\FeatureXML.java]: ..\uniprot\FeatureXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\InterproXML.java]: ..\uniprot\InterproXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\IsoformXML.java]: ..\uniprot\IsoformXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\KeywordXML.java]: ..\uniprot\KeywordXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\ProteinXML.java]: ..\uniprot\ProteinXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\SubcellularLocationXML.java]: ..\uniprot\SubcellularLocationXML.java.md
[main\java\com\ohnosequences\xml\model\util\Argument.java]: ..\util\Argument.java.md
[main\java\com\ohnosequences\xml\model\util\Arguments.java]: ..\util\Arguments.java.md
[main\java\com\ohnosequences\xml\model\util\Error.java]: ..\util\Error.java.md
[main\java\com\ohnosequences\xml\model\util\Execution.java]: ..\util\Execution.java.md
[main\java\com\ohnosequences\xml\model\util\FlexXMLWrapperClassCreator.java]: ..\util\FlexXMLWrapperClassCreator.java.md
[main\java\com\ohnosequences\xml\model\util\ScheduledExecutions.java]: ..\util\ScheduledExecutions.java.md
[main\java\com\ohnosequences\xml\model\util\XMLWrapperClass.java]: ..\util\XMLWrapperClass.java.md
[main\java\com\ohnosequences\xml\model\util\XMLWrapperClassCreator.java]: ..\util\XMLWrapperClassCreator.java.md
[main\java\com\ohnosequences\xml\model\wip\Region.java]: ..\wip\Region.java.md
[main\java\com\ohnosequences\xml\model\wip\WipPosition.java]: ..\wip\WipPosition.java.md
[main\java\com\ohnosequences\xml\model\wip\WipResult.java]: ..\wip\WipResult.java.md