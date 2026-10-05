package MuSeq.outputhandler;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

public class WriteMidi {

    private File outputFile = new File("output/output.mid");
    private final int[] numbers;

    public WriteMidi(int[] numbers) {
        this.numbers = numbers;
    }

    public WriteMidi(int[] numbers, String customPath) {
        this.numbers = numbers;
        if (customPath != null && !customPath.isEmpty()) {
            this.outputFile = new File(customPath);
        }
    }

    public int writeMidiFile() {
        try {
            if (outputFile.getParentFile() != null) {
                outputFile.getParentFile().mkdirs();
            }

            Sequence sequence = new Sequence(Sequence.PPQ, 480);
            Track track = sequence.createTrack();

            // Set tempo: 120 BPM
            MetaMessage tempoMessage = new MetaMessage();
            byte[] tempoData = new byte[] { 0x07, (byte) 0xA1, 0x20 };
            tempoMessage.setMessage(0x51, tempoData, 3);
            track.add(new MidiEvent(tempoMessage, 0));


            long currentTick = 0;
            int duration = 480; //length quarter note


            // create the melody, wordt nog aangepast
            for (int i = 0; i < numbers.length; i++) {
                int noteNumber = numbers[i];

                // note ON
                ShortMessage onMessage = new ShortMessage();
                onMessage.setMessage(ShortMessage.NOTE_ON, 0, noteNumber, 93);
                track.add(new MidiEvent(onMessage, currentTick));

                // note off
                ShortMessage offMessage = new ShortMessage();
                offMessage.setMessage(ShortMessage.NOTE_OFF, 0, noteNumber, 0);
                track.add(new MidiEvent(offMessage, currentTick + duration));

                // go to next tick
                currentTick += duration;
            }

            // write output to midifile to correct path:D
            MidiSystem.write(sequence, 1, outputFile);

            System.out.println("Succes! The MIDI file is written. " + outputFile.getPath());
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