package MuSeq;

import MuSeq.museq_commands.MuSeq;
import MuSeq.error_handling.PrintExceptionMessageHandler;
import picocli.CommandLine;



public class Main {
    static void main(String[] args) {
      
        int exitCode = new CommandLine(new MuSeq())
                .setExecutionExceptionHandler(new PrintExceptionMessageHandler())
                .execute(args);
        System.exit(exitCode);
    }
}