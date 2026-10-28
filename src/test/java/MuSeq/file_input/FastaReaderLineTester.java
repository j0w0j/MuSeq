package MuSeq.file_input;

import MuSeq.model.MidiData;

import java.util.ArrayList;

/**
 * Used for testing lines read by FastaReader.
 * Override MidiData addFromFastaLine method to add FASTA lines to variable ArrayList<String> lines,
 * instead of translating FASTA to MIDI and adding to midiNotes lists.
 * This way it can be tested if FastaReader read the FASTA lines correctly.
 */
public class FastaReaderLineTester extends MidiData {

    private ArrayList<String> lines = new ArrayList<String>();;

    public ArrayList<String> getLines() {
        return lines;
    }

    @Override
    public void addFromFastaLine(String line) {
        lines.add(line);
    }
}
