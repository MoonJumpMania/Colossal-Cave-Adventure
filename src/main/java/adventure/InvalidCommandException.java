package adventure;

/**
 * @author Nasif Mauthoor
 * Exception class for providing an invalid command into the Command class.
 */
public class InvalidCommandException extends Exception {
    /**
     * Gets the exception message of the InvalidCommandException.
     * @return Exception message.
     */
    @Override
    public String getMessage() {
        return "Invalid command.";
    }
}
