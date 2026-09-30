package MuSeq.file_input;

import MuSeq.translator.LettersToNumbers;
import MuSeq.translator.NumberToNote;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Contains a method to convert FASTA files to MIDI files.
 */
public class FastaToMidiNotes {
    /**
     * Reads a FASTA file and converts it from nucleotides to MIDI notes (integers).
     * @param filepath: path to the FASTA file to read, as String.
     */
    public ArrayList<Integer> convertFastaToMidiNotes(String filepath) {
        // https://www.w3schools.com/java/java_bufferedreader.asp
        ArrayList<Integer> midiNotes = new ArrayList<Integer>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            String codonsToConvert = "";

            // read fasta file line by line (non-empty lines only)
            while ((line = br.readLine()) != null) {

                // remove leading and trailing space (similair to python's .strip())
                line = line.trim();

                // skip header or empty lines
                if (line.startsWith(">") | line.isEmpty()) {
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
                        codonsToConvert = line.substring(i*9, i*9+9).toUpperCase();

                        System.out.println(codonsToConvert); // test printje TODO

                        //first class resulting in 3 numbers
                        LettersToNumbers lettersToNumbers = new LettersToNumbers();
                        ArrayList<Integer> codonInt = lettersToNumbers.codonTranslate(codonsToConvert);
                        System.out.println("het getal gemaakt is " + codonInt);

                        //second class resulting in 3 new numbers
                        NumberToNote numberToNote = new NumberToNote();
                        ArrayList<Integer> codonNote = numberToNote.devideNumbers(codonInt);
                        System.out.println(codonNote);

                        midiNotes.addAll(codonNote);
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
        return midiNotes;
    }
}

