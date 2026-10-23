package MuSeq.model;

import MuSeq.file_input.MetadataGetter;
import MuSeq.translator.LettersToNumbers;
import MuSeq.translator.NumberToNote;

import java.util.ArrayList;

public class MidiData {

    private ArrayList<Integer> midiNotes = new ArrayList<Integer>();

    private Integer[] midiMetadata;

    /**
     * Unused nucleotides from the previous line of the input FASTA file.
     */
    private String unusedNucleotides = "";


    /**
     * Translate the FASTA line to MIDI notes, and add the MIDI notes to the midiNotes ArrayList.
     * @param line Line from the FASTA file, as String.
     */
    public void addFromFastaLine(String line) {
        // Add unused nucleotides from previous line to the beginning of the current line.</li>
        if (!unusedNucleotides.isEmpty()) {
            line = unusedNucleotides.concat(line);
            unusedNucleotides = "";
        }

        // split line into sections of maximum 9 nucleotides (3 codons).
        // iterateAmount is the amount of sections in the line.
        int iterateAmount = (int) Math.ceil((double) line.length() / 9);

        // iterate over each section.
        for (int i = 0; i < iterateAmount; i++) {
            // check if current section of the line is long enough (>=9).
            if (i * 9 + 9 <= line.length()) {
                String codonsToConvert = line.substring(i * 9, i * 9 + 9);

                // convert the codons
                ArrayList<Integer> codonNote = convertCodonsToMidi(codonsToConvert);

                // add converted codons to ArrayList to return
                midiNotes.addAll(codonNote);
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
        LettersToNumbers lettersToNumbers = new LettersToNumbers();
        ArrayList<Integer> codonInt = lettersToNumbers.codonTranslate(codonsToConvert);

        //second class resulting in 3 new numbers
        NumberToNote numberToNote = new NumberToNote();
        ArrayList<Integer> codonNote = numberToNote.devideNumbers(codonInt);

        return codonNote;
    }


    /**
     * Get midi metadata from midinotes.
     * @param amount First amount of integers/notes to retrieve from the list.
     *               These are removed from the input list.
     */
    public void setMidiMetadata(int amount) {
        MetadataGetter metadataGetter = new MetadataGetter();
        this.midiMetadata = metadataGetter.getMetadata(this.midiNotes, amount);
    }

    public Integer[] getMidiMetadata() {
        return midiMetadata;
    }

    public ArrayList<Integer> getMidiNotes() {
        return midiNotes;
    }
}
