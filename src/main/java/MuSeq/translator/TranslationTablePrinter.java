package MuSeq.translator;
import java.util.ArrayList;
import java.util.List;


public class TranslationTablePrinter {

    static void main() {
        TranslationTablePrinter translationTable = new TranslationTablePrinter();

        for (int i = 0; i < 3; i++) {
            String[][] table = translationTable.getTranslationTable(i);
            translationTable.printFormatedTable(table, i);
        }
    }

    private void printFormatedTable(String[][] table, int noteProperty) {

        String[] propertyNames = new String[]{"Note pitch", "Note length", "Note volume"};

        String[] notes = table[0];
        String[] codons = table[1];

        System.out.println(String.format("| %s | Codon |", propertyNames[noteProperty]));
        System.out.println("| ------ | ------ |");

        for (int i = 0; i < notes.length; i++) {
            String tableRow = String.format("| %s | %s |", notes[i], codons[i]);
            System.out.println(tableRow);
        }

        System.out.println();
    }

    /**
     * Generate all possible 64 codons from nucleotides A, C, G and T.
     * @return ArrayList with the codons.
     */
    private ArrayList<String> getAllPossibleCodons() {

        String possibleNucleotides = "TCAG";
        StringBuilder codon = new StringBuilder();
        ArrayList<String> allCodons = new ArrayList<String>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    codon.append(possibleNucleotides.charAt(i));
                    codon.append(possibleNucleotides.charAt(j));
                    codon.append(possibleNucleotides.charAt(k));
                    allCodons.add(codon.toString());
                    codon.delete(0, 3);
                }
            }
        }
        return allCodons;
    }

    /**
     *
     * @param notePropertyIndex 0 = pitch, 1 = length, 2 = volume
     * @return
     */
    private String[][] getTranslationTable(int notePropertyIndex) {

        // used for translation
        LettersToNumbers lettersToNumbers = new LettersToNumbers();
        NumberToNote numberToNote = new NumberToNote();

        // list of all 64 possible codons
        ArrayList<String> allCodons = getAllPossibleCodons();

        // 2 arrays of equal length. index matches codon with value (e.g. codon TTT = note C2).
        // stored in 2 String[][]
        String[] notes = new String[allCodons.toArray().length];
        String[] codons = new String[allCodons.toArray().length];
        String[][] table = new String[2][];
        table[0] = notes; table[1] = codons;

        // get value for each possible codon
        for (String codon : allCodons) {

            // translate codon to number
            ArrayList<Integer> codonNumberList = lettersToNumbers.codonTranslate(codon);
            int codonNumber = codonNumberList.getFirst();

            // translation of number to note is based on position in array (0 = pitch, 1 = length, 2 = volume)
            // insert the condonNumber into the correct position, based on selected note property
            ArrayList<Integer> codonNumbers = new ArrayList<>(List.of(0, 1, 2));
            codonNumbers.add(notePropertyIndex, codonNumber);
            int codonAspectNumber = numberToNote.devideNumbers(codonNumbers).get(notePropertyIndex);

            // if the selected property is pitch, translate the midinote number to an actual note
            String codonValue;
            if (notePropertyIndex == 0) {
                codonValue = convertMidiNoteToNote(codonAspectNumber);
            } else {
                codonValue = String.valueOf(codonAspectNumber);
            }

            codons[codonNumber] = codon;
            notes[codonNumber] = codonValue;

        }
        return table;
    }

    private String convertMidiNoteToNote(int noteNumber) {
        // Source - https://stackoverflow.com/a/712686
        StringBuilder noteWithOctave = new StringBuilder();
        String notes = "C C#D D#E F F#G G#A A#B ";

        int octave = noteNumber / 12 - 1;
        String note = notes.substring((noteNumber % 12) * 2, (noteNumber % 12) * 2 + 2);

        noteWithOctave.append(note.trim());
        noteWithOctave.append(octave);

        return noteWithOctave.toString();
    }
}
