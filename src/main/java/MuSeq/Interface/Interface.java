    package MuSeq.Interface;

    import MuSeq.file_input.FastaToMidiNotes;
    import MuSeq.translator.NumberToNote;
    import MuSeq.translator.LettersToNumbers;
    import MuSeq.outputhandler.WriteMidi;
    import picocli.CommandLine;
    import picocli.CommandLine.Command;
    import picocli.CommandLine.Option;
    import picocli.CommandLine.Parameters;

    import java.util.ArrayList;


    /**
     * Documentatie van de MuSeq TODO
     */
    @Command(name = "MuSeq", version = "versie nummer!", mixinStandardHelpOptions = true,
            subcommands = {Sing.class, CommandLine.HelpCommand.class},
            description = "vet coole description voor MuSeq")
    public class Interface {

    }

    /**
    * Convert a FASTA file containing DNA or RNA sequences to a MIDI file.
     * TODO misschien optie toevoegen voor synthizer, of deze wel of niet gebruikt moet worden?
     */
    @Command(name = "sing", mixinStandardHelpOptions = true,
            description = "vet coole description voor MuSeq sing")
    class Sing implements Runnable {
