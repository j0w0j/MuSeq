package MuSeq.file_input;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static MuSeq.file_input.MetadataGetter.getMetadata;
import static org.junit.jupiter.api.Assertions.*;

public class MetadataGetterTest {

    ArrayList<Integer> inputPitch = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    ArrayList<Integer> inputLength = new ArrayList<>(Arrays.asList(6, 7, 8, 9, 10));
    ArrayList<Integer> inputVolume = new ArrayList<>(Arrays.asList(11, 12, 13, 14, 15));

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
    void getMetadataRainyDay() {

        assertThrows(IndexOutOfBoundsException.class, () -> {
            Integer[] metadata = getMetadata(inputPitch, inputLength, inputVolume, 20);
        });
    }
}