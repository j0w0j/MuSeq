package MuSeq;

import MuSeq.inputhandler.InputHandler;
import picocli.CommandLine;

public class Main {
    static void main(String[] args) {

        int exitCode = new CommandLine(new InputHandler()).execute(args);
        System.exit(exitCode);
    }
}
