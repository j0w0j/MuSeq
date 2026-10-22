package MuSeq.file_input;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MetadataGetterTest {

    MetadataGetter metadataGetter = new MetadataGetter();

    @Test
    void getMetadataSunnyDay() {

        ArrayList<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        ArrayList<Integer> inputUpdated = new ArrayList<>(Arrays.asList(6, 7, 8, 9, 10));
        Integer[] expectedOutput = new Integer[] {1, 2, 3, 4, 5};

        Integer[] metadata = metadataGetter.getMetadata(input, 5);

        assertEquals(inputUpdated, input, "Input does not change as expected!");
        assertArrayEquals(expectedOutput, metadata, "Metadata is not as expected!");
    }
    @Test
    void getMetadataRainyDay() {

        ArrayList<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            Integer[] metadata = metadataGetter.getMetadata(input, 20);
        });
    }
}