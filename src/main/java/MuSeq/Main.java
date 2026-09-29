package MuSeq;

import MuSeq.Interface.Interface;
import picocli.CommandLine;



public class Main {
    static void main(String[] args) {

//        int[] randomNumbers = {60, 62, 64, 65, 67};
//
//        int exitCode = new CommandLine(new WriteMidi(randomNumbers)).execute(args);
//        System.exit(exitCode);
//        ArrayList pitchNumber = numberToNote.calculatePitch(codonInt);
      
        int exitCode = new CommandLine(new Interface()).execute(args);
        System.exit(exitCode);
    }
}