package MuSeq.file_input;


import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains a function used to retrieve and remove first n amount of integers/notes from the MidiNotes list.
 * This is later used as Metadata (options such as instrument and bpm) for the MIDI file.
 */
public class MetadataGetter {
    /**
     * Retrieve first n amount of integers/notes from the MidiNotes list.
     * This is later used as Metadata for the MIDI file (options such as instrument and bpm).
     * @param notes ArrayList of integers (midi notes).
     * @param amount First amount of integers/notes to retrieve from the list.
     *               These are removed from the input list.
     * @return metadata Integer array used for the MIDI file metadata.
     * @throws IndexOutOfBoundsException Happens when the MidiNotes list is too short (i.e. the input FASTA file was too short).
     */
    public Integer[] getMetadata(ArrayList<Integer> notes, int amount) throws IndexOutOfBoundsException{

        // throw error if input list is too small
        if (amount > notes.size()) {
            throw new IndexOutOfBoundsException("The input FASTA file is too short to make a song.\nTry a bigger FASTA file.");
        }

        // make empty array to return
        Integer[] metadata = new Integer[amount];

        // fill array with the first n amount of integers in the notes list
        metadata = notes.subList(0, amount).toArray(metadata);
        // remove the integers used for metadata from the notes list
        notes.subList(0, amount).clear();

        return metadata;
    }
}
