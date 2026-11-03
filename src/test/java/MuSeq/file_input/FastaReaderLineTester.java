package MuSeq.file_input;

import MuSeq.model.MidiData;

import java.util.ArrayList;
import java.util.List;

/**
 * Used for testing lines read by FastaReader.
 * Override MidiData addFromFastaLine method to add FASTA lines to variable ArrayList<String> lines,
 * instead of translating FASTA to MIDI and adding to midiNotes lists.
 * This way it can be tested if FastaReader read the FASTA lines correctly.
 */
public class FastaReaderLineTester extends MidiData {

    private List<String> lines = new ArrayList<String>();;

    /**
     *
     * @param metadataAmount Amount of codons reserved for the metadata
     */
    public FastaReaderLineTester(int metadataAmount) {
        super(metadataAmount);
    }

    public List<String> getLines() {
        return lines;
    }

    @Override
    public void addFromFastaLine(String line) {
        lines.add(line);
    }
}
