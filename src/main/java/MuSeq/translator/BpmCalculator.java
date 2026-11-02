package MuSeq.translator;

/**
 * Calculate the BPM from a codon number (0-63).
 * The resulting BPM ranges from 54 to 180.
 */
public class BpmCalculator {

    private final int bpm;
    private final int codonNumber;

    public BpmCalculator(Integer codonNumber) {
        this.codonNumber = codonNumber;

        // calculate BPM with codon number
        this.bpm = calculateBpm(codonNumber);
    }

    private int calculateBpm(int codonNumber) {
        return 54 + (codonNumber * 2);
    }

    public int getBpm() {
        return bpm;
    }

    public int getCodonNumber() {
        return codonNumber;
    }
}