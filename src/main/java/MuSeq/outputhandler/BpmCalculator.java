package MuSeq.outputhandler;


public class BpmCalculator {

    private final int bpm;
    private final int amount;

    public BpmCalculator(Integer amount) {
        this.amount = amount;

        // calculate BPM with amount
        this.bpm = calculateBpm(amount);
    }

    // random calculation, we can change this later
    private int calculateBpm(int amount) {
        int calculated = 80 + (amount * 5);
        return Math.max(60, Math.min(200, calculated));
    }

    public int getBpm() {
        return bpm;
    }

    public int getAmount() {
        return amount;
    }
}