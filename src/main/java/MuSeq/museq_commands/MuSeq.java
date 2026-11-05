package MuSeq.museq_commands;

import MuSeq.file_input.FastaReader;
import MuSeq.model.MidiData;
import MuSeq.translator.BpmCalculator;
import MuSeq.outputhandler.WriteMidi;
import MuSeq.translator.InstrumentCalculator;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.List;


@Command(name = "MuSeq", version = "1.0", mixinStandardHelpOptions = true,
        description = "Translate your dna or rna into a melody")

/**
 * Convert a FASTA file containing DNA or RNA sequences to a MIDI file.
 */
public class MuSeq implements Runnable{

    /**
     * all possible commandline arguments and their description
     */
    @Option(names = {"-i", "--instrument"}, description = "Instrument number standard per MIDI between 0-127. If not specified, it will be calculated using the inputfile")
    Integer instrument;

    @Option(names = {"-t", "--tempo"}, description = "Music tempo (bpm) if not specified, it will be calculated using the inputfile")
    Integer bpm;

    @Option(names = {"-f", "--filter"},
            description = "Text used to filter headers. If the text is not in a header, the corresponding sequence will not be translated to music.\n" +
                    "Matching is case insensitive.\n" +
                    "Example usages:\n" +
                    "\t -h \"chromosome 1\"\n" +
                    "\t -h \"BRCA1\"")
    String headerFilter;

    @Option(names = {"-o", "--output"}, description = "Name of the output MIDI file. Filepath including filename ending with .mid. File cannot already exist.")
    String outputFile;

    // INPUT FASTA FILE

    @Parameters(paramLabel = "Input FASTA file", description = "Filepath to input FASTA file to convert to MIDI")
    String inputFile;

    /**
     * excuting using user input
     */
    @Override
    public void run() {
        FastaReader fastaReader = new FastaReader();

        // MidiData stores the Midi sequence, track, and metadata.
        MidiData midiData = new MidiData(10);

        // read fasta file. MidiData converts the FASTA to MIDI.
        try {
            fastaReader.readFasta(inputFile, midiData, headerFilter);
        } catch (IOException e) {
            // if file could not be read
            throw new RuntimeException(e);
        }

        // list of codons reserved for metadata. see readme: https://github.com/j0w0j/MuSeq#metadata
        List<Integer> metadata = midiData.getMidiMetadata();

        int finalBpm;
        // user did not give bpm, get bpm from metadata
        if (bpm == null) {
            BpmCalculator bpmCalculator = new BpmCalculator(metadata.getFirst()); // first codon is for bpm
            finalBpm = bpmCalculator.getBpm();
        // if user did give bpm, use that (if valid)
        } else if (bpm > 0) {
            finalBpm = bpm;
        } else {
            throw new IllegalArgumentException(String.format("Bpm should be greater than 0! Current input bpm: %d", bpm));
        }

        int finalInstrument;
        // user did not give instrument, get instrument from metadata
        if (instrument == null) {
            InstrumentCalculator instrumentCalculator = new InstrumentCalculator(metadata.get(1), metadata.get(2)); // second and third codon are for the instrument
            finalInstrument = instrumentCalculator.getInstrument();
        // if user did give instrument, use that (if valid)
        } else if (instrument >= 0 & instrument <= 127) {
            finalInstrument = instrument;
        } else {
            throw new IllegalArgumentException(String.format("Instrument number %d is not a valid option! Valid options are from 0 to 127.", instrument));
        }

        WriteMidi midiCommand = new WriteMidi(
                midiData.getSequence(), midiData.getTrack(),
                outputFile, finalBpm, finalInstrument);
        int exitCode = midiCommand.writeMidiFile();
    }
}
