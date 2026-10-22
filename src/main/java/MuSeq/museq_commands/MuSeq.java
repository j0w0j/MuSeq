package MuSeq.museq_commands;

import MuSeq.file_input.MetadataGetter;
import MuSeq.file_input.FastaToMidiNotesConverter;
import MuSeq.outputhandler.Synthesizer;
import MuSeq.outputhandler.WriteMidi;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Documentatie van de MuSeq TODO
 * Convert a FASTA file containing DNA or RNA sequences to a MIDI file.
 * TODO misschien optie toevoegen voor synthizer, of deze wel of niet gebruikt moet worden?
 */
@Command(name = "MuSeq", version = "versie nummer!", mixinStandardHelpOptions = true,
        description = "vet coole description voor MuSeq")
public class MuSeq implements Runnable{
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
        System.out.println("instrument: " + instrument);
        System.out.println("bpm: " + bpm);
        System.out.println("chromosome: " + chromosomeNumber);
        System.out.println("gene: " + geneID);
        System.out.println("output: " + outputFile);
        System.out.println("input: " + inputFile);

        FastaToMidiNotesConverter converter = new FastaToMidiNotesConverter();

        // read fasta file and make ArrayList of integers (midi notes)
        ArrayList<Integer> midiNotes = null;
        try {
            midiNotes = converter.convertFastaToMidiNotes(inputFile);
        } catch (IOException e) {
            // if file could not be read
            throw new RuntimeException(e);
        }

        // get metadata from list of midi notes
        MetadataGetter metaGetter = new MetadataGetter();
        // amount n is the amount of notes/integers that is used as metadata
        Integer[] midiMetadata = metaGetter.getMetadata(midiNotes, 15);

        int[] midiNotesArray = midiNotes.stream().mapToInt(i -> i).toArray();

        WriteMidi midiCommand = new WriteMidi(midiNotesArray, outputFile);
        int exitCode = midiCommand.writeMidiFile();
        System.out.println("exit " + exitCode);
    }
}
