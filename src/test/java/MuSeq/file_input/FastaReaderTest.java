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
    static Stream<Arguments> getFilterTestData() {
        return Stream.of(
                Arguments.of("chromosome 1", Arrays.asList("AAA", "CCC", "TTT")),
                Arguments.of("Chromosome x", Arrays.asList("GCGCGC")),
                Arguments.of("cHROMOSOME 3CEN", Arrays.asList("CCCCCCCCCC", "CCCCCCCCCC")),
                Arguments.of("chromosome 3", Arrays.asList("ATATATATATAT")),
                Arguments.of("chromosome 22", Arrays.asList("GG", "TTT", "AA", "C")),
                Arguments.of("chromOSOme mT", Arrays.asList("T")),
                Arguments.of("BRCA1", Arrays.asList("ACGT")),
                Arguments.of("egfr", Arrays.asList("TGCA"))
                );
    }

    @ParameterizedTest
    @MethodSource("getFilterTestData")
    void testReadFastaWithFilterSunnyDay(String headerFilter, List<String> expectedLines) throws IOException {
        fastaReader.readFasta("src/test/test_files/test_headerfilter.fasta", readerTester, headerFilter);

        assertEquals(expectedLines, readerTester.getLines());
    }

    @ParameterizedTest
    @ValueSource(strings = {"chromosome XXL", "chromosome 2", "BRCA", "EGFR2", "BRCA2"})
    void testReadFastaWithFilterRainyDay(String headerFilter) {
        assertThrows(IllegalArgumentException.class, () -> {
            fastaReader.readFasta("src/test/test_files/test_headerfilter.fasta", readerTester, headerFilter);
        });
    }
}