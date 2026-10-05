package MuSeq;

import MuSeq.Interface.Interface;
import MuSeq.error_handling.PrintExceptionMessageHandler;
import picocli.CommandLine;



public class Main {
    static void main(String[] args) {
      
        int exitCode = new CommandLine(new Interface())
                .setExecutionExceptionHandler(new PrintExceptionMessageHandler())
                .execute(args);
        System.exit(exitCode);
    }
}