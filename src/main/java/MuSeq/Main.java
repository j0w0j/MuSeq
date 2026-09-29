import MuSeq.outputhandler.WriteMidi;
import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        // array wiht notes
        int[] myMelody = {60, 62, 64, 60}; // C, D, E, C

        WriteMidi midiCommand = new WriteMidi(myMelody);

        // commando picocli
        int exitCode = new CommandLine(midiCommand).execute(args);

        System.out.println("exit" + exitCode);
    }
}