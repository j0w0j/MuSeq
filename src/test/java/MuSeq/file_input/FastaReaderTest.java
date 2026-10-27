package MuSeq.file_input;

import MuSeq.model.MidiData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FastaReaderTest {

    FastaReader fastaReader = new FastaReader();

    /**
     * Test valid FASTA files.
     */
    @ParameterizedTest
    @CsvSource(
            // numbers = amount of nucleotides in each FASTA file, divided by 9, rounded down.
            {"src/test/test_files/test_valid1.fasta, 91",
            "src/test/test_files/test_valid2.fasta, 31"})
    void testReadFastaSunnyDay(String filepath, int expectedNotesAmount) throws IOException {

        MidiData midiData = new MidiData();

        fastaReader.readFasta(filepath, midiData);

        // all lists should be of equal size
        assertEquals(midiData.getMidiNotesPitch().size(), midiData.getMidiNotesLength().size());
        assertEquals(midiData.getMidiNotesLength().size(), midiData.getMidiNotesVolume().size());

        // the tests below assume all lists are of equal size!!

        // amount of notes should be as expected
        assertEquals(expectedNotesAmount, midiData.getMidiNotesPitch().size(), "amount of MIDI notes is not as expected");

        // lists should not be empty
        assertFalse(midiData.getMidiNotesPitch().isEmpty(), "MIDI notes is empty!");
    }

    /**
     * Test invalid FASTA files.
     */
    @ParameterizedTest
    @CsvSource(
            // numbers = amount of nucleotides in each FASTA file, divided by 9, rounded down.
            {"src/test/test_files/test_valid1.fasta, 91",
                    "src/test/test_files/test_valid2.fasta, 31"})
    void testReadFastaRainyDay(String filepath, int expectedNotesAmount) throws IOException {

        MidiData midiData = new MidiData();

        // TODO!
    }
}