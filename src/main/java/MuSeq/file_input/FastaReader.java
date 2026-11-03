package MuSeq.file_input;

import MuSeq.model.MidiData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;
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
     * @param filepath Path to the FASTA file to read, as String.
     * @param midiData Class which converts the FASTA to MIDI, and then stores it.
     */
    public void readFasta(String filepath, MidiData midiData) throws IOException {
        readFasta(filepath, midiData, null);
    }


    /**
     * Reads a FASTA file.
     * Each line is added to MidiData class, which converts it to MIDI notes.
     *
     * @param filepath Path to the FASTA file to read, as String.
     * @param midiData Class which converts the FASTA to MIDI, and then stores it.
     * @param chromosome chromosome ID to check for (e.g. 5, 2L, Y or MT).
     */
    public void readFasta(String filepath, MidiData midiData, String chromosome) throws IOException {

        boolean sequenceIsFromSelectedChromosome = false;
        boolean isChromosomeFound = false;

        try {
            // if file is large, ask user whether to continue.
            checkFileSize(filepath, 50 * 1024 * 1024);

            // https://www.w3schools.com/java/java_bufferedreader.asp
            try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
                String line;

                // read fasta file line by line
                while ((line = br.readLine()) != null) {

                    // remove leading and trailing space (similar to python's .strip())
                    line = line.trim();

                    // check header lines for chromosome and then skip the line.
                    if (line.startsWith(">")) {
                        if (chromosome != null) {
                            System.out.println(line);
                            sequenceIsFromSelectedChromosome = containsChromosome(line, chromosome);
                            if (sequenceIsFromSelectedChromosome) {
                                isChromosomeFound = true;
                            }
                        }
                        continue;
                    }

                    // skip the line if it is not from the user selected chromosome
                    if (chromosome != null & !sequenceIsFromSelectedChromosome) {
                        continue;
                    }

                    line = validateLineFromFasta(line);

                    // skip empty lines
                    if (line == null) {
                        continue;
                    }

                    // MidiData converts Fasta line to Midi data and stores it
                    midiData.addFromFastaLine(line);
                }
            }
        } catch (FileNotFoundException | NoSuchFileException e) {
            // specifically if the file could not be found
            throw new FileNotFoundException("Input file could not be found: " + filepath);
        } catch (IOException e) {
            // for other errors that might occur
            throw new IOException("Error reading file: " + filepath);
        }
        if (!isChromosomeFound) {
            throw new IllegalArgumentException("Invalid chromosome input!\n" +
                    String.format("Chromosome '%s' is not in the input file!", chromosome));
        }
    }

    /**
     * Validate a line from the input FASTA file. For each line:
     * <ul>
     *     <li>Converts all characters to uppercase.</li>
     *     <li>Raises and error if input is not valid FASTA format.</li>
     *     <li>Remove all ambiguous nucleotides, so only A, C, G, T or U remain.</li>
     * </ul>
     *
     * @param line Current line from the input FASTA file, as String.
     * @return A new line as String. Empty lines return null.
     */
    private String validateLineFromFasta(String line) {

        // return null for empty lines
        if (line.isEmpty()) {
            return null;
        }

        // convert line to uppercase.
        line = line.toUpperCase();

        // check line for invalid FASTA format. throw an error if it is invalid.
        if (containsInvalidFastaFormat(line)) {
            throw new IllegalArgumentException("Invalid FASTA format in input file!\n" +
                    "Line:\n" + line);
        }

        // Remove any characters that are NOT A, C, G, T or U. (Returns null if no characters remain.)
        line = removeAmbiguousNucleotides(line);

        return line;
    }

    // matches any character that is NOT IUPAC nucleotide code (from https://www.bioinformatics.org/sms/iupac.html).
    // matches if it finds at least one character.
    private final Pattern invalidFastaLinePattern = Pattern.compile("[^ACGTURYSWKMBDHVN.-]+");

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
     * </ol>
     *
     * @param line FASTA line to check, as String.
     * @return true if the line is invalid, false if the line is valid.
     */
    private boolean containsInvalidFastaFormat(String line) {
        Matcher matcher = invalidFastaLinePattern.matcher(line);
        return matcher.find();
    }

    // matches characters A, C, G, T or U. matches if it finds at least one character.
    private final Pattern validNucleotidesPattern = Pattern.compile("[ACGTU]+");

    /**
     * Remove any characters that are NOT A, C, G, T or U.
     *
     * @param line String to remove characters from
     * @return new String. A filtered line with the characters removed. Returns null if no characters remain.
     */
    private String removeAmbiguousNucleotides(String line) {
        Matcher matcher = validNucleotidesPattern.matcher(line);

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

    /**
     * Check if a FASTA header contains a specific chromosome.
     * @param header Header line to check for chromosome
     * @param chromosome chromosome ID to check for (e.g. 5, 22, Y or MT).
     * @return true if the chromosome is found. false if it is not found.
     */
    private boolean containsChromosome(String header, String chromosome) {

        // pattern matches chromosome ID, NOT followed by any number
        // example: chromosome 3R will match chromosome 3, but chromosome 1 will not match chromosome 12.
        String chromosomePatternString = String.format("chromosome %s[^0-9]", chromosome);
        Pattern chromosomePattern = Pattern.compile(chromosomePatternString, Pattern.CASE_INSENSITIVE);
        Matcher matcher = chromosomePattern.matcher(header);
        return matcher.find();
    }

    /**
     * If the file is above the given size, show the user a warning.
     * User is prompted to choose whether to proceed or not.
     *
     * @param filepath          Path of the file to check.
     * @param sizeToCompare     File size to compare the given file to.
     */
    private void checkFileSize(String filepath, int sizeToCompare) throws IOException {
        // Source - https://stackoverflow.com/a/8721276
        Path path = Paths.get(filepath);
        long fileSize = Files.size(path);

        if (fileSize >= sizeToCompare) {
            Scanner myObj = new Scanner(System.in);
            System.out.println("This file might be (very) large. Are you sure you want to continue? (y/n)");
            String userInput = myObj.nextLine().toUpperCase();

            if (userInput.equals("Y")) {
                return;
            } else if (userInput.equals("N")) {
                System.exit(0);
            } else {
                System.out.println("Invalid answer. Please answer only y or n.");
                checkFileSize(filepath, sizeToCompare);
            }
        }
    }
}

