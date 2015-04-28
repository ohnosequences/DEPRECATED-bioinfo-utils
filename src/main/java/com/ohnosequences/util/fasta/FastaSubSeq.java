/*
 This program exports a subset of the sequence selected by the contig ID provided by the user that is included in the
 input `MULTIFASTA` file, building a new `MULTIFASTA` file with the name provided whose contents are exactly the same but
 the sequence of the selected contig. The sub-sequence is determined by the positions provided and the header of the new
 `FASTA` entry has the following format:
 Previous sample header:

 > \>tr|Q2GKU1|Q2GKU1_ANAPZ Anaplasma surface protein P55 (Asp55) OS=Anaplasma phagocytophilum (strain HZ) GN=asp55 PE=4 SV=1

 Updated sample header _(when selecting the fragment 1-100)_

 > \>|Fragment 1 - 100|tr|Q2GKU1|Q2GKU1_ANAPZ Anaplasma surface protein P55 (Asp55) OS=Anaplasma phagocytophilum (strain HZ) GN=asp55 PE=4 SV=1

 The parameters for the program are:

 1. Input FASTA filename
 2. Contig ID
 3. Subset initial position _(inclusive)_
 4. Subset final position _(inclusive)_
 5. Output resulting FASTA filename

 */

package com.ohnosequences.util.fasta;


import com.ohnosequences.util.Executable;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FastaSubSeq implements Executable {

    public static final int SEQUENCE_LINE_LEGTH = 60;

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

        if (args.length != 6) {
            System.out.println("This program expects the following parameters: \n"
                    + "1. Input FASTA filename \n"
                    + "2. Contig ID \n"
                    + "3. Subset initial position (inclusive) \n"
                    + "4. Subset final position (inclusive) \n"
                    + "5. Output resulting FASTA filename \n"
                    + "6. Include only the selected Contig in the output file (true/false)");
        } else {

            String inFileString = args[0];
            String outFileString = args[4];
            String contigID = args[1];
            boolean includeOnlySelectedContig = Boolean.parseBoolean(args[5]);

            boolean wrongNumberFormat = false;
            int startPosition = 0;
            int endPosition = 0;

            try {

                startPosition = Integer.parseInt(args[2]);
                endPosition = Integer.parseInt(args[3]);

            } catch (NumberFormatException ex) {
                System.out.println("The positions for the subset do not have a correct number format");
                ex.printStackTrace();
                wrongNumberFormat = true;
            }

            if (!wrongNumberFormat) {
                BufferedReader reader = null;
                try {

                    //We're substracting one to the initial index since the start position from the user's perspective
                    // is equal to 1
                    startPosition--;
                    // The end position is not changed since the String method substr() is exclusive for the final position

                    if (startPosition > endPosition) {
                        System.out.println("Initial position cannot be greater than final position");
                        System.exit(-1);
                    }else{

                        File inFile;
                        File outFile;
                        inFile = new File(inFileString);
                        reader = new BufferedReader(new FileReader(inFile));

                        outFile = new File(outFileString);
                        FileWriter fileWriter = new FileWriter(outFile);
                        BufferedWriter writer = new BufferedWriter(fileWriter);

                        String line;
                        StringBuilder seqBuilder = new StringBuilder();
                        String header = null;
                        boolean contigFound = false;
                        while ((line = reader.readLine()) != null) {

                            if(line.startsWith(">")){

                                if(contigFound){
                                    //---we already finished reading the sequence we're interested in----
                                    contigFound = false;
                                    if (endPosition > seqBuilder.length()) {
                                        System.out.println("The final position provided is greather than sequence length");
                                        throw new Exception("Wrong end position");
                                    } else {

                                        String subSeq = seqBuilder.substring(startPosition, endPosition);
                                        String newHeader = ">|Fragment " + (startPosition + 1) + " - " + endPosition + "|" + header.substring(1) + "\n";

                                        //Writing the new header
                                        writer.write(newHeader);

                                        //writing sequences
                                        FastaUtil.writeSequenceToFileInFastaFormat(subSeq, SEQUENCE_LINE_LEGTH, writer);
                                    }
                                }

                                String tempContigID = line.substring(1).trim();
                                if(tempContigID.equals(contigID)){
                                    contigFound = true;
                                    header = line;
                                }

                            }else{
                                if(contigFound){
                                    seqBuilder.append(line.trim());
                                }
                            }

                            if(!contigFound){
                                if(!includeOnlySelectedContig){
                                    writer.write(line + "\n");
                                }
                            }

                        }

                        if(contigFound){
                            if (endPosition > seqBuilder.length()) {
                                System.out.println("The final position provided is greather than sequence length");
                                throw new Exception("Wrong end position");
                            } else {

                                String subSeq = seqBuilder.substring(startPosition, endPosition);
                                String newHeader = ">|Fragment " + (startPosition + 1) + " - " + endPosition + "|" + header.substring(1) + "\n";

                                //Writing the new header
                                writer.write(newHeader);

                                //writing sequences
                                FastaUtil.writeSequenceToFileInFastaFormat(subSeq, SEQUENCE_LINE_LEGTH, writer);
                            }
                        }

                        System.out.println("Closing readers and writers...");
                        reader.close();
                        writer.close();
                        System.out.println("Done!");

                        System.out.println("Fasta file created with the name: " + outFileString);

                    }


                } catch (Exception ex) {
                    Logger.getLogger(FastaSubSeq.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                }
            }


        }
    }

}