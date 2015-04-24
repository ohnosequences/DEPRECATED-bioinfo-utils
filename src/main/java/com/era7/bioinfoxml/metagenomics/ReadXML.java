/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfoxml.metagenomics;

import com.ohnosequences.xml.api.model.XMLElement;
import com.ohnosequences.xml.api.model.XMLElementException;
import org.jdom2.Element;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
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
