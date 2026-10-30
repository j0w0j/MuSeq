package MuSeq.outputhandler;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class WriteMidi {

    private File outputFile = new File("output/output.mid");
    private final List<List<Integer>> notes; // notes [pitch, length in ticks, velocity)
    private final int bpm;
    private final int instrument;

    public WriteMidi(List<List<Integer>> notes, int bpm) {
        this.notes = notes;
        this.bpm = bpm;
        this.instrument = 0;
    }

    public WriteMidi(List<List<Integer>> notes, String customPath, int bpm, int instrument) {
        this.notes = notes;
        this.bpm = bpm;
        this.instrument = instrument;
        if (customPath != null && !customPath.isEmpty()) {

            // add .mid if user did not put it behind the output name
            if (!customPath.toLowerCase().endsWith(".mid")) {
                customPath += ".mid";
            }

            File custom = new File(customPath);
            // if no path given go to output
            if (custom.getParentFile() == null) {
                this.outputFile = new File("output", customPath);
            } else {
                this.outputFile = custom;
            }
        }
    }
    public int writeMidiFile () {
        try {
            if (outputFile.exists()) {
                System.err.println("File already exists: " + outputFile.getPath());
                // return 2 if filename already exists
                return 2;
            }
            if (outputFile.getParentFile() != null) {
                outputFile.getParentFile().mkdirs();
            }

            Sequence sequence = new Sequence(Sequence.PPQ, 480);
            Track track = sequence.createTrack();

            ShortMessage instrumentMessage  = new ShortMessage();
            instrumentMessage.setMessage(ShortMessage.PROGRAM_CHANGE, 0, instrument,0);
            track.add(new MidiEvent(instrumentMessage, 0));

            // Set tempo
            int mpq = 60000000 / bpm;
            byte[] tempoData = new byte[]{
                    (byte) ((mpq >> 16) & 0xFF),
                    (byte) ((mpq >> 8) & 0xFF),
                    (byte) (mpq & 0xFF)
            };

            MetaMessage tempoMessage = new MetaMessage();
            tempoMessage.setMessage(0x51, tempoData, 3);
            track.add(new MidiEvent(tempoMessage, 0));


            long currentTick = 0;

            for (List<Integer> note : notes) {
                int pitch = note.get(0);
                int duration = note.get(1);   // length in ticks
                int velocity = note.get(2);   // 0-127

                // note ON
                ShortMessage on = new ShortMessage();
                on.setMessage(ShortMessage.NOTE_ON, 0, pitch, velocity);
                track.add(new MidiEvent(on, currentTick));

                // note OFF
                ShortMessage off = new ShortMessage();
                off.setMessage(ShortMessage.NOTE_OFF, 0, pitch, 0);
                track.add(new MidiEvent(off, currentTick + duration));

                // next note starts when this one ends, so the lengths define the rhythm
                currentTick += duration;
            }

            MidiSystem.write(sequence, 1, outputFile);

            System.out.println("Succes! The MIDI file is written. " + outputFile.getPath());
        } catch (IOException e) {
            System.err.println("Oops, something went wrong with writing your file! " + e.getMessage());
            return 1;
        } catch (Exception e) {
            System.err.println("Oops, something went wrong with MIDI data! " + e.getMessage());
            return 1;
        }
        return 0;
    }
}