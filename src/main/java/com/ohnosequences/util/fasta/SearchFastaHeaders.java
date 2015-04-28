/*
 This program looks for the text provided in the headers of the input FASTA file, all entries for which the value provided
 is found are included in the output FASTA file.
 The parameters for the program are:

 1. Input FASTA file name
 2. Output FASTA file name
 3. String to look up for

 */

package com.ohnosequences.util.fasta;

import com.ohnosequences.util.Executable;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SearchFastaHeaders implements Executable {

    @Override
    public void execute(ArrayList<String> array) {
        String[] args = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            args[i] = array.get(i);
        }
        main(args);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("This program expects the followgin parameters: \n"
                    + "1. Input FASTA file name \n"
                    + "2. Output FASTA file name \n"
                    + "3. String to look up for");
        } else {

            String inFileString = args[0];
            String outFileString = args[1];
            String valueToLookUp = args[2];

            try {

                File inFile;
                File outFile;
                inFile = new File(inFileString);
                BufferedReader reader = new BufferedReader(new FileReader(inFile));

                outFile = new File(outFileString);
                FileWriter fileWriter = new FileWriter(outFile);
                BufferedWriter writer = new BufferedWriter(fileWriter);

                String line;
                boolean writeSeq = false;

                while ((line = reader.readLine()) != null) {

                    if(line.startsWith(">")) {
                        if(line.substring(1).indexOf(valueToLookUp) >= 0){
                            writeSeq = true;
                            writer.write(line + "\n");
                        }else{
                            writeSeq = false;
                        }
                    }else{
                        if(writeSeq){
                            writer.write(line + "\n");
                        }
                    }
                }

                System.out.println("Closing readers and writers...");
                reader.close();
                writer.close();
                System.out.println("Done!");
                System.out.println("Fasta file created with the name: " + outFileString);

            } catch (Exception ex) {
                Logger.getLogger(SearchFastaHeaders.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }

        }
    }

}