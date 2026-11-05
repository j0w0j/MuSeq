package MuSeq.error_handling;

import picocli.CommandLine;

/**
 * Class to handle errors and their messages
 */
// https://picocli.info/#_business_logic_exceptions
public class PrintExceptionMessageHandler implements CommandLine.IExecutionExceptionHandler {
    /**
     *
     * this class handles the error handling printing
     */
    public int handleExecutionException(Exception ex,
                                        CommandLine cmd,
                                        CommandLine.ParseResult parseResult) {

        // bold red error message
        cmd.getErr().println(cmd.getColorScheme().errorText(ex.getMessage()));

        return cmd.getExitCodeExceptionMapper() != null
                ? cmd.getExitCodeExceptionMapper().getExitCode(ex)
                : cmd.getCommandSpec().exitCodeOnExecutionException();
    }
}
