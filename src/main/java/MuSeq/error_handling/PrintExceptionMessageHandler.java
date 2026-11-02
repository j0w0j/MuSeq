package MuSeq.error_handling;

import picocli.CommandLine;

/**
 * Class to handle errors and their messages
 */
// https://picocli.info/#_business_logic_exceptions
public class PrintExceptionMessageHandler implements CommandLine.IExecutionExceptionHandler {
    /**
     *
     * @param ex the Exception thrown by the {@code Runnable}, {@code Callable} or {@code Method} user object of the command
     * @param cmd the CommandLine representing the command or subcommand where the exception occurred
     * @param parseResult the result of parsing the command line arguments.
     *                        This is the ParseResult of the <b>top-level command</b>.
     *               Note that if the exception occurred in a subcommand, you may want to inspect the ParseResult of
     *               the subcommand that threw the exception, which can be obtained by calling {@code commandLine.getParseResult()}
     *               on the CommandLine object passed to this method.
     * @return TODO
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
