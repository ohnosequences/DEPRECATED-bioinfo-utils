/*
 This program looks for the text provided in the sequences included in the input FASTA file, all entries for which the value provided
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


public class SearchFastaSequence implements Executable {

    public static final int FASTA_LINE_LENGTH = 60;

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
            System.out.println("This program expects the following parameters: \n"
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
                String lastID = null;
                StringBuilder stBuilder = new StringBuilder();
                boolean writeEntry = false;


                while ((line = reader.readLine()) != null) {

                    if(line.startsWith(">")) {
                        if(lastID != null){
                            String sequence = stBuilder.toString();
                            int index = sequence.toLowerCase().indexOf(valueToLookUp.toLowerCase());
                            if(index >= 0){
                                writer.write(lastID + "\n");
                                writer.write(FastaUtil.formatSequenceWithFastaFormat(sequence, FASTA_LINE_LENGTH) + "\n");
                            }
                            stBuilder.delete(0, stBuilder.length());
                        }
                        lastID = line;

                    }else{
                        stBuilder.append(line.trim());
                    }
                }

                //-------------we still have to check the last sequence-------------------------
                if(lastID != null){
                    String sequence = stBuilder.toString();
                    int index = sequence.toLowerCase().indexOf(valueToLookUp.toLowerCase());
                    if(index >= 0){
                        writer.write(lastID + "\n");
                        writer.write(FastaUtil.formatSequenceWithFastaFormat(sequence, FASTA_LINE_LENGTH) + "\n");
                    }
                }

                System.out.println("Closing readers and writers...");
                reader.close();
                writer.close();
                System.out.println("Done!");
                System.out.println("Fasta file created with the name: " + outFileString);

            } catch (Exception ex) {
                Logger.getLogger(FastaSubSet.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }

        }
    }

}