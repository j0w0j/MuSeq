package MuSeq;

import MuSeq.outputhandler.WriteMidi;
import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        int[] randomNumbers = {60, 62, 64, 65, 67};

        int exitCode = new CommandLine(new WriteMidi(randomNumbers)).execute(args);
        System.exit(exitCode);
    }
}