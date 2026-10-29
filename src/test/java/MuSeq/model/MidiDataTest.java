package MuSeq.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MidiDataTest {


    @ParameterizedTest
    @CsvSource(
            // numbers = amount of nucleotides in each line/sequence, divided by 9, rounded down.
            {"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA, 4",
            "ACGTACGTACGTACGTGTGACACAGTGACACACAA, 3",
            "ATGACCTGG, 1",
            "ACACACACACGTGCTGATCGATCGTACTAGTCGATCGTAGCTAGCTGATCGATCGTACGTACGTAGCTAGCTAGTGCTAGTCGATCGTACGTAGCTACGTAGC, 11"})
    void testAddFromFastaLine(String line, int expectedNotesAmount) {

        MidiData midiData = new MidiData(0);

        midiData.addFromFastaLine(line);

        // all lists should be of equal size
        assertEquals(midiData.getMidiNotesPitch().size(), midiData.getMidiNotesLength().size());
        assertEquals(midiData.getMidiNotesLength().size(), midiData.getMidiNotesVolume().size());

        // the tests below assume all lists are of equal size!!

        // amount of notes should be as expected
        assertEquals(expectedNotesAmount, midiData.getMidiNotesPitch().size(), "amount of MIDI notes is not as expected");
    }
}