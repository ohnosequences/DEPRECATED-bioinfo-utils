
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
package com.ohnosequences.xml.model.util;

import com.ohnosequences.xml.api.model.XMLElement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.jdom2.Element;

/**
 *
 * @author ppareja
 */
public class XMLWrapperClassCreator {

    public static final String NECESSARY_IMPORTS = "import com.ohnosequences.xml.api.model.XMLElement;\nimport com.ohnosequences.xml.api.model.XMLElementException;"
            + "\nimport java.util.ArrayList;\nimport java.util.List;\nimport org.jdom2.Element;\n\n";
    public static final String VARS_PREFIX = "public static final String ";
    private static final String CLASS_NAME_CONSTRUCTOR_VAR = "lalala";
    private static final String CONSTRUCTORS_STR = "public " + CLASS_NAME_CONSTRUCTOR_VAR + "(){\n"
            + "super(new Element(TAG_NAME));\n"
            + "\n}\npublic " + CLASS_NAME_CONSTRUCTOR_VAR + "(Element elem) throws XMLElementException{\nsuper(elem);\n"
            + "if(!elem.getName().equals(TAG_NAME)){\nthrow new XMLElementException(XMLElementException.WRONG_TAG_NAME,new XMLElement(elem));\n"
            + "}\n}\npublic " + CLASS_NAME_CONSTRUCTOR_VAR + "(String value) throws Exception{\nsuper(value);\n"
            + "if(!root.getName().equals(TAG_NAME)){\nthrow new XMLElementException(XMLElementException.WRONG_TAG_NAME,new XMLElement(value));\n"
            + "}\n}";
    private static final String GETTERS_STR = "\n//----------------GETTERS-------------------\n";
    private static final String SETTERS_STR = "\n//----------------SETTERS-------------------\n";

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("El programa espera un parametro: \n"
                    + "1. Nombre del archivo xml con la descripcion de la clase \n");
        } else {
            File inFile = new File(args[0]);

            try {

                BufferedReader reader = new BufferedReader(new FileReader(inFile));
                String line = null;
                StringBuilder stBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    stBuilder.append(line);
                }
                reader.close();

                XMLElement classes = new XMLElement(stBuilder.toString());
                List<Element> list = classes.getRoot().getChildren(XMLWrapperClass.TAG_NAME);

                for (Element elem : list) {
                    XMLWrapperClass wrapper = new XMLWrapperClass(elem);

                    //primero creo los directorios del package
                    String[] packageSplit = wrapper.getPackage().split("\\.");
                    String currentFolder = "./";
                    for (int i = 0; i < packageSplit.length; i++) {
                        String string = packageSplit[i];
                        currentFolder += string;
                        System.out.println("currentFolder = " + currentFolder);
                        File tempFile = new File(currentFolder);
                        if(!tempFile.exists()){
                            tempFile.mkdir();
                            System.out.println("no existe: tempFile = " + tempFile);
                        }else{
                            System.out.println("existe: tempFile = " + tempFile);
                        }
                        currentFolder += "/";

                    }

                    BufferedWriter outBuff = new BufferedWriter(new FileWriter(new File(currentFolder + wrapper.getClassName() + ".java")));
                    outBuff.write("package " + wrapper.getPackage() + ";\n\n");
                    outBuff.write(NECESSARY_IMPORTS);
                    outBuff.write("public class " + wrapper.getClassName() + " extends XMLElement{\n\n");
                    outBuff.write("public static final String TAG_NAME = \"" + wrapper.getTagName() + "\";\n\n");

                    HashMap<String, String> varsDeclarationNames = new HashMap<String, String>();
                    HashMap<String, String> varsTypes = new HashMap<String, String>();

                    List<Element> vars = wrapper.getVars().getChildren();
                    for (Element element : vars) {
                        outBuff.write(VARS_PREFIX + " ");
                        String varName = element.getText();
                        String varDeclarationName = "";
                        for (int i = 0; i < varName.length(); i++) {
                            char c = varName.charAt(i);
                            if (Character.isUpperCase(c)) {
                                varDeclarationName += "_" + c;
                            } else {
                                varDeclarationName += ("" + c).toUpperCase();
                            }
                        }
                        varDeclarationName = varDeclarationName + "_TAG_NAME";
                        varsDeclarationNames.put(varName, varDeclarationName);
                        varsTypes.put(varName, element.getName());

                        //En este ciclo cambio la mayuscula del nombre de la variable por '_'
                        String tempTagName = "";
                        for (int i = 0; i < varName.length(); i++) {
                            char c = varName.charAt(i);
                            if (Character.isUpperCase(c)) {
                                tempTagName += "_" + ("" + c).toLowerCase();
                            } else {
                                tempTagName += "" + c;
                            }

                        }

                        outBuff.write(varDeclarationName + " = \"" + tempTagName + "\";\n");
                    }

                    //Ahora la parte de los constructores
                    outBuff.write("\n" + CONSTRUCTORS_STR.replaceAll(CLASS_NAME_CONSTRUCTOR_VAR, wrapper.getClassName()));


                    Set<String> varsKeySet = varsDeclarationNames.keySet();
                    //A rellenar los getters!
                    outBuff.write("\n" + GETTERS_STR);
                    for (String key : varsKeySet) {

                        String varType = varsTypes.get(key);
                        String getStr = " get" + key.substring(0, 1).toUpperCase() + key.substring(1);
                        outBuff.write("\npublic " + varType + getStr + "(){\treturn ");
                        if (varType.equals("int")) {
                            outBuff.write("Integer.parseInt(getNodeText(" + varsDeclarationNames.get(key) + "));}");
                        } else if (varType.equals("double")) {
                            outBuff.write("Double.parseDouble(getNodeText(" + varsDeclarationNames.get(key) + "));}");
                        } else if (varType.equals("float")) {
                            outBuff.write("Float.parseFloat(getNodeText(" + varsDeclarationNames.get(key) + "));}");
                        } else if (varType.equals("String")) {
                            outBuff.write("getNodeText(" + varsDeclarationNames.get(key) + ");}");
                        } else if (varType.equals("boolean")){
                            outBuff.write("Boolean.parseBoolean(getNodeText(" + varsDeclarationNames.get(key) + "));}");
                        }

                    }

                    //A rellenar los setters!
                    outBuff.write("\n" + SETTERS_STR);
                    for (String key : varsKeySet) {
                        String varType = varsTypes.get(key);
                        String setStr = " set" + key.substring(0, 1).toUpperCase() + key.substring(1);
                        outBuff.write("\npublic void " + setStr + "(" + varType + " value){\t setNodeText("
                                + varsDeclarationNames.get(key) + ", ");
                        if (varType.equals("String")) {
                            outBuff.write("value");
                        } else {
                            outBuff.write("String.valueOf(value)");
                        }
                        outBuff.write(");}");
                    }


                    //Llave que cierra la clase
                    outBuff.write("\n}\n");

                    outBuff.close();
                }




            } catch (Exception e) {
                e.printStackTrace();
            }

        }
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
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jNodeIndexXML.java]: ../bio4j/Bio4jNodeIndexXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jNodeXML.java]: ../bio4j/Bio4jNodeXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jPropertyXML.java]: ../bio4j/Bio4jPropertyXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jRelationshipIndexXML.java]: ../bio4j/Bio4jRelationshipIndexXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/Bio4jRelationshipXML.java]: ../bio4j/Bio4jRelationshipXML.java.md
[main/java/com/ohnosequences/xml/model/bio4j/UniprotDataXML.java]: ../bio4j/UniprotDataXML.java.md
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
[main/java/com/ohnosequences/xml/model/util/Argument.java]: Argument.java.md
[main/java/com/ohnosequences/xml/model/util/Arguments.java]: Arguments.java.md
[main/java/com/ohnosequences/xml/model/util/Error.java]: Error.java.md
[main/java/com/ohnosequences/xml/model/util/Execution.java]: Execution.java.md
[main/java/com/ohnosequences/xml/model/util/FlexXMLWrapperClassCreator.java]: FlexXMLWrapperClassCreator.java.md
[main/java/com/ohnosequences/xml/model/util/ScheduledExecutions.java]: ScheduledExecutions.java.md
[main/java/com/ohnosequences/xml/model/util/XMLWrapperClass.java]: XMLWrapperClass.java.md
[main/java/com/ohnosequences/xml/model/util/XMLWrapperClassCreator.java]: XMLWrapperClassCreator.java.md
[main/java/com/ohnosequences/xml/model/wip/Region.java]: ../wip/Region.java.md
[main/java/com/ohnosequences/xml/model/wip/WipPosition.java]: ../wip/WipPosition.java.md
[main/java/com/ohnosequences/xml/model/wip/WipResult.java]: ../wip/WipResult.java.md