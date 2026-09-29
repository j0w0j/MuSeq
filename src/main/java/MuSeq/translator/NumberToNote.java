package MuSeq.translator;

import java.util.ArrayList;

/**
 * In this class the 3 numbers calculated from the 3 codons get transformed
 * into a number needed for a singular note in a midi file. The 3 characters
 * of a note calculated here are the pitch, volume en length. The method
 * devideNumbers handles the 3 numbers and their respective methods.
 *
 */
public class NumberToNote {


    /**
     * This method takes the 3 numbers made using the codons and calls on
     * the other methods present in this class to transform them into the
     * numberformat needed for a midi-file.
     * @param codonNumbers 3 number representing the 3 codons in ArrayList
     * @return the 3 transformed numbers in a ArrayList
     */

    public ArrayList<Integer> devideNumbers(ArrayList<Integer> codonNumbers){
        ArrayList<Integer> pitchLengthVolume = new ArrayList<Integer>();
        int pitch = calculatePitch((Integer) codonNumbers.get(0));
        int length = calculateLenght((Integer) codonNumbers.get(1));
        int volume = calculateVolume((Integer) codonNumbers.get(2));
        pitchLengthVolume.add(pitch);
        pitchLengthVolume.add(length);
        pitchLengthVolume.add(volume);

        return pitchLengthVolume;
    }

    /**
     *
     * @param codonNumber 1 number from a codon between 0 and 63
     * @return number between 36 and 100 representing notes C3 through E8 in a midifile
     */
    int calculatePitch(int codonNumber) {
        int pitchNumber = codonNumber + 36;

        return pitchNumber;
    }

    /**
     *
     * @param codonNumber 1 number from a codon between 0 and 63
     * @return
     */
    int calculateLenght(int codonNumber){

        int tempoNumber = codonNumber * 2;
        return tempoNumber;
    }

    /**
     *
     * @param codonNumber 1 number from a codon between 0 and 63
     * @return
     */
    int calculateVolume(int codonNumber){

        int volumeNumber = codonNumber * 40;
        return volumeNumber;
    }

}

