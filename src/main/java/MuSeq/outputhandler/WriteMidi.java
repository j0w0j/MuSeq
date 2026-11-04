package MuSeq.outputhandler;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

/**
 * Writes all created midi data into the output file
 */
public class WriteMidi {

    private File outputFile = new File("output/output.mid");
    private final Sequence sequence;
    private final Track track;
    private final int bpm;
    private final int instrument;

    /**
     * Makes output file name given user input
     *
     */
    public WriteMidi(Sequence sequence, Track track, String customPath, int bpm, int instrument) {
        this.sequence = sequence;
        this.track = track;
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

    /**
     * Creates midi file and write created midi data into that file
     * @return 0 if everything went well, 1 or 2 if an error occurred
     */
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