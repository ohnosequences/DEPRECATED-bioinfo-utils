
 * To change this template, choose Tools | Templates
 * and open the template in the editor.


```java
package com.ohnosequences.util.file;

import com.ohnosequences.xml.model.genome.feature.ORF;
import com.ohnosequences.xml.model.genome.feature.RNA;
import com.ohnosequences.xml.model.genome.GenomeElement;
import com.ohnosequences.xml.api.model.XMLElementException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ppareja
 */
public class GenomeFilesParser {

    public static List<ORF> parsePttFile(InputStream in,
            boolean closeInputStream)
            throws IOException, XMLElementException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        ArrayList<ORF> orfs = new ArrayList<ORF>();

        String tempSt = null;
        boolean locationFound = false;

        while ((tempSt = reader.readLine()) != null) {
            if (locationFound) {
                ORF tempORF = new ORF();

                String[] columns = tempSt.split("\t");
                String locationSt = columns[0];
                String[] positions = locationSt.split("\\.\\.");
                int tempBegin = Integer.parseInt(positions[0]);
                int tempEnd = Integer.parseInt(positions[1]);
                tempORF.setBegin(tempBegin);
                tempORF.setEnd(tempEnd);

                tempORF.setStrand(columns[1]);

                tempORF.setLength((Integer.parseInt(columns[2]) + 1) * 3);

                tempORF.setFeatureName(columns[8]);
                //tempORF.setGene(columns[4]);
                //tempORF.setSynonym(columns[5]);

                orfs.add(tempORF);

            } else {
                if (tempSt.split("\t")[0].equals("Location")) {
                    locationFound = true;
                }
            }
        }

        reader.close();
        if (closeInputStream) {
            in.close();
        }

        return orfs;
    }

    public static List<RNA> parseRntFile(InputStream in,
            boolean closeInputStream)
            throws IOException, XMLElementException {

        ArrayList<RNA> rnas = new ArrayList<RNA>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        boolean locationFound = false;
        String tempSt = null;

        while ((tempSt = reader.readLine()) != null) {
            if (locationFound) {
                RNA tempRNA = new RNA();

                String[] columns = tempSt.split("\t");
                String locationSt = columns[0];
                int tempBegin = Integer.parseInt(locationSt.split("\\.\\.")[0]);
                int tempEnd = Integer.parseInt(locationSt.split("\\.\\.")[1]);
                tempRNA.setBegin(tempBegin);
                tempRNA.setEnd(tempEnd);

                tempRNA.setLength(Integer.parseInt(columns[2]));

                tempRNA.setStrand(columns[1]);
                tempRNA.setFeatureName(columns[8]);
                //tempRNA.setGene(columns[4]);
                //tempRNA.setSynonym(columns[5]);

                rnas.add(tempRNA);

            } else {
                if (tempSt.split("\t")[0].equals("Location")) {
                    locationFound = true;
                }
            }
        }

        reader.close();
        if (closeInputStream) {
            in.close();
        }

        return rnas;
    }

    public static GenomeElement parseFnaFile(InputStream in,
            boolean closeInputStream)
            throws IOException {

        GenomeElement genomeElement = new GenomeElement();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        StringBuilder stringBuilder = new StringBuilder();
        String tempSt = null;

        while ((tempSt = reader.readLine()) != null) {
            if (tempSt.charAt(0) != '>') {
                stringBuilder.append(tempSt.trim());
            }
        }
        String sequence = stringBuilder.toString();
        genomeElement.setSequence(sequence);
        genomeElement.setLength(sequence.length());

        reader.close();
        if (closeInputStream) {
            in.close();
        }

        return genomeElement;
    }
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

[main\java\com\ohnosequences\aws\ec2\InstanceUtil.java]: ..\..\aws\ec2\InstanceUtil.java.md
[main\java\com\ohnosequences\aws\ec2\SpotUtil.java]: ..\..\aws\ec2\SpotUtil.java.md
[main\java\com\ohnosequences\aws\s3\S3FileDownloader.java]: ..\..\aws\s3\S3FileDownloader.java.md
[main\java\com\ohnosequences\aws\s3\S3FileUploader.java]: ..\..\aws\s3\S3FileUploader.java.md
[main\java\com\ohnosequences\aws\util\AMITypes.java]: ..\..\aws\util\AMITypes.java.md
[main\java\com\ohnosequences\aws\util\AvailabilityZones.java]: ..\..\aws\util\AvailabilityZones.java.md
[main\java\com\ohnosequences\aws\util\CredentialsRetriever.java]: ..\..\aws\util\CredentialsRetriever.java.md
[main\java\com\ohnosequences\aws\util\Endpoints.java]: ..\..\aws\util\Endpoints.java.md
[main\java\com\ohnosequences\aws\util\InstanceTypes.java]: ..\..\aws\util\InstanceTypes.java.md
[main\java\com\ohnosequences\BioinfoUtil.java]: ..\..\BioinfoUtil.java.md
[main\java\com\ohnosequences\util\BitOperations.java]: ..\BitOperations.java.md
[main\java\com\ohnosequences\util\blast\BlastExporter.java]: ..\blast\BlastExporter.java.md
[main\java\com\ohnosequences\util\blast\BlastSubset.java]: ..\blast\BlastSubset.java.md
[main\java\com\ohnosequences\util\CodonUtil.java]: ..\CodonUtil.java.md
[main\java\com\ohnosequences\util\Entry.java]: ..\Entry.java.md
[main\java\com\ohnosequences\util\Executable.java]: ..\Executable.java.md
[main\java\com\ohnosequences\util\ExecuteFromFile.java]: ..\ExecuteFromFile.java.md
[main\java\com\ohnosequences\util\fasta\FastaSubSeq.java]: ..\fasta\FastaSubSeq.java.md
[main\java\com\ohnosequences\util\fasta\FastaUtil.java]: ..\fasta\FastaUtil.java.md
[main\java\com\ohnosequences\util\fasta\MultifastaSelector.java]: ..\fasta\MultifastaSelector.java.md
[main\java\com\ohnosequences\util\fasta\SearchFastaHeaders.java]: ..\fasta\SearchFastaHeaders.java.md
[main\java\com\ohnosequences\util\file\FileUtil.java]: FileUtil.java.md
[main\java\com\ohnosequences\util\file\FnaFileFilter.java]: FnaFileFilter.java.md
[main\java\com\ohnosequences\util\file\GenomeFilesParser.java]: GenomeFilesParser.java.md
[main\java\com\ohnosequences\util\file\PttFileFilter.java]: PttFileFilter.java.md
[main\java\com\ohnosequences\util\file\RntFileFilter.java]: RntFileFilter.java.md
[main\java\com\ohnosequences\util\genbank\GBCommon.java]: ..\genbank\GBCommon.java.md
[main\java\com\ohnosequences\util\gephi\GephiExporter.java]: ..\gephi\GephiExporter.java.md
[main\java\com\ohnosequences\util\gephi\GexfToDotExporter.java]: ..\gephi\GexfToDotExporter.java.md
[main\java\com\ohnosequences\util\go\GOExporter.java]: ..\go\GOExporter.java.md
[main\java\com\ohnosequences\util\model\Feature.java]: ..\model\Feature.java.md
[main\java\com\ohnosequences\util\model\Intergenic.java]: ..\model\Intergenic.java.md
[main\java\com\ohnosequences\util\model\PalindromicityResult.java]: ..\model\PalindromicityResult.java.md
[main\java\com\ohnosequences\util\ncbi\TaxonomyLoader.java]: ..\ncbi\TaxonomyLoader.java.md
[main\java\com\ohnosequences\util\oric\OricDataRetriever.java]: ..\oric\OricDataRetriever.java.md
[main\java\com\ohnosequences\util\Pair.java]: ..\Pair.java.md
[main\java\com\ohnosequences\util\pal\PalindromicityAnalyzer.java]: ..\pal\PalindromicityAnalyzer.java.md
[main\java\com\ohnosequences\util\security\MD5.java]: ..\security\MD5.java.md
[main\java\com\ohnosequences\util\seq\SeqUtil.java]: ..\seq\SeqUtil.java.md
[main\java\com\ohnosequences\util\statistics\StatisticalValues.java]: ..\statistics\StatisticalValues.java.md
[main\java\com\ohnosequences\util\uniprot\UniprotProteinRetreiver.java]: ..\uniprot\UniprotProteinRetreiver.java.md
[main\java\com\ohnosequences\xml\api\interfaces\IAttribute.java]: ..\..\xml\api\interfaces\IAttribute.java.md
[main\java\com\ohnosequences\xml\api\interfaces\IElement.java]: ..\..\xml\api\interfaces\IElement.java.md
[main\java\com\ohnosequences\xml\api\interfaces\INameSpace.java]: ..\..\xml\api\interfaces\INameSpace.java.md
[main\java\com\ohnosequences\xml\api\interfaces\IXmlThing.java]: ..\..\xml\api\interfaces\IXmlThing.java.md
[main\java\com\ohnosequences\xml\api\interfaces\package-info.java]: ..\..\xml\api\interfaces\package-info.java.md
[main\java\com\ohnosequences\xml\api\model\NameSpace.java]: ..\..\xml\api\model\NameSpace.java.md
[main\java\com\ohnosequences\xml\api\model\package-info.java]: ..\..\xml\api\model\package-info.java.md
[main\java\com\ohnosequences\xml\api\model\XMLAttribute.java]: ..\..\xml\api\model\XMLAttribute.java.md
[main\java\com\ohnosequences\xml\api\model\XMLElement.java]: ..\..\xml\api\model\XMLElement.java.md
[main\java\com\ohnosequences\xml\api\model\XMLElementException.java]: ..\..\xml\api\model\XMLElementException.java.md
[main\java\com\ohnosequences\xml\api\util\XMLUtil.java]: ..\..\xml\api\util\XMLUtil.java.md
[main\java\com\ohnosequences\xml\model\Annotation.java]: ..\..\xml\model\Annotation.java.md
[main\java\com\ohnosequences\xml\model\bio4j\Bio4jNodeIndexXML.java]: ..\..\xml\model\bio4j\Bio4jNodeIndexXML.java.md
[main\java\com\ohnosequences\xml\model\bio4j\Bio4jNodeXML.java]: ..\..\xml\model\bio4j\Bio4jNodeXML.java.md
[main\java\com\ohnosequences\xml\model\bio4j\Bio4jPropertyXML.java]: ..\..\xml\model\bio4j\Bio4jPropertyXML.java.md
[main\java\com\ohnosequences\xml\model\bio4j\Bio4jRelationshipIndexXML.java]: ..\..\xml\model\bio4j\Bio4jRelationshipIndexXML.java.md
[main\java\com\ohnosequences\xml\model\bio4j\Bio4jRelationshipXML.java]: ..\..\xml\model\bio4j\Bio4jRelationshipXML.java.md
[main\java\com\ohnosequences\xml\model\bio4j\UniprotDataXML.java]: ..\..\xml\model\bio4j\UniprotDataXML.java.md
[main\java\com\ohnosequences\xml\model\BlastOutput.java]: ..\..\xml\model\BlastOutput.java.md
[main\java\com\ohnosequences\xml\model\BlastOutputParam.java]: ..\..\xml\model\BlastOutputParam.java.md
[main\java\com\ohnosequences\xml\model\Codon.java]: ..\..\xml\model\Codon.java.md
[main\java\com\ohnosequences\xml\model\ContigXML.java]: ..\..\xml\model\ContigXML.java.md
[main\java\com\ohnosequences\xml\model\cufflinks\CuffLinksElement.java]: ..\..\xml\model\cufflinks\CuffLinksElement.java.md
[main\java\com\ohnosequences\xml\model\embl\EmblXML.java]: ..\..\xml\model\embl\EmblXML.java.md
[main\java\com\ohnosequences\xml\model\Frameshift.java]: ..\..\xml\model\Frameshift.java.md
[main\java\com\ohnosequences\xml\model\Gap.java]: ..\..\xml\model\Gap.java.md
[main\java\com\ohnosequences\xml\model\gb\GenBankXML.java]: ..\..\xml\model\gb\GenBankXML.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\Feature.java]: ..\..\xml\model\genome\feature\Feature.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\Intergenic.java]: ..\..\xml\model\genome\feature\Intergenic.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\MisRNA.java]: ..\..\xml\model\genome\feature\MisRNA.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\ORF.java]: ..\..\xml\model\genome\feature\ORF.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\RNA.java]: ..\..\xml\model\genome\feature\RNA.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\RRNA.java]: ..\..\xml\model\genome\feature\RRNA.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\TRNA.java]: ..\..\xml\model\genome\feature\TRNA.java.md
[main\java\com\ohnosequences\xml\model\genome\GenomeElement.java]: ..\..\xml\model\genome\GenomeElement.java.md
[main\java\com\ohnosequences\xml\model\gexf\AttributesXML.java]: ..\..\xml\model\gexf\AttributesXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\AttributeXML.java]: ..\..\xml\model\gexf\AttributeXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\AttValuesXML.java]: ..\..\xml\model\gexf\AttValuesXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\AttValueXML.java]: ..\..\xml\model\gexf\AttValueXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\EdgesXML.java]: ..\..\xml\model\gexf\EdgesXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\EdgeXML.java]: ..\..\xml\model\gexf\EdgeXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\GexfXML.java]: ..\..\xml\model\gexf\GexfXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\GraphXML.java]: ..\..\xml\model\gexf\GraphXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\NodesXML.java]: ..\..\xml\model\gexf\NodesXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\NodeXML.java]: ..\..\xml\model\gexf\NodeXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\SpellsXML.java]: ..\..\xml\model\gexf\SpellsXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\SpellXML.java]: ..\..\xml\model\gexf\SpellXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\viz\VizColorXML.java]: ..\..\xml\model\gexf\viz\VizColorXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\viz\VizPositionXML.java]: ..\..\xml\model\gexf\viz\VizPositionXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\viz\VizSizeXML.java]: ..\..\xml\model\gexf\viz\VizSizeXML.java.md
[main\java\com\ohnosequences\xml\model\go\GoAnnotationXML.java]: ..\..\xml\model\go\GoAnnotationXML.java.md
[main\java\com\ohnosequences\xml\model\go\GOSlimXML.java]: ..\..\xml\model\go\GOSlimXML.java.md
[main\java\com\ohnosequences\xml\model\go\GoTermXML.java]: ..\..\xml\model\go\GoTermXML.java.md
[main\java\com\ohnosequences\xml\model\go\SlimSetXML.java]: ..\..\xml\model\go\SlimSetXML.java.md
[main\java\com\ohnosequences\xml\model\graphml\DataXML.java]: ..\..\xml\model\graphml\DataXML.java.md
[main\java\com\ohnosequences\xml\model\graphml\EdgeXML.java]: ..\..\xml\model\graphml\EdgeXML.java.md
[main\java\com\ohnosequences\xml\model\graphml\GraphmlXML.java]: ..\..\xml\model\graphml\GraphmlXML.java.md
[main\java\com\ohnosequences\xml\model\graphml\GraphXML.java]: ..\..\xml\model\graphml\GraphXML.java.md
[main\java\com\ohnosequences\xml\model\graphml\KeyXML.java]: ..\..\xml\model\graphml\KeyXML.java.md
[main\java\com\ohnosequences\xml\model\graphml\NodeXML.java]: ..\..\xml\model\graphml\NodeXML.java.md
[main\java\com\ohnosequences\xml\model\Hit.java]: ..\..\xml\model\Hit.java.md
[main\java\com\ohnosequences\xml\model\Hsp.java]: ..\..\xml\model\Hsp.java.md
[main\java\com\ohnosequences\xml\model\HspSet.java]: ..\..\xml\model\HspSet.java.md
[main\java\com\ohnosequences\xml\model\Iteration.java]: ..\..\xml\model\Iteration.java.md
[main\java\com\ohnosequences\xml\model\logs\LogRecordXML.java]: ..\..\xml\model\logs\LogRecordXML.java.md
[main\java\com\ohnosequences\xml\model\metagenomics\ReadResultXML.java]: ..\..\xml\model\metagenomics\ReadResultXML.java.md
[main\java\com\ohnosequences\xml\model\metagenomics\ReadXML.java]: ..\..\xml\model\metagenomics\ReadXML.java.md
[main\java\com\ohnosequences\xml\model\metagenomics\SampleXML.java]: ..\..\xml\model\metagenomics\SampleXML.java.md
[main\java\com\ohnosequences\xml\model\MetagenomicsDataXML.java]: ..\..\xml\model\MetagenomicsDataXML.java.md
[main\java\com\ohnosequences\xml\model\mg7\MG7DataXML.java]: ..\..\xml\model\mg7\MG7DataXML.java.md
[main\java\com\ohnosequences\xml\model\mg7\ReadResultXML.java]: ..\..\xml\model\mg7\ReadResultXML.java.md
[main\java\com\ohnosequences\xml\model\mg7\SampleXML.java]: ..\..\xml\model\mg7\SampleXML.java.md
[main\java\com\ohnosequences\xml\model\ncbi\NCBITaxonomyNodeXML.java]: ..\..\xml\model\ncbi\NCBITaxonomyNodeXML.java.md
[main\java\com\ohnosequences\xml\model\oric\Oric.java]: ..\..\xml\model\oric\Oric.java.md
[main\java\com\ohnosequences\xml\model\Overlap.java]: ..\..\xml\model\Overlap.java.md
[main\java\com\ohnosequences\xml\model\pal\PalindromicityResultXML.java]: ..\..\xml\model\pal\PalindromicityResultXML.java.md
[main\java\com\ohnosequences\xml\model\pg\Primer.java]: ..\..\xml\model\pg\Primer.java.md
[main\java\com\ohnosequences\xml\model\PredictedGene.java]: ..\..\xml\model\PredictedGene.java.md
[main\java\com\ohnosequences\xml\model\PredictedGenes.java]: ..\..\xml\model\PredictedGenes.java.md
[main\java\com\ohnosequences\xml\model\PredictedRna.java]: ..\..\xml\model\PredictedRna.java.md
[main\java\com\ohnosequences\xml\model\PredictedRnas.java]: ..\..\xml\model\PredictedRnas.java.md
[main\java\com\ohnosequences\xml\model\uniprot\ArticleXML.java]: ..\..\xml\model\uniprot\ArticleXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\CommentXML.java]: ..\..\xml\model\uniprot\CommentXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\FeatureXML.java]: ..\..\xml\model\uniprot\FeatureXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\InterproXML.java]: ..\..\xml\model\uniprot\InterproXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\IsoformXML.java]: ..\..\xml\model\uniprot\IsoformXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\KeywordXML.java]: ..\..\xml\model\uniprot\KeywordXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\ProteinXML.java]: ..\..\xml\model\uniprot\ProteinXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\SubcellularLocationXML.java]: ..\..\xml\model\uniprot\SubcellularLocationXML.java.md
[main\java\com\ohnosequences\xml\model\util\Argument.java]: ..\..\xml\model\util\Argument.java.md
[main\java\com\ohnosequences\xml\model\util\Arguments.java]: ..\..\xml\model\util\Arguments.java.md
[main\java\com\ohnosequences\xml\model\util\Error.java]: ..\..\xml\model\util\Error.java.md
[main\java\com\ohnosequences\xml\model\util\Execution.java]: ..\..\xml\model\util\Execution.java.md
[main\java\com\ohnosequences\xml\model\util\FlexXMLWrapperClassCreator.java]: ..\..\xml\model\util\FlexXMLWrapperClassCreator.java.md
[main\java\com\ohnosequences\xml\model\util\ScheduledExecutions.java]: ..\..\xml\model\util\ScheduledExecutions.java.md
[main\java\com\ohnosequences\xml\model\util\XMLWrapperClass.java]: ..\..\xml\model\util\XMLWrapperClass.java.md
[main\java\com\ohnosequences\xml\model\util\XMLWrapperClassCreator.java]: ..\..\xml\model\util\XMLWrapperClassCreator.java.md
[main\java\com\ohnosequences\xml\model\wip\Region.java]: ..\..\xml\model\wip\Region.java.md
[main\java\com\ohnosequences\xml\model\wip\WipPosition.java]: ..\..\xml\model\wip\WipPosition.java.md
[main\java\com\ohnosequences\xml\model\wip\WipResult.java]: ..\..\xml\model\wip\WipResult.java.md