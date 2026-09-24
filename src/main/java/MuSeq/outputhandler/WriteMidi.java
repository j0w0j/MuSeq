package MuSeq.outputhandler;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.Callable;

@Command(name = "write-midi", mixinStandardHelpOptions = true, description = "Write MIDI data in file")
public class WriteMidi implements Callable<Integer> {

    // user can use -f to write file with custom path name and file name
    @Option(names = {"-writemidi", "--file"}, description = "Name Outputfile and path", defaultValue = "output/output.txt")
    private File outputFile;

    private final int[] numbers;

    public WriteMidi(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public Integer call() throws Exception {
        if (outputFile == null) {
            // if user gives no path/filename
            outputFile = new File("output/output.txt");
        }

        // use NoteToMidiChar class
        NoteToMidiChar converter = new NoteToMidiChar();
        String midiString = converter.convertNumberToMidiChar(numbers);

        try {
            if (outputFile.getParentFile() != null) {
                outputFile.getParentFile().mkdirs();
            }

            Files.writeString(outputFile.toPath(), midiString);
            System.out.println("Succes! The MIDI file is written. " + outputFile.getName());
        } catch (IOException e) {
            System.err.println("Oops, something went wrong with writing your file! " + e.getMessage());
            return 1;
        }

        return 0;
    }
}