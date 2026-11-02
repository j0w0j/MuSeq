package MuSeq.translator;


public class BpmCalculator {

    private final int bpm;
    private final int codonNumber;

    public BpmCalculator(Integer codonNumber) {
        this.codonNumber = codonNumber;

        // calculate BPM with amount
        this.bpm = calculateBpm(codonNumber);
    }

    private int calculateBpm(int amount) {
        int calculated = 80 + (amount * 5);
        return Math.max(60, Math.min(200, calculated));
    }

    public int getBpm() {
        return bpm;
    }

    public int getAmount() {
        return codonNumber;
    }
}