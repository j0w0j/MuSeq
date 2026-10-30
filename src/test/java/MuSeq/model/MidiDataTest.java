package MuSeq.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class MidiDataTest {


    @ParameterizedTest
    @CsvSource(
            // expectedNotesAmount = (amount of nucleotides in each sequence - 3*metadataAmount), divided by 9, rounded down.
            // metadataAmount =  codons that are reserved for metadata
            {"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA, 4, 0",
            "ACGTACGTACGTACGTGTGACACAGTGACACACAA, 3, 0",
            "ATGACCTGG, 1, 0",
            "ACACACACACGTGCTGATCGATCGTACTAGTCGATCGTAGCTAGCTGATCGATCGTACGTACGTAGCTAGCTAGTGCTAGTCGATCGTACGTAGCTACGTAGC, 11, 0",
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA, 3, 2",
            "ACGTACGTACGTACGTGTGACACAGTGACACACAA, 3, 2",
            "ATGACCTGG, 0, 1",
            "ACACACACACGTGCTGATCGATCGTACTAGTCGATCGTAGCTAGCTGATCGATCGTACGTACGTAGCTAGCTAGTGCTAGTCGATCGTACGTAGCTACGTAGC, 8, 10"})
    void testAddFromFastaLine(String line, int expectedNotesAmount, int metadataAmount) {

        MidiData midiData = new MidiData(metadataAmount); // no codons are reserved for metadata

        midiData.addFromFastaLine(line);

        // all lists should be of equal size
        assertEquals(midiData.getMidiNotesPitch().size(), midiData.getMidiNotesLength().size());
        assertEquals(midiData.getMidiNotesLength().size(), midiData.getMidiNotesVolume().size());

        // amount of notes should be as expected (assumes all lists are of equal size)
        assertEquals(expectedNotesAmount, midiData.getMidiNotesPitch().size(), "amount of MIDI notes is not as expected");

        // amount of metadata should be as expected
        assertEquals(metadataAmount, midiData.getMidiMetadata().size());
    }

    @ParameterizedTest
    @ValueSource(strings =
            {"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            "ACGTACGTACGTACGTGTGACACAGTGACACACAA",
            "ATGACCTGG",
            ""})
    void testNotEnoughData(String line) {

        MidiData midiData = new MidiData(20); // 20 codons are reserved for metadata (= 60 nucleotides)

        midiData.addFromFastaLine(line);

        // the input was not enough to fill the metadata, so should throw error.
        assertThrows(IllegalArgumentException.class, () -> {
            midiData.getMidiMetadata();
        });
    }
}