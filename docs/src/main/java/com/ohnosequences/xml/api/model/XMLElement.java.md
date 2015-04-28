
```java
package com.ohnosequences.xml.api.model;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.filter.ElementFilter;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.InputSource;

import com.ohnosequences.xml.api.interfaces.IElement;
import com.ohnosequences.xml.api.interfaces.INameSpace;

public class XMLElement implements IElement {

    protected static XMLOutputter outputter = new XMLOutputter();
    protected Element root = null;
    protected Document doc = null;
    protected SAXBuilder builder = null;

    /**
     *
     * @param elem
     */
    public XMLElement(Element elem) {
        root = elem;
    }

    /**
     * Constructor
     * @param value String representing the XML document
     * @throws Exception
     */
    public XMLElement(String value) throws Exception {
        buildElementFromString(value);
    }

    /**
     * Constructor
     * @param tagName Tag name
     * @param ns Namespace
     */
    public XMLElement(String tagName, INameSpace ns) {
        root = new Element(tagName, Namespace.getNamespace(ns.getPrefix(), ns.getUri()));
    }

    private void initDocBuilder() throws ParserConfigurationException {
        if (builder == null) {
            builder = new SAXBuilder();
        }
    }

    private void buildElementFromString(String value) throws ParserConfigurationException, JDOMException, IOException {
        initDocBuilder();

        Reader reader = new CharArrayReader(value.toCharArray());
        doc = builder.build(new InputSource(reader));
        root = doc.getRootElement();
    }
//
//    /**
//     *
//     * @param value
//     * @throws XMLElementException
//     * @throws IOException
//     * @throws JDOMException
//     * @throws ParserConfigurationException
//     */
//    public XMLElement(XmlValue value) throws XMLElementException, ParserConfigurationException, JDOMException, IOException {
//        try {
//            if (value.getNodeType() == XmlValue.ELEMENT_NODE) {
//                buildElementFromString(value.asString());
//            } else {
//                throw new XMLElementException(XMLElementException.WRONG_CONSTRUCTOR_PARAMETER, null);
//            }
//        } catch (XmlException e) {
//            e.printStackTrace();
//            throw new XMLElementException(XMLElementException.WRONG_CONSTRUCTOR_PARAMETER, null);
//        }
//    }

    @Override
    public String getName() {
        return root.getName();
    }

    @Override
    public NameSpace getNameSpace() {
        return new NameSpace(root.getNamespace());
    }

    @Override
    public String getText() {
        return root.getText();
    }

    @Override
    public void setName(String newValue) {
        this.root.setName(newValue);
    }

    @Override
    public void setNameSpace(INameSpace newValue) {
        this.root.setNamespace(Namespace.getNamespace(newValue.getPrefix(), newValue.getUri()));
    }

    @Override
    public void setText(String newValue) {
        this.root.setText(newValue);
    }

    @Override
    public List<XMLAttribute> getAttributes() {
        List<XMLAttribute> list = new ArrayList<XMLAttribute>();
        for (Object attr : this.root.getAttributes()) {
            XMLAttribute temp = new XMLAttribute((org.jdom2.Attribute) (attr));
            list.add(temp);
        }
        return list;
    }

    @Override
    public List<XMLElement> getChildren() {
        List<XMLElement> list = new ArrayList<XMLElement>();
        for (Object elem : this.root.getChildren()) {
            XMLElement temp = new XMLElement((org.jdom2.Element) (elem));
            list.add(temp);
        }
        return list;
    }

    @Override
    public List<XMLElement> getChildrenWith(INameSpace ns) {
        List<XMLElement> list = new ArrayList<XMLElement>();

        for (Object elem : this.root.getChildren()) {

            org.jdom2.Element e = (org.jdom2.Element) (elem);
            if (e.getNamespacePrefix().equals(ns.getPrefix()) && e.getNamespaceURI().equals(ns.getUri())) {
                XMLElement temp = new XMLElement(e);
                list.add(temp);
            }

        }
        return list;
    }

    @Override
    public List<XMLElement> getChildrenWith(String name) {
        List<XMLElement> list = new ArrayList<XMLElement>();

        ElementFilter filter = new ElementFilter(name);

        for (Object elem : this.root.getContent(filter)) {
            XMLElement temp = new XMLElement((org.jdom2.Element) (elem));
            list.add(temp);

        }
        return list;
    }

    @Override
    public List<XMLAttribute> getAttributeWith(String name) {
        List<XMLAttribute> list = new ArrayList<XMLAttribute>();

        for (Object attr : this.root.getAttributes()) {

            org.jdom2.Attribute a = (org.jdom2.Attribute) (attr);
            if (a.getName().equals(name)) {
                XMLAttribute temp = new XMLAttribute(a);
                list.add(temp);
            }

        }
        return list;
    }

    @Override
    public List<XMLAttribute> getAttributeWith(INameSpace ns) {
        List<XMLAttribute> list = new ArrayList<XMLAttribute>();

        for (Object attr : this.root.getChildren()) {

            org.jdom2.Attribute a = (org.jdom2.Attribute) (attr);
            if (a.getNamespacePrefix().equals(ns.getPrefix()) && a.getNamespaceURI().equals(ns.getUri())) {
                XMLAttribute temp = new XMLAttribute(a);
                list.add(temp);
            }

        }
        return list;
    }

    @Override
    public void setAttributes(List<XMLAttribute> newValue) {
        List<org.jdom2.Attribute> list = new ArrayList<org.jdom2.Attribute>();
        for (XMLAttribute attr : newValue) {
            list.add(new org.jdom2.Attribute(attr.getName(), attr.getText(), attr.getNameSpace().asJdomNamespace()));
        }
        this.root.setAttributes(list);
    }

    @Override
    public void setChildren(List<XMLElement> newValue) {
        //Removing previous children
        for (Object elem : this.root.getChildren()) {
            this.root.removeChildren(((Element) elem).getName());
        }
        //Setting new children
        for (IElement elem : newValue) {
            this.root.addContent(elem.asJDomElement());
        }
    }

    /**
     *	Detach
     *
     *	Detaches the root element so it can be added to other document
     */
    public void detach() {
        root.detach();
    }

    public Element getRoot() {
        return root;
    }

    public void setRoot(Element root) {
        this.root = root;
    }

    /**
     * Returns the cdata text of the first element with tag name = 'tagName'
     * If there is no such node it returns the empty string
     * @param tagName Name of the tag
     * @return cdata text of the first element with tag name = 'tagName'
     */
    protected String getNodeText(String tagName) {
        Element element = this.root.getChild(tagName);

        if (element != null) {
            return element.getText();
        } else {
            return null;
        }
    }

    /**
     * Sets the 'text' for the element with tag node 'tagNameNode' creating the
     * node in case it does not exist yet
     * @param tagNameNode
     */
    protected void setNodeText(String tagNameNode, String text) {
        Element element = this.root.getChild(tagNameNode);

        if (element == null) {
            element = new Element(tagNameNode);
            this.root.addContent(element);
        }
        element.setText(text);
    }

    /**
     * Returns the text of the attribute with name = 'name'
     * If there is no such attribute it returns the empty string
     * @param name
     */
    protected String getAttributeText(String name) {
        return root.getAttributeValue(name);
    }
    /**
     * Sets the 'text' for the attribute with name 'name'
     * @param name
     * @param text
     */
    protected void setAttributeText(String name, String text) {
       root.setAttribute(name, text);
    }

    /**
     *  To string
     */
    @Override
    public String toString() {
        return outputter.outputString(root);
    }

    @Override
    public Element asJDomElement() {
        return this.root;
    }
//
//    @Override
//    public XmlValue asXmlvalue() throws XmlException, FileNotFoundException {
//        XmlDocument tempDoc = new XmlManager().createDocument();
//        tempDoc.setContent(this.toString());
//        XmlValue temp = new XmlValue(tempDoc);
//        return temp;
//    }

    @Override
    public void addChild(XMLElement element) {
        this.root.addContent(element.asJDomElement());
    }

    @Override
    public void addChildren(List<XMLElement> list) {
        for (XMLElement elem : list) {
            this.root.addContent(elem.asJDomElement());
        }
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
[main\java\com\ohnosequences\xml\api\interfaces\IAttribute.java]: ..\interfaces\IAttribute.java.md
[main\java\com\ohnosequences\xml\api\interfaces\IElement.java]: ..\interfaces\IElement.java.md
[main\java\com\ohnosequences\xml\api\interfaces\INameSpace.java]: ..\interfaces\INameSpace.java.md
[main\java\com\ohnosequences\xml\api\interfaces\IXmlThing.java]: ..\interfaces\IXmlThing.java.md
[main\java\com\ohnosequences\xml\api\interfaces\package-info.java]: ..\interfaces\package-info.java.md
[main\java\com\ohnosequences\xml\api\model\NameSpace.java]: NameSpace.java.md
[main\java\com\ohnosequences\xml\api\model\package-info.java]: package-info.java.md
[main\java\com\ohnosequences\xml\api\model\XMLAttribute.java]: XMLAttribute.java.md
[main\java\com\ohnosequences\xml\api\model\XMLElement.java]: XMLElement.java.md
[main\java\com\ohnosequences\xml\api\model\XMLElementException.java]: XMLElementException.java.md
[main\java\com\ohnosequences\xml\api\util\XMLUtil.java]: ..\util\XMLUtil.java.md
[main\java\com\ohnosequences\xml\model\Annotation.java]: ..\..\model\Annotation.java.md
[main\java\com\ohnosequences\xml\model\bio4j\Bio4jNodeIndexXML.java]: ..\..\model\bio4j\Bio4jNodeIndexXML.java.md
[main\java\com\ohnosequences\xml\model\bio4j\Bio4jNodeXML.java]: ..\..\model\bio4j\Bio4jNodeXML.java.md
[main\java\com\ohnosequences\xml\model\bio4j\Bio4jPropertyXML.java]: ..\..\model\bio4j\Bio4jPropertyXML.java.md
[main\java\com\ohnosequences\xml\model\bio4j\Bio4jRelationshipIndexXML.java]: ..\..\model\bio4j\Bio4jRelationshipIndexXML.java.md
[main\java\com\ohnosequences\xml\model\bio4j\Bio4jRelationshipXML.java]: ..\..\model\bio4j\Bio4jRelationshipXML.java.md
[main\java\com\ohnosequences\xml\model\bio4j\UniprotDataXML.java]: ..\..\model\bio4j\UniprotDataXML.java.md
[main\java\com\ohnosequences\xml\model\BlastOutput.java]: ..\..\model\BlastOutput.java.md
[main\java\com\ohnosequences\xml\model\BlastOutputParam.java]: ..\..\model\BlastOutputParam.java.md
[main\java\com\ohnosequences\xml\model\Codon.java]: ..\..\model\Codon.java.md
[main\java\com\ohnosequences\xml\model\ContigXML.java]: ..\..\model\ContigXML.java.md
[main\java\com\ohnosequences\xml\model\cufflinks\CuffLinksElement.java]: ..\..\model\cufflinks\CuffLinksElement.java.md
[main\java\com\ohnosequences\xml\model\embl\EmblXML.java]: ..\..\model\embl\EmblXML.java.md
[main\java\com\ohnosequences\xml\model\Frameshift.java]: ..\..\model\Frameshift.java.md
[main\java\com\ohnosequences\xml\model\Gap.java]: ..\..\model\Gap.java.md
[main\java\com\ohnosequences\xml\model\gb\GenBankXML.java]: ..\..\model\gb\GenBankXML.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\Feature.java]: ..\..\model\genome\feature\Feature.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\Intergenic.java]: ..\..\model\genome\feature\Intergenic.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\MisRNA.java]: ..\..\model\genome\feature\MisRNA.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\ORF.java]: ..\..\model\genome\feature\ORF.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\RNA.java]: ..\..\model\genome\feature\RNA.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\RRNA.java]: ..\..\model\genome\feature\RRNA.java.md
[main\java\com\ohnosequences\xml\model\genome\feature\TRNA.java]: ..\..\model\genome\feature\TRNA.java.md
[main\java\com\ohnosequences\xml\model\genome\GenomeElement.java]: ..\..\model\genome\GenomeElement.java.md
[main\java\com\ohnosequences\xml\model\gexf\AttributesXML.java]: ..\..\model\gexf\AttributesXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\AttributeXML.java]: ..\..\model\gexf\AttributeXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\AttValuesXML.java]: ..\..\model\gexf\AttValuesXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\AttValueXML.java]: ..\..\model\gexf\AttValueXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\EdgesXML.java]: ..\..\model\gexf\EdgesXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\EdgeXML.java]: ..\..\model\gexf\EdgeXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\GexfXML.java]: ..\..\model\gexf\GexfXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\GraphXML.java]: ..\..\model\gexf\GraphXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\NodesXML.java]: ..\..\model\gexf\NodesXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\NodeXML.java]: ..\..\model\gexf\NodeXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\SpellsXML.java]: ..\..\model\gexf\SpellsXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\SpellXML.java]: ..\..\model\gexf\SpellXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\viz\VizColorXML.java]: ..\..\model\gexf\viz\VizColorXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\viz\VizPositionXML.java]: ..\..\model\gexf\viz\VizPositionXML.java.md
[main\java\com\ohnosequences\xml\model\gexf\viz\VizSizeXML.java]: ..\..\model\gexf\viz\VizSizeXML.java.md
[main\java\com\ohnosequences\xml\model\go\GoAnnotationXML.java]: ..\..\model\go\GoAnnotationXML.java.md
[main\java\com\ohnosequences\xml\model\go\GOSlimXML.java]: ..\..\model\go\GOSlimXML.java.md
[main\java\com\ohnosequences\xml\model\go\GoTermXML.java]: ..\..\model\go\GoTermXML.java.md
[main\java\com\ohnosequences\xml\model\go\SlimSetXML.java]: ..\..\model\go\SlimSetXML.java.md
[main\java\com\ohnosequences\xml\model\graphml\DataXML.java]: ..\..\model\graphml\DataXML.java.md
[main\java\com\ohnosequences\xml\model\graphml\EdgeXML.java]: ..\..\model\graphml\EdgeXML.java.md
[main\java\com\ohnosequences\xml\model\graphml\GraphmlXML.java]: ..\..\model\graphml\GraphmlXML.java.md
[main\java\com\ohnosequences\xml\model\graphml\GraphXML.java]: ..\..\model\graphml\GraphXML.java.md
[main\java\com\ohnosequences\xml\model\graphml\KeyXML.java]: ..\..\model\graphml\KeyXML.java.md
[main\java\com\ohnosequences\xml\model\graphml\NodeXML.java]: ..\..\model\graphml\NodeXML.java.md
[main\java\com\ohnosequences\xml\model\Hit.java]: ..\..\model\Hit.java.md
[main\java\com\ohnosequences\xml\model\Hsp.java]: ..\..\model\Hsp.java.md
[main\java\com\ohnosequences\xml\model\HspSet.java]: ..\..\model\HspSet.java.md
[main\java\com\ohnosequences\xml\model\Iteration.java]: ..\..\model\Iteration.java.md
[main\java\com\ohnosequences\xml\model\logs\LogRecordXML.java]: ..\..\model\logs\LogRecordXML.java.md
[main\java\com\ohnosequences\xml\model\metagenomics\ReadResultXML.java]: ..\..\model\metagenomics\ReadResultXML.java.md
[main\java\com\ohnosequences\xml\model\metagenomics\ReadXML.java]: ..\..\model\metagenomics\ReadXML.java.md
[main\java\com\ohnosequences\xml\model\metagenomics\SampleXML.java]: ..\..\model\metagenomics\SampleXML.java.md
[main\java\com\ohnosequences\xml\model\MetagenomicsDataXML.java]: ..\..\model\MetagenomicsDataXML.java.md
[main\java\com\ohnosequences\xml\model\mg7\MG7DataXML.java]: ..\..\model\mg7\MG7DataXML.java.md
[main\java\com\ohnosequences\xml\model\mg7\ReadResultXML.java]: ..\..\model\mg7\ReadResultXML.java.md
[main\java\com\ohnosequences\xml\model\mg7\SampleXML.java]: ..\..\model\mg7\SampleXML.java.md
[main\java\com\ohnosequences\xml\model\ncbi\NCBITaxonomyNodeXML.java]: ..\..\model\ncbi\NCBITaxonomyNodeXML.java.md
[main\java\com\ohnosequences\xml\model\oric\Oric.java]: ..\..\model\oric\Oric.java.md
[main\java\com\ohnosequences\xml\model\Overlap.java]: ..\..\model\Overlap.java.md
[main\java\com\ohnosequences\xml\model\pal\PalindromicityResultXML.java]: ..\..\model\pal\PalindromicityResultXML.java.md
[main\java\com\ohnosequences\xml\model\pg\Primer.java]: ..\..\model\pg\Primer.java.md
[main\java\com\ohnosequences\xml\model\PredictedGene.java]: ..\..\model\PredictedGene.java.md
[main\java\com\ohnosequences\xml\model\PredictedGenes.java]: ..\..\model\PredictedGenes.java.md
[main\java\com\ohnosequences\xml\model\PredictedRna.java]: ..\..\model\PredictedRna.java.md
[main\java\com\ohnosequences\xml\model\PredictedRnas.java]: ..\..\model\PredictedRnas.java.md
[main\java\com\ohnosequences\xml\model\uniprot\ArticleXML.java]: ..\..\model\uniprot\ArticleXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\CommentXML.java]: ..\..\model\uniprot\CommentXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\FeatureXML.java]: ..\..\model\uniprot\FeatureXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\InterproXML.java]: ..\..\model\uniprot\InterproXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\IsoformXML.java]: ..\..\model\uniprot\IsoformXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\KeywordXML.java]: ..\..\model\uniprot\KeywordXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\ProteinXML.java]: ..\..\model\uniprot\ProteinXML.java.md
[main\java\com\ohnosequences\xml\model\uniprot\SubcellularLocationXML.java]: ..\..\model\uniprot\SubcellularLocationXML.java.md
[main\java\com\ohnosequences\xml\model\util\Argument.java]: ..\..\model\util\Argument.java.md
[main\java\com\ohnosequences\xml\model\util\Arguments.java]: ..\..\model\util\Arguments.java.md
[main\java\com\ohnosequences\xml\model\util\Error.java]: ..\..\model\util\Error.java.md
[main\java\com\ohnosequences\xml\model\util\Execution.java]: ..\..\model\util\Execution.java.md
[main\java\com\ohnosequences\xml\model\util\FlexXMLWrapperClassCreator.java]: ..\..\model\util\FlexXMLWrapperClassCreator.java.md
[main\java\com\ohnosequences\xml\model\util\ScheduledExecutions.java]: ..\..\model\util\ScheduledExecutions.java.md
[main\java\com\ohnosequences\xml\model\util\XMLWrapperClass.java]: ..\..\model\util\XMLWrapperClass.java.md
[main\java\com\ohnosequences\xml\model\util\XMLWrapperClassCreator.java]: ..\..\model\util\XMLWrapperClassCreator.java.md
[main\java\com\ohnosequences\xml\model\wip\Region.java]: ..\..\model\wip\Region.java.md
[main\java\com\ohnosequences\xml\model\wip\WipPosition.java]: ..\..\model\wip\WipPosition.java.md
[main\java\com\ohnosequences\xml\model\wip\WipResult.java]: ..\..\model\wip\WipResult.java.md