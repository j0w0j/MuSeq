package MuSeq.museq_commands;

import MuSeq.file_input.MetadataGetter;
import MuSeq.file_input.FastaToMidiNotesConverter;
import MuSeq.outputhandler.Synthesizer;
import MuSeq.outputhandler.WriteMidi;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Documentatie van de MuSeq TODO
 */
@Command(name = "MuSeq", version = "versie nummer!", mixinStandardHelpOptions = true,
        subcommands = {Sing.class, CommandLine.HelpCommand.class},
        description = "vet coole description voor MuSeq")
public class MuSeq {

}

/**
* Convert a FASTA file containing DNA or RNA sequences to a MIDI file.
 * TODO misschien optie toevoegen voor synthizer, of deze wel of niet gebruikt moet worden?
 */
@Command(name = "sing", mixinStandardHelpOptions = true,
        description = "vet coole description voor MuSeq sing")
class Sing implements Runnable {

    // ALL OPTIONS
    // TODO goeie default waarden instellen

    @Option(names = {"-i", "--instrument"}, description = "Instrument name")
    String instrument = "";

    @Option(names = {"-t", "--tempo"}, description = "Music tempo (bpm)")
    int bpm = -1;

    @Option(names = {"-c", "--chr"}, description = "Specific chromosome by number to convert to MIDI")
    int chromosomeNumber = -1;

    @Option(names = {"-g", "--gene"}, description = "Specific gene by gene ID to convert to MIDI")
    String geneID = "";

    @Option(names = {"-o", "--output"}, description = "Name of the output MIDI file")
    String outputFile = "";

    // INPUT FASTA FILE

    @Parameters(paramLabel = "Input FASTA file", description = "Input FASTA file to convert to MIDI")
    String inputFile = "";

    // SING FUNCTIONALITY

    @Override
    public void run() {
        // als testje print het nu alle opties TODO dit weghalen uiteindelijk
        System.out.println("Options:");
        System.out.println("instrument: " + instrument);
        System.out.println("bpm: " + bpm);
        System.out.println("chromosome: " + chromosomeNumber);
        System.out.println("gene: " + geneID);
        System.out.println("output: " + outputFile);
        System.out.println("input: " + inputFile);

        // make list of midi notes
        FastaToMidiNotesConverter converter = new FastaToMidiNotesConverter();
        ArrayList<Integer> midiNotes = converter.convertFastaToMidiNotes(inputFile);

        // get metadata from list of midi notes
        MetadataGetter metaGetter = new MetadataGetter();
        // amount n is the amount of notes/integers that is used as metadata
        Integer[] midiMetadata = metaGetter.getMetadata(midiNotes, 15);

        System.out.println("\nOUTPUT:"); // test printje TODO dit weghalen uiteindelijk
        System.out.println("midiMetadata: " + Arrays.toString(midiMetadata)); // test printje TODO dit weghalen uiteindelijk
        System.out.println("midiNotes: " + midiNotes); // test printje TODO dit weghalen uiteindelijk

        // TODO functie die midi bestand schrijft?
        //  input is ArrayList<Integer>
        WriteMidi writeMidi = new WriteMidi(new int[] {1,2,3,4,5,6});

        // TODO Synthesizer doet nu nog niks
        Synthesizer synthesizer = new Synthesizer();
    }
}