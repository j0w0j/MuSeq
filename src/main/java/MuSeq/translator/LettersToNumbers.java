package MuSeq.translator;

import java.util.ArrayList;
import java.util.HashMap;

/** This class will take a string of 9 nucleotides and translate them to
 * 3 numbers. Per codon a number will be generated between 0 and 63. It uses
 * the conventional order in most nucleotide-to-aminoacids translate tables.
 * These go from U(or T) to C, to A, to G per letter in de codon. Thus UUU
 * is the first option and GGG the last.
 *
 */
public class LettersToNumbers {

    /**
     * It takes the 3 codons of nucleotides and turns them into a number
     * representing the codons relative position in a translate-table.
     * Each letter of the codon adds consecutively to the total number.
     * The first in steps of 16, the second in steps of 4 and the last
     * in steps of 1.
     *
     * @param codon a string containing 9 nucleotide letters capatalized
     * @return a ArrayList containing 3 numbers each representing a codon
     */


    public ArrayList codonTranslate(String codon) {
        HashMap<String, Integer> nucleotideValues = new HashMap<String, Integer>();
        nucleotideValues.put("A", 2);
        nucleotideValues.put("C", 1);
        nucleotideValues.put("G", 3);
        nucleotideValues.put("T", 0); //in case fasta is dna
        nucleotideValues.put("U", 0); //in case fasta in rna

        ArrayList<Integer> codonNumericValues = new ArrayList<Integer>();
        int codonNumericValue = 200;

        for (int i = 0; i < codon.length(); i++) {
            String letter = Character.toString(codon.charAt(i)); //extracting singular letter to work with
            if (i % 3 == 0){ //each new codon starts here
                int firstNumber = 64/4 * nucleotideValues.get(letter); //translating letter to number
                codonNumericValue = 0;
                codonNumericValue += firstNumber;
            }
            else if (i % 3 == 1){ //second letter
                int secondNumber = 16/4 * nucleotideValues.get(letter);
                codonNumericValue += secondNumber;
            }
            else if (i % 3 == 2){ //third letter
                int thirdNumber = nucleotideValues.get(letter);
                codonNumericValue += thirdNumber;
                codonNumericValues.add(codonNumericValue); //final codonnumber added
            }
            else { //errorhandling
                System.err.println("codon to long error");
            }
        }
        return codonNumericValues;
    }
}
