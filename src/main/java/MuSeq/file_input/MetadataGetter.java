package MuSeq.file_input;


import java.util.ArrayList;

/**
 * Contains a function used to retrieve first n amount of integers/notes from the MidiNotes list.
 * This is later used as Metadata (options such as instrument and bpm) for the MIDI file.
 */
public class MetadataGetter {
    /**
     * retrieve first n amount of integers/notes from the MidiNotes list.
     * This is later used as Metadata for the MIDI file (options such as instrument and bpm).
     * @param notes ArrayList of integers (midi notes).
     * @param amount First amount of integers/notes to retrieve from the list.
     *               These are removed from the input list.
     * @return metadata Integer array used for the MIDI file metadata.
     */
    public Integer[] getMetadata(ArrayList<Integer> notes, int amount) {
        // make empty array to return
        Integer[] metadata = new Integer[amount];
        // fill array with the first n amount of integers in the notes list
        metadata = notes.subList(0, amount).toArray(metadata);
        // remove the integers used for metadata from the notes list
        notes.subList(0, amount).clear();
        return metadata;
    }
}
