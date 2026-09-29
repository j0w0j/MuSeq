package MuSeq;

import MuSeq.outputhandler.WriteMidi;
import picocli.CommandLine;
import MuSeq.translator.LettersToNumbers;
import MuSeq.translator.NumberToNote;
import java.util.ArrayList;


public class Main {
    static void main() {

        // testing codon
        String codon1 = "UUUGGGAAA";

        //first class resulting in 3 numbers
        LettersToNumbers lettersToNumbers = new LettersToNumbers();
        ArrayList codonInt = lettersToNumbers.codonTranslate(codon1);
        System.out.println("het getal gemaakt is " + codonInt);

        //second class resulting in 3 new numbers
        NumberToNote numberToNote = new NumberToNote();
        System.out.println(numberToNote.devideNumbers(codonInt));
      
        int[] randomNumbers = {60, 62, 64, 65, 67};

        int exitCode = new CommandLine(new WriteMidi(randomNumbers)).execute(args);
        System.exit(exitCode);
//        ArrayList pitchNumber = numberToNote.calculatePitch(codonInt);
      
        int exitCode = new CommandLine(new Interface()).execute(args);
        System.exit(exitCode);
    }
}