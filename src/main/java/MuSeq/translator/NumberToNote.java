package MuSeq.translator;

import java.util.ArrayList;

/**
 * In this class the 3 numbers calculated from the 3 codons get transformed
 * into the numbers needed for a single note in a MIDI file. The 3 properties
 * of a note calculated here are pitch, length and volume. The method
 * devideNumbers handles the 3 numbers and their respective methods.
 */
public class NumberToNote {

    // note lengths in ticks at PPQ = 480:
    // sixteenth, eighth, dotted eighth, quarter, dotted quarter, half, dotted half, whole
    private static final int[] DURATIONS = {120, 240, 360, 480, 720, 960, 1440, 1920};

    /**
     * Takes the 3 numbers made using the codons and calls the other methods
     * in this class to transform them into the format needed for a MIDI file.
     *
     * @param codonNumbers 3 numbers representing the 3 codons
     * @return list with [pitch, length in ticks, volume]
     */
    public ArrayList<Integer> devideNumbers(ArrayList<Integer> codonNumbers) {
        ArrayList<Integer> pitchLengthVolume = new ArrayList<>();
        int pitch = calculatePitch(codonNumbers.get(0));
        int length = calculateLenght(codonNumbers.get(1));
        int volume = calculateVolume(codonNumbers.get(2));
        pitchLengthVolume.add(pitch);
        pitchLengthVolume.add(length);
        pitchLengthVolume.add(volume);

        return pitchLengthVolume;
    }

    /**
     * @param codonNumber 1 number from a codon between 0 and 63
     * @return number between 36 and 99, representing notes C2 through D#7 in a MIDI file
     */
    int calculatePitch(int codonNumber) {
        return codonNumber + 36;
    }

    /**
     * @param codonNumber 1 number from a codon between 0 and 63
     * @return note length in MIDI ticks (120 to 1920)
     */
    int calculateLenght(int codonNumber) {
        return DURATIONS[codonNumber / 8];   // 0-63 / 8 gives index 0-7
    }

    /**
     * @param codonNumber 1 number from a codon between 0 and 63
     * @return velocity between 40 and 103
     */
    int calculateVolume(int codonNumber) {
        return codonNumber + 40;
    }
}