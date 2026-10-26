package MuSeq.file_input;

import MuSeq.model.MidiData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.Parameter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static MuSeq.file_input.MetadataGetter.getMetadata;
import static org.junit.jupiter.api.Assertions.*;

class FastaReaderTest {


    private FastaReader fastaReader;
    private FastaReaderLineTester readerTester;

    @BeforeEach
    void setup() {
        fastaReader = new FastaReader();
        readerTester = new FastaReaderLineTester();
    }


    // source - https://stackoverflow.com/questions/46712485
    static Stream<Arguments> getValidData() {
        ExpectedFastaLines expectedLines = new ExpectedFastaLines();
        return Stream.of(
                Arguments.of("src/test/test_files/test_valid1.fasta", expectedLines.getTestValid1()),
                Arguments.of("src/test/test_files/test_valid2.fasta", expectedLines.getTestValid2())
        );
    }


    /**
     * Test if valid FASTA files are read correctly.
     */
    @ParameterizedTest
    @MethodSource("getValidData")
    void testReadFastaSunnyDay(String filepath, ArrayList<String> expectedLines) throws IOException {

        fastaReader.readFasta(filepath, readerTester);

        assertEquals(expectedLines, readerTester.getLines());
    }

    /**
     * Test if invalid FASTA files throw the expected errors.
     */
    @Test
    void testReadFastaRainyDay() throws IOException {

        // Files with invalid FASTA format,
        // example: characters that are not A, T, C, G or U in sequences.
        assertThrows(IllegalArgumentException.class, () -> {
            fastaReader.readFasta("src/test/test_files/test_invalid1.fasta", readerTester);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            fastaReader.readFasta("src/test/test_files/test_invalid2.fasta", readerTester);
        });

        // File that does not exist
        assertThrows(FileNotFoundException.class, () -> {
            fastaReader.readFasta("this file does not exist!", readerTester);
        });
    }
}