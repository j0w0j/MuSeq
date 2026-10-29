package MuSeq.model;

import MuSeq.translator.LettersToNumbers;
import MuSeq.translator.NumberToNote;

import java.util.ArrayList;

public class MidiData {

    private ArrayList<Integer> midiNotesPitch = new ArrayList<Integer>();
    private ArrayList<Integer> midiNotesLength = new ArrayList<Integer>();
    private ArrayList<Integer> midiNotesVolume = new ArrayList<Integer>();

    private final int metadataAmount;

    private ArrayList<Integer> midiMetadata = new ArrayList<Integer>();

    // used for translating FASTA to MIDI
    private LettersToNumbers lettersToNumbers = new LettersToNumbers();
    private NumberToNote numberToNote = new NumberToNote();

    /**
     *
     * @param metadataAmount Amount of codons reserved for the metadata
     */
    public MidiData(int metadataAmount) {
        this.metadataAmount = metadataAmount;
    }

    /**
     * Unused nucleotides from the previous line of the input FASTA file.
     */
    private String unusedNucleotides = "";


    /**
     * Translate the FASTA line to MIDI notes, and then add the MIDI notes to the midiNotes ArrayList.
     * @param line Line from the FASTA file, as String.
     */
    public void addFromFastaLine(String line) {
        // Add unused nucleotides from previous line to the beginning of the current line.
        if (!unusedNucleotides.isEmpty()) {
            line = unusedNucleotides.concat(line);
            unusedNucleotides = "";
        }

        if (midiMetadata.size() < metadataAmount) {
            splitFastaLineIntoMetadata(line);
        } else {
            splitFastaLineIntoNotes(line);
        }
    }


    private void splitFastaLineIntoMetadata(String line) {
        // split line into sections of maximum 3 nucleotides (1 codon).
        // iterateAmount is the amount of sections in the line.
        int iterateAmount = (int) Math.ceil((double) line.length() / 3);

        // iterate over each section.
        for (int i = 0; i < iterateAmount; i++) {

            // stop adding to metadata if it is full!
            if (midiMetadata.size() >= metadataAmount) {
                unusedNucleotides = line.substring(i * 3);
            }

            // check if current section of the line is long enough (>=9) to be converted.
            if (i * 3 + 3 <= line.length()) {
                String codonToConvert = line.substring(i * 3, i * 3 + 3);

                // convert codons to numbers
                Integer translatedCodon = lettersToNumbers.codonTranslate(codonToConvert).getFirst();

                // add converted codon to metadata.
                midiMetadata.add(translatedCodon);

            }
            // if current section of the line is not long enough, get a smaller piece (<9).
            // this will be added to the start of the next line instead
            else {
                unusedNucleotides = line.substring(i * 3);
            }
        }
    }


    private void splitFastaLineIntoNotes(String line) {

        // split line into sections of maximum 9 nucleotides (3 codons).
        // iterateAmount is the amount of sections in the line.
        int iterateAmount = (int) Math.ceil((double) line.length() / 9);

        // iterate over each section.
        for (int i = 0; i < iterateAmount; i++) {
            // check if current section of the line is long enough (>=9) to be converted.
            if (i * 9 + 9 <= line.length()) {
                String codonsToConvert = line.substring(i * 9, i * 9 + 9);

                // convert the codons
                // codonNote: {pitch, length, volume}
                ArrayList<Integer> codonNote = convertCodonsToMidi(codonsToConvert);

                this.midiNotesPitch.add(codonNote.get(0));
                this.midiNotesLength.add(codonNote.get(1));
                this.midiNotesVolume.add(codonNote.get(2));

            }
            // if current section of the line is not long enough, get a smaller piece (<9).
            // this will be added to the start of the next line instead
            else {
                unusedNucleotides = line.substring(i * 9);
            }
        }
    }

    /**
     * Converts a String of 3 codons (9 nucleotides) to an ArrayList of integers used as Midi data.
     *
     * @param codonsToConvert String of 3 codons (9 nucleotides).
     * @return ArrayList of integers (used as Midi data).
     */
    private ArrayList<Integer> convertCodonsToMidi(String codonsToConvert) {

        //first class resulting in 3 numbers
        ArrayList<Integer> codonInt = lettersToNumbers.codonTranslate(codonsToConvert);

        //second class resulting in 3 new numbers
        ArrayList<Integer> codonNote = numberToNote.devideNumbers(codonInt);

        return codonNote;
    }

    public ArrayList<Integer> getMidiMetadata() {
        if (midiMetadata.size() < metadataAmount) {
            throw new IllegalArgumentException("The input FASTA file was too short to make a song.\nTry a bigger FASTA file.");
        }

        return midiMetadata;
    }

    public ArrayList<Integer> getMidiNotesPitch() {
        return midiNotesPitch;
    }

    public ArrayList<Integer> getMidiNotesLength() {
        return midiNotesLength;
    }

    public ArrayList<Integer> getMidiNotesVolume() {
        return midiNotesVolume;
    }
}
