/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bioinfoutil.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public class MD5 {

    public static String generateMD5(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        
        byte[] bytesOfMessage = string.getBytes("UTF-8");

        MessageDigest md = MessageDigest.getInstance("MD5");
        return new String(md.digest(bytesOfMessage));        
        
    }
}
