package MuSeq.outputhandler;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "write-midi", mixinStandardHelpOptions = true, description = "Write MIDI data in file")
public class WriteMidi implements Callable<Integer> {

    // user can use -f to write file with custom path name and file name
    @Option(names = {"-writemidi", "--file"}, description = "Name Outputfile and path", defaultValue = "output/output.mid")
    private File outputFile;

    private final int[] numbers;

    public WriteMidi(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public Integer call() throws Exception {
        if (outputFile == null) {
            // if user gives no path/filename
            outputFile = new File("output/output.mid");
        }

        try {
            if (outputFile.getParentFile() != null) {
                outputFile.getParentFile().mkdirs();
            }

            // pulses per Quarter note, highet int = higher precision
            Sequence sequence = new Sequence(Sequence.PPQ, 480);
            Track track = sequence.createTrack();

            // Set tempo: 120 BPM (500,000 microseconds per quarter note)
            MetaMessage tempoMessage = new MetaMessage();
            byte[] tempoData = new byte[] { 0x07, (byte) 0xA1, 0x20 };
            tempoMessage.setMessage(0x51, tempoData, 3);
            track.add(new MidiEvent(tempoMessage, 0));

            long currentTick = 0;
            int duration = 480; // length quarter note

            // create melody
            for (int i = 0; i < numbers.length; i++) {
                int noteNumber = numbers[i];

                //  Note ON
                ShortMessage onMessage = new ShortMessage();
                onMessage.setMessage(ShortMessage.NOTE_ON, 0, noteNumber, 93); // kanaal 0, noot, velocity (volume) 93
                track.add(new MidiEvent(onMessage, currentTick));

                // Note OFF
                ShortMessage offMessage = new ShortMessage();
                offMessage.setMessage(ShortMessage.NOTE_OFF, 0, noteNumber, 0);
                track.add(new MidiEvent(offMessage, currentTick + duration));

                // go to next tick for next note
                currentTick += duration;
            }

            // write to midi file
            MidiSystem.write(sequence, 1, outputFile);

            System.out.println("Succes! The MIDI file is written. " + outputFile.getName());
        } catch (IOException e) {
            System.err.println("Oops, something went wrong with writing your file! " + e.getMessage());
            return 1;
        } catch (Exception e) {
            System.err.println("Oops, something went wrong with MIDI data! " + e.getMessage());
            e.printStackTrace();
            return 1;
        }

        return 0;
    }
}