package MuSeq.file_input;


import java.util.ArrayList;

/**
 * Contains a function used to retrieve and remove first n amount of integers/notes from each MidiData list.
 * This is later used as Metadata (options such as instrument and bpm) for the MIDI file.
 */
public class MetadataGetter {
    /**
     * Retrieve first n amount of integers/notes from the MidiData.
     * This is used as Metadata for the MIDI file (options such as instrument and bpm).
     * @param midiNotesPitch ArrayList with the pitches for the notes.
     * @param midiNotesLength ArrayList with lengths for the notes.
     * @param midiNotesVolume ArrayList with volumes for the notes.
     * @param amount First amount of integers/notes to retrieve from each list.
     *               The retrieved values are removed from each list.
     * @return metadata Integer array used for the MIDI file metadata.
     * @throws IndexOutOfBoundsException Happens when the MidiData is too short (i.e. the input FASTA file was too short).
     */
    public static Integer[] getMetadata(ArrayList<Integer> midiNotesPitch,
                                 ArrayList<Integer> midiNotesLength,
                                 ArrayList<Integer> midiNotesVolume,
                                 int amount) throws IndexOutOfBoundsException{

        // throw error if input list is too small (meaning no notes remain after metadata extraction).
        if (midiNotesPitch.size() <= amount) {
            throw new IndexOutOfBoundsException("The input FASTA file is too short to make a song.\nTry a bigger FASTA file.");
        }

        // make empty array to return
        Integer[] metadata = new Integer[amount*3];

        // fill array with the first n amount of integers in the notes lists
        // also remove the integers used for metadata from the notes list
        int counter = 0;
        for (int i = 0; i < amount; i++) {
            metadata[counter] = midiNotesPitch.removeFirst();
            counter++;
            metadata[counter] = midiNotesLength.removeFirst();
            counter++;
            metadata[counter] = midiNotesVolume.removeFirst();
            counter++;
        }

        return metadata;
    }
}
