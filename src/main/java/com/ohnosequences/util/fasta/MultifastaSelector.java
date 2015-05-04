/*
This program filters a MULTIFASTA file based on the ids provided by means of the TSV file.
The result of that filtering is exported to a new MULTIFASTA file.
There also exists the possibility of specifying that the sequence of a selected entry must be exported in an 'reversed' form.
In order to do that, the letter 'R' should be specified on the second column of the TSV file besides the id on entry that must be included with its sequence reversed.

The parameters for the program are:

1. Input Multifasta file name of the raw sequence file
2. Input TSV file name of selector file (two tab delimited file)"
3. Outupt Multifasta file name

 */
package com.ohnosequences.util.fasta;

import com.ohnosequences.util.Executable;
import com.ohnosequences.util.seq.SeqUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author <a href="mailto:ppareja@ohnosequences.com">Pablo Pareja-Tobes</a>
 */
public class MultifastaSelector implements Executable{

    public static final String REVERSED_ST = "R";
    public static final int SEQUENCE_LINE_LENGTH = 60;
    
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
            System.out.println("This program expects 3 paramaters: \n"
                    + "1. Input Multifasta file name of the raw sequence file \n"
                    + "2. Input TSV file name of selector file (two tab delimited file) \n"
                    + "3. Outupt Multifasta file name \n");
        } else {

            File inMultifastaFile = new File(args[0]);
            File inSelectorFile = new File(args[1]);
            File outMultifastaFile = new File(args[2]);

            try {

                System.out.println("Initializing buffered writer...");
                BufferedWriter outBuff = new BufferedWriter(new FileWriter(outMultifastaFile));
                System.out.println("done!");

                HashMap<String, String> selectorValuesMap = new HashMap<String, String>();
                HashSet<String> fastaIds = new HashSet<String>();

                System.out.println("Reading selector file...");
                BufferedReader reader = new BufferedReader(new FileReader(inSelectorFile));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    String[] columns = line.split("\t");
                    selectorValuesMap.put(columns[0].trim(), columns[1].trim());
                    fastaIds.add(columns[0].trim());
                }
                reader.close();

                System.out.println("done!");

                System.out.println("Reading multifasta input file...");

                reader = new BufferedReader(new FileReader(inMultifastaFile));
                line = reader.readLine();

                while (line != null) {

                    if (line.charAt(0) == '>') {

                        String header = line;
                        boolean included = false;
                        String currentID = "";

                        for (String fastaId : fastaIds) {

                            if (line.indexOf(fastaId) >= 0) {
                                //This fasta elem must be included in the output                                
                                included = true;
                                currentID = fastaId;
                                break;
                            }
                        }


                        if (included) {

                            String seq = "";
                            line = reader.readLine();
                            //System.out.println("line = " + line);
                            while (! (line.charAt(0) == '>')) {
                                seq += line;
                                line = reader.readLine();
                                if(line == null){
                                    //This is when we already are at the end of the file
                                    break;
                                }
                            }

                            seq = seq.toUpperCase();

                            if (selectorValuesMap.get(currentID).equals(REVERSED_ST)) {
                                
                                seq = SeqUtil.getComplementaryInverted(seq).toUpperCase();
                                
                            }

                            System.out.println("writing header for " + currentID);
                            outBuff.write(header + "\n");

                            //---writing sequence----
                            FastaUtil.writeSequenceToFileInFastaFormat(seq, SEQUENCE_LINE_LENGTH, outBuff);
                            
                            //Escribir las lineas de la sub-secuencia
//                            for (int counter = 0; counter < seq.length(); counter += SEQUENCE_LINE_LENGTH) {
//                                if (counter + SEQUENCE_LINE_LENGTH > seq.length()) {
//                                    outBuff.write(seq.substring(counter, seq.length()) + "\n");
//                                } else {
//                                    outBuff.write(seq.substring(counter, counter + SEQUENCE_LINE_LENGTH) + "\n");
//                                }
//                            }
                            
                        }else{

                            //just read lines till I find the next header
                            boolean keepReading = true;
                            do{
                                line = reader.readLine();
                                if(line == null){
                                    keepReading = false;
                                }else if((line.charAt(0) == '>')){
                                    keepReading = false;
                                }
                            }while(keepReading);
                        }
                    }
                }

                reader.close();
                outBuff.close();


                System.out.println("Output file created successfully!! :D");


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
