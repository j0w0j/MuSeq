package MuSeq.outputhandler;

import MuSeq.file_input.MetadataGetter;
import java.util.ArrayList;

public class BpmCalculator {

    private final Integer[] metadata;
    private final int bpm;
    private final int amount;

    public BpmCalculator(ArrayList<Integer> pitch, ArrayList<Integer> length, ArrayList<Integer> volume, int amount) {
        this.amount = amount;

        // get metadata with amount
        this.metadata = MetadataGetter.getMetadata(pitch, length, volume, amount);

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