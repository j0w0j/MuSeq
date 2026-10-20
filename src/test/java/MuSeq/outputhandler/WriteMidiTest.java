package MuSeq.outputhandler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class WriteMidiTest {

    @Test
    // tests if the midi is written
    void testWriteMidiSucces(@TempDir Path tempDir) {
        int[] notes = {60, 44, 22};

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
        int[] notes = {60, 44, 22};
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