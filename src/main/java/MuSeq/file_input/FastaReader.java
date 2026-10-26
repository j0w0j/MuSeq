package MuSeq.file_input;

import MuSeq.model.MidiData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Can read a FASTA file using the BufferedReader.
 */
public class FastaReader {
    /**
     * Reads a FASTA file.
     * Each line is added to MidiData class, which converts it to MIDI notes.
     *
     * @param filepath: path to the FASTA file to read, as String.
     * @param midiData: Class which stores converts the FASTA to MIDI, and then stores it.
     */
    public void readFasta(String filepath, MidiData midiData) throws IOException {
        // https://www.w3schools.com/java/java_bufferedreader.asp
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;

            // read fasta file line by line (non-empty lines only)
            while ((line = br.readLine()) != null) {

                line = validateLineFromFasta(line);

                // skip empty or header lines
                if (line == null) {
                    continue;
                }

                // MidiData converts Fasta line to Midi data and stores it
                midiData.addFromFastaLine(line);
            }
        } catch (FileNotFoundException e) {
            // specifically if the file could not be found
            throw new FileNotFoundException("Input file could not be found: " + filepath);
        } catch (IOException e) {
            // for other errors that might occur
            throw new IOException("Error reading file: " + filepath);
        }
    }

    /**
     * Validate a line from the input FASTA file:
     * <ul>
     *     <li>Leading and trailing space removed from each line and converts all to uppercase.</li>
     *     <li>Empty or header lines return null.</li>
     *     <li>Raises and error if input is not valid FASTA format.</li>
     * </ul>
     * @param line            Current line from the input FASTA file, as String.
     * @return null for empty or header lines, or a new line as String.
     */
    private String validateLineFromFasta(String line) {
        // remove leading and trailing space (similar to python's .strip())
        line = line.trim();

        // return null for empty or header lines
        if (line.startsWith(">") | line.isEmpty()) {
            return null;
        }

        // convert line to uppercase.
        line = line.toUpperCase();

        // check line for invalid FASTA format. throw an error if it is invalid.
        if (containsInvalidFastaFormat(line)) {
            throw new IllegalArgumentException("Invalid FASTA format in input file!\n" +
                    "Line:\n" + line);
        }

        // Remove any characters that are NOT A, C, G, T or U.
        line = removeAmbiguousNucleotides(line);

        return line;
    }

    /**
     * Check if a line is a valid FASTA sequence.
     * A sequence containing a character that is not IUPAC nucleotide code is deemed invalid.
     * Nucleotide codes are used from: <a href="https://www.bioinformatics.org/sms/iupac.html">https://www.bioinformatics.org/sms/iupac.html</a>
     * More info about IUPAC codes:
     * <ol>
     *     <li><a href="https://genome.ucsc.edu/goldenPath/help/iupac.html">https://genome.ucsc.edu/goldenPath/help/iupac.html</a></li>
     *     <li><a href="https://olvtools.com/en/documents/fasta">https://olvtools.com/en/documents/fasta</a></li>
     *     <li><a href="https://en.wikipedia.org/wiki/FASTA_format#Sequence_representation">https://en.wikipedia.org/wiki/FASTA_format#Sequence_representation</a></li>
     *     <li><a href="https://www.dnabaser.com/articles/IUPAC%20ambiguity%20codes.html">https://www.dnabaser.com/articles/IUPAC%20ambiguity%20codes.html</a></li>
     *</ol>
     *
     * @param line FASTA line to check, as String.
     * @return true if the line is invalid, false if the line is valid.
     */
    private boolean containsInvalidFastaFormat(String line) {
        // matches any character that is NOT IUPAC nucleotide code (from https://www.bioinformatics.org/sms/iupac.html).
        Pattern pattern = Pattern.compile("[^ACGTURYSWKMBDHVN.-]+");
        // matches if it finds at least one character.
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }

    /**
     * Remove any characters that are NOT A, C, G, T or U.
     *
     * @param line String to remove characters from
     * @return new String. A filtered line with the characters removed. Returns null if no characters remain.
     */
    private String removeAmbiguousNucleotides(String line) {
        // Matches characters A, C, G, T or U. Must atleast match one or more.
        Pattern pattern = Pattern.compile("[ACGTU]+");
        Matcher matcher = pattern.matcher(line);

        // empty string builder to append matches to.
        StringBuilder filteredLine = new StringBuilder();

        // https://www.geeksforgeeks.org/java/regular-expressions-in-java/
        // get all matches and append to make new filtered line.
        while (matcher.find()) {
            String match = matcher.group();
            filteredLine.append(match);
        }

        // return null if no characters remain.
        if (filteredLine.isEmpty()) {
            return null;
        }

        return filteredLine.toString();
    }
}

