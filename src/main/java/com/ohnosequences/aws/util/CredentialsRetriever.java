/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ohnosequences.aws.util;

import com.amazonaws.auth.BasicAWSCredentials;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class CredentialsRetriever {
    
    public static BasicAWSCredentials getBasicAWSCredentialsFromOurAMI() throws FileNotFoundException, IOException{      
        
        
        File file = new File("/opt/init/aws-credentials");
        
        return getCredentialsFromPropertiesFile(file);
    }
    
    
    public static BasicAWSCredentials getCredentialsFromPropertiesFile(File file) throws FileNotFoundException, IOException{
        String accessSt = "";
        String secretSt = "";
        
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while((line = reader.readLine()) != null){
            
            if( line.startsWith("AWSAccessKeyId=") ){
                
                accessSt = line.split("AWSAccessKeyId=")[1].trim();
                
            }else if(line.startsWith("AWSSecretKey=")){
                
                secretSt = line.split("AWSSecretKey=")[1].trim();
            }
        }
        
        reader.close();
        
        
        return new BasicAWSCredentials(accessSt, secretSt);
    }
    
}
