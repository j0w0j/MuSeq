package MuSeq.outputhandler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WriteMidiTest {

    // each note: {pitch, length in ticks, velocity}
    private final List<List<Integer>> notes = List.of(
            List.of(60, 480, 90),
            List.of(64, 240, 80),
            List.of(67, 960, 100)
    );

    @Test
        // tests if the midi is written
    void testWriteMidiSucces(@TempDir Path tempDir) {

        // temp map to not get confused
        File testOutputFile = tempDir.resolve("test_output.mid").toFile();

        WriteMidi writer = new WriteMidi(notes, testOutputFile.getPath());

        int exitCode = writer.writeMidiFile();

        assertEquals(0, exitCode, "If succes the code should print 0");

        assertTrue(testOutputFile.exists(), "Midi file made what a succes!");
        assertTrue(testOutputFile.length() > 0, "The midi file cannot be empty");
    }

    @Test
        // tests if the filename is unique, if not the file wont be written
    void testDoesMidiExist(@TempDir Path tempDir) {
        File testOutputFile = tempDir.resolve("test_output.mid").toFile();

        // 2 possible outcomes:

        // returns 0 if file does not exist and the file can be written
        int firstExitCode = new WriteMidi(notes, testOutputFile.getPath()).writeMidiFile();
        assertEquals(0, firstExitCode);
        assertTrue(testOutputFile.exists(), "The MIDI file should exist after writing");

        // if the file already exist return 2 and file will not be written
        int secondExitCode = new WriteMidi(notes, testOutputFile.getPath()).writeMidiFile();

        assertEquals(2, secondExitCode, "File cannot be written because it already exists!");
    }

    @Test
        // tests if the notes are valid
    void testValidNotes() {
        // TODO: Dit kom nog
        assertTrue(true, "This test is so cool it will succeed always:D");
    }
}