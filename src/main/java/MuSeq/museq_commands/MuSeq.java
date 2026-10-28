package MuSeq.museq_commands;

import MuSeq.file_input.FastaReader;
import MuSeq.model.MidiData;
import MuSeq.outputhandler.BpmCalculator;
import MuSeq.outputhandler.WriteMidi;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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

        FastaReader fastaReader = new FastaReader();

        // MidiData stores MidiNotes and MidiMetadata
        MidiData midiData = new MidiData();
        // read fasta file. MidiData converts the FASTA to MIDI notes.
        try {
            fastaReader.readFasta(inputFile, midiData);
        } catch (IOException e) {
            // if file could not be read
            throw new RuntimeException(e);
        }

        int finalBpm;
        // user did not give bpm
        if (bpm == -1) {
            BpmCalculator bpmCalculator = new BpmCalculator(
                    (ArrayList<Integer>) midiData.getMidiNotesPitch(),
                    (ArrayList<Integer>) midiData.getMidiNotesLength(),
                    (ArrayList<Integer>) midiData.getMidiNotesVolume(),
                    5
                    // use first 5 of the file for bpm
            );
            finalBpm =bpmCalculator.getBpm();
        // custom bpm
        } else {
            finalBpm = bpm;
        }

        // get metadata from list of midi notes
        // int 5 gives metadata list of 15 integers!
        if (midiData.getMidiNotesPitch().size() > 5) {
            midiData.setMidiMetadata(5);


        List<Integer> pitches = midiData.getMidiNotesPitch();
        List<Integer> lengths = midiData.getMidiNotesLength();
        List<Integer> volumes = midiData.getMidiNotesVolume();

        // combine pitch, length and volume into one list per note
        List<List<Integer>> notes = new ArrayList<>();
        for (int i = 0; i < pitches.size(); i++) {
            notes.add(List.of(pitches.get(i), lengths.get(i), volumes.get(i)));
        }

        WriteMidi midiCommand = new WriteMidi(notes, outputFile, finalBpm);
        int exitCode = midiCommand.writeMidiFile();
        System.out.println("exit " + exitCode);
    }
}
