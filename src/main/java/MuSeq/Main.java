package MuSeq;

import MuSeq.museq_commands.MuSeq;
import MuSeq.error_handling.PrintExceptionMessageHandler;
import picocli.CommandLine;

/**
 * Main class excuting program based on commandline arguments given by user
 */
public class Main {
    public static void main(String[] args) {
        try {
            int exitCode = new CommandLine(new MuSeq())
                    .setExecutionExceptionHandler(new PrintExceptionMessageHandler())
                    .execute(args);

            System.exit(exitCode);
        }
        catch (OutOfMemoryError e) {
            System.out.println("ERROR: Ran out of memory!");
            System.out.println("If possible, try increasing the amount of memory Java is allowed to use with the -Xmx<n> option.");
            System.out.println("Example usage:");
            System.out.println("\njava -jar -Xmx8G .\\build\\libs\\MuSeq-1.0-SNAPSHOT-all.jar <input.fasta>\n");
            System.out.println("For more information about this, see the README Usage section");
        }
    }
}