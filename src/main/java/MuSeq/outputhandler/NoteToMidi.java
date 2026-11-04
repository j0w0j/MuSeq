package MuSeq.outputhandler;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import java.util.List;

public class NoteToMidi {

    long currentTick = 0;

    public void addNoteToTrack(Track track, List<Integer> note) {

        int pitch = note.get(0);      // note pitch
        int duration = note.get(1);   // note length
        int velocity = note.get(2);   // note volume

        // note ON
        ShortMessage on = new ShortMessage();
        try {
            on.setMessage(ShortMessage.NOTE_ON, 0, pitch, velocity);
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
        track.add(new MidiEvent(on, currentTick));

        // note OFF
        ShortMessage off = new ShortMessage();
        try {
            off.setMessage(ShortMessage.NOTE_OFF, 0, pitch, 0);
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
        track.add(new MidiEvent(off, currentTick + duration));

        // next note starts when this one ends, so the lengths define the rhythm
        currentTick += duration;
    }
}
