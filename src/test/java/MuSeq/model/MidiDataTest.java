package MuSeq.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.sound.midi.MidiEvent;

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
            "ACACACACACGTGCTGATCGATCGTACTAGTCGATCGTAGCTAGCTGATCGATCGTACGTACGTAGCTAGCTAGTGCTAGTCGATCGTACGTAGCTACGTAGC, 8, 10",
            "A, 0, 0"})
    void testAddFromFastaLine(String line, int expectedNotesAmount, int metadataAmount) {

        expectedNotesAmount *= 2; // each note adds 2 MidiEvents to the track, so expectedNotesAmount*2
        expectedNotesAmount += 1; // "The track initially contains the meta-event End of Track" -> See Sequence.createTrack doc.

        MidiData midiData = new MidiData(metadataAmount); // no codons are reserved for metadata

        midiData.addFromFastaLine(line);

        // amount of notes should be as expected (assumes all lists are of equal size)
        assertEquals(expectedNotesAmount, midiData.getTrack().size(), "amount of MIDI notes is not as expected");

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