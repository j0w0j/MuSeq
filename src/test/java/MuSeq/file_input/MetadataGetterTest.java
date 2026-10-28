package MuSeq.file_input;

import MuSeq.model.MidiData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static MuSeq.file_input.MetadataGetter.getMetadata;
import static org.junit.jupiter.api.Assertions.*;

public class MetadataGetterTest {

    private ArrayList<Integer> inputPitch;
    private ArrayList<Integer> inputLength;
    private ArrayList<Integer> inputVolume;

    @BeforeEach
    void setup(){
        inputPitch = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        inputLength = new ArrayList<>(Arrays.asList(6, 7, 8, 9, 10));
        inputVolume = new ArrayList<>(Arrays.asList(11, 12, 13, 14, 15));
    }


    /**
     * Test if MetadataGetter works with valid input.
     */
    @Test
    void getMetadataSunnyDay() {

        // expected changes to input after metadata retrieval
        ArrayList<Integer> inputPitchUpdated = new ArrayList<>(Arrays.asList(3, 4, 5));
        ArrayList<Integer> inputLengthUpdated = new ArrayList<>(Arrays.asList(8, 9, 10));
        ArrayList<Integer> inputVolumeUpdated = new ArrayList<>(Arrays.asList(13, 14, 15));

        // expected metadata
        Integer[] expectedOutput = new Integer[] {1, 6, 11, 2, 7, 12};

        Integer[] metadata = getMetadata(inputPitch, inputLength, inputVolume, 2);

        assertEquals(inputPitchUpdated, inputPitch, "Input does not change as expected!");
        assertEquals(inputLengthUpdated, inputLength, "Input does not change as expected!");
        assertEquals(inputVolumeUpdated, inputVolume, "Input does not change as expected!");
        assertArrayEquals(expectedOutput, metadata, "Metadata is not as expected!");
    }

    /**
     * Test if MetadataGetter works with invalid input.
     */
    @Test
    void getMetadataRainyDay() throws IOException {

        // Amount is too high
        assertThrows(IndexOutOfBoundsException.class, () -> {
            Integer[] metadata = getMetadata(inputPitch, inputLength, inputVolume, 20);
        });

        // file that is too short
        MidiData midiData = new MidiData();
        FastaReader fastaReader = new FastaReader();

        fastaReader.readFasta("src/test/test_files/test_validfasta_but_too_short.fasta", midiData);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            midiData.setMidiMetadata(5);
        });
    }
}