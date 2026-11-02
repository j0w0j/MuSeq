package MuSeq.translator;

/**
 * Calculates an instrument number (between 0-127) from 2 codon numbers (each between 0-63).
 */
public class InstrumentCalculator {

    private final int instrument;

    public InstrumentCalculator(int codonNumber1, int codonNumber2) {

        this.instrument = calculateInstrument(codonNumber1, codonNumber2);
    }

    private int calculateInstrument(int codonNumber1, int codonNumber2) {

        if (codonNumber2 > 0) {
            codonNumber2++;
        }

        return codonNumber1 + codonNumber2;
    }

    public int getInstrument() {
        return instrument;
    }
}
