package MuSeq.translator;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class LettersToNumbersTest {

    @Test
    void codonTranslateSunnyDayTest() {
        final String codon = "TTTAAACCC";
        ArrayList<Integer> expected = new ArrayList<Integer>();
        //adding corresponing number to codon
        expected.add(0);
        expected.add(42);
        expected.add(21);
        LettersToNumbers lettersToNumbers = new LettersToNumbers();
        ArrayList<Integer> result = lettersToNumbers.codonTranslate(codon);
        //codons should translate into expected numbers (translationtable in README)
        assertEquals(expected, result);
    }

}