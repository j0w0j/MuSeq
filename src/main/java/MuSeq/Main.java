package MuSeq;

import MuSeq.Interface.Interface;
import MuSeq.file_input.FastaToMidiNotes;
import MuSeq.outputhandler.WriteMidi;
import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new Interface()).execute(args);
        System.exit(exitCode);
    }
}