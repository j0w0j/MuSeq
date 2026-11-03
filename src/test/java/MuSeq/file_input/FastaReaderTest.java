package MuSeq.file_input;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FastaReaderTest {


    private FastaReader fastaReader;
    private FastaReaderLineTester readerTester;

    @BeforeEach
    void setup() {
        fastaReader = new FastaReader();
        readerTester = new FastaReaderLineTester(0);
    }


    // source - https://stackoverflow.com/questions/46712485
    static Stream<Arguments> getValidTestData() {
        ExpectedFastaLines expectedLines = new ExpectedFastaLines();
        return Stream.of(
                Arguments.of("src/test/test_files/test_valid1.fasta", expectedLines.getTestValid1()),
                Arguments.of("src/test/test_files/test_valid2.fasta", expectedLines.getTestValid2()),
                Arguments.of("src/test/test_files/test_valid3.fasta", expectedLines.getTestValid3())
        );
    }


    /**
     * Test if valid FASTA files are read correctly.
     */
    @ParameterizedTest
    @MethodSource("getValidTestData")
    void testReadFastaSunnyDay(String filepath, List<String> expectedLines) throws IOException {

        fastaReader.readFasta(filepath, readerTester);

        assertEquals(expectedLines, readerTester.getLines());
    }

    /**
     * Test if invalid FASTA files throw the expected errors.
     */
    @Test
    void testReadFastaRainyDay() {

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

    // source - https://stackoverflow.com/questions/46712485
    static Stream<Arguments> getChromosomeTestData() {
        return Stream.of(
                Arguments.of("1", Arrays.asList("AAA", "CCC", "TTT")),
                Arguments.of("x", Arrays.asList("GCGCGC")),
                Arguments.of("3CEN", Arrays.asList("CCCCCCCCCC", "CCCCCCCCCC")),
                Arguments.of("3", Arrays.asList("CCCCCCCCCC", "CCCCCCCCCC", "ATATATATATAT")),
                Arguments.of("22", Arrays.asList("GG", "TTT", "AA", "C")),
                Arguments.of("mT", Arrays.asList("T"))
                );
    }

    @ParameterizedTest
    @MethodSource("getChromosomeTestData")
    void testReadFastaWithChromosomeSunnyDay(String chromosome, List<String> expectedLines) throws IOException {
        fastaReader.readFasta("src/test/test_files/test_chromosome.fasta", readerTester, chromosome);

        assertEquals(expectedLines, readerTester.getLines());
    }
}