package MuSeq.inputhandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Documentatie TODO
 *
 * https://www.w3schools.com/java/java_bufferedreader.asp
 */
public class FastaReader {
    void readFasta(String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            String codonsToConvert = "";

            // read fasta file line by line (non-empty lines only)
            while ((line = br.readLine()) != null) {

                // skip header lines
                if (line.startsWith(">")) {
                    continue;
                }

                // if previous condonsToConvert was not long enough, add the beginning of the current line
                if (codonsToConvert.length() < 9) {
                    line = codonsToConvert.concat(line);
                }

                // get amount of times line needs to be iterated over, to be split up into codons
                // this is rounded up, so last codonsToConvert is most likely not of length 9
                int iterateAmount = (int) Math.ceil((double) line.length() / 9);

                System.out.println(line); // test printje TODO dit weghalen

                // iterate over each line to split up into sections 9 nucleotides (3 codons)
                for (int i = 0; i < iterateAmount; i++) {
                    // check if current section of the line is long enough (>=9)
                    if (i*9+9 < line.length()) {
                        codonsToConvert = line.substring(i*9, i*9+9);
                        // TODO hier zou dan code moeten komen (translator en output classes methodes) om het om te zetten naar nummers en uiteindelijk naar MIDI??
                        System.out.println(codonsToConvert); // test printje TODO dit vervangen met code wat daadwerklijk iets doet
                    }
                    // if current section of the line is not long enough, get a smaller piece (<9).
                    // this will be added to the start of the next line instead
                    else {
                        codonsToConvert = line.substring(i*9);
                    }
                }
            }
        } catch (IOException e) {
            // TODO dit is nu nog een hele generieke error, misschien meerdere errors toevoegen zodat het duidelijker is wat er misgaat
            //  bijvoorbeeld error voor als het bestand ontbreekt of als bestand geen (valide) fasta is.
            System.out.println("Error reading file.");
        }
    }
}

