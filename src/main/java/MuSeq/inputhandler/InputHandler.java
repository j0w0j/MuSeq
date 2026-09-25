package MuSeq.inputhandler;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


/**
 * Documentatie van de MuSeq TODO
 */
@Command(name = "MuSeq", version = "versie nummer!", mixinStandardHelpOptions = true,
        subcommands = {Sing.class, CommandLine.HelpCommand.class},
        description = "vet coole description voor MuSeq")
public class InputHandler {

}

/**
* Documentatie van de subcommand sing TODO
 */
@Command(name = "sing", mixinStandardHelpOptions = true,
        description = "vet coole description voor MuSeq sing")
class Sing implements Runnable {

    // ALLE OPTIES

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

    // INPUT BESTAND

    @Parameters(paramLabel = "Input FASTA file", description = "Input FASTA file to convert to MIDI")
    String inputFile = "";

    // SING FUNCTIONALITEIT

    @Override
    public void run() {
        // als testje print het nu alle opties TODO dit weghalen uiteindelijk
        System.out.println("instrument: " + instrument);
        System.out.println("bpm: " + bpm);
        System.out.println("chromosome: " + chromosomeNumber);
        System.out.println("gene: " + geneID);
        System.out.println("output: " + outputFile);
        System.out.println("input: " + inputFile);

        // print voor nu alleen het bestand naar de terminal TODO dit weghalen/vervangen uiteindelijk
        FastaReader reader = new FastaReader();
        reader.readFasta(inputFile);
    }
}