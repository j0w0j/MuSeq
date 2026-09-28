package MuSeq;

import MuSeq.Interface.Interface;
import picocli.CommandLine;

public class Main {
    static void main(String[] args) {

        int exitCode = new CommandLine(new Interface()).execute(args);
        System.exit(exitCode);
    }
}
