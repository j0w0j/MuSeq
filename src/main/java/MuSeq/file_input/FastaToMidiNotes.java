package MuSeq.file_input;

import MuSeq.translator.LettersToNumbers;
import MuSeq.translator.NumberToNote;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Contains a method to convert FASTA files to MIDI files.
 */
public class FastaToMidiNotes {
    /**
     * Reads a FASTA file and converts it from nucleotides to MIDI notes (integers).
     *
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

                line = fastaLineHandler(line, codonsToConvert);
                // skip empty or header lines
                if (line == null) {
                    continue;
                }

                // get amount of times line needs to be iterated over, to be split up into codons
                // this is rounded up, so last codonsToConvert is most likely not of length 9
                int iterateAmount = (int) Math.ceil((double) line.length() / 9);

                System.out.println(line); // test printje TODO dit weghalen

                // iterate over each line to split up into sections 9 nucleotides (3 codons)
                for (int i = 0; i < iterateAmount; i++) {
                    // check if current section of the line is long enough (>=9)
                    if (i * 9 + 9 < line.length()) {
                        codonsToConvert = line.substring(i * 9, i * 9 + 9);

                        // convert the codons
                        ArrayList<Integer> codonNote = convertCodonsToMidi(codonsToConvert);

                        // add converted codons to ArrayList to return
                        midiNotes.addAll(codonNote);
                    }
                    // if current section of the line is not long enough, get a smaller piece (<9).
                    // this will be added to the start of the next line instead
                    else {
                        codonsToConvert = line.substring(i * 9);
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

    /**
     * Handles a line from the input FASTA file.
     * Also adds unused nucleotides from previous line to the beginning of the current line.
     * Leading and trailing space removed from each line and converts all to uppercase.
     * Empty or header lines return null.
     * Raises and error if input is not valid FASTA format.
     *
     * @param line            Current line from the input FASTA file, as String.
     * @param codonsToConvert Unused nucleotides from the previous line of the input FASTA file, as String.
     * @return null for empty or header lines, or a new line as String.
     */
    private String fastaLineHandler(String line, String codonsToConvert) {
        // remove leading and trailing space (similar to python's .strip())
        line = line.trim().toUpperCase();

        // return null for empty or header lines
        if (line.startsWith(">") | line.isEmpty()) {
            return null;
        }

        // check line for invalid FASTA format. throw an error if it is invalid.
        if (containsInvalidFastaFormat(line)) {
            throw new IllegalArgumentException("Invalid FASTA format in input file!\n" +
                    "Line:\n" + line);
        }

        // if previous condonsToConvert was not long enough, add the beginning of the current line
        if (codonsToConvert.length() < 9) {
            line = codonsToConvert.concat(line);
        }

        return line;
    }

    /**
     * Converts a String of 3 codons (9 nucleotides) to an ArrayList of integers used as Midi data.
     *
     * @param codonsToConvert String of 3 codons (9 nucleotides).
     * @return ArrayList of integers (used as Midi data).
     */
    private ArrayList<Integer> convertCodonsToMidi(String codonsToConvert) {
        System.out.println(codonsToConvert); // test printje TODO

        //first class resulting in 3 numbers
        LettersToNumbers lettersToNumbers = new LettersToNumbers();
        ArrayList<Integer> codonInt = lettersToNumbers.codonTranslate(codonsToConvert);
        System.out.println("het getal gemaakt is " + codonInt);

        //second class resulting in 3 new numbers
        NumberToNote numberToNote = new NumberToNote();
        ArrayList<Integer> codonNote = numberToNote.devideNumbers(codonInt);
        System.out.println(codonNote);
        return codonNote;
    }

    /**
     * Check if a line is a valid FASTA format.
     * Raises an error if not.
     *
     * @param line FASTA line to check, as String.
     */
    private boolean containsInvalidFastaFormat(String line) {
        // the pattern matches any character that is NOT A, C, G, T or U.
        // matches if it finds at least one character.
        Pattern pattern = Pattern.compile("[^ACGTU]+");
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }
}

