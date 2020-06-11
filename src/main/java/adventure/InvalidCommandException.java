package adventure;

/**
 * Exception class for providing an invalid command into the Command class.
 * @author Nasif Mauthoor
 */
public class InvalidCommandException extends Exception {
    /**
     * Default constructor.
     */
    public InvalidCommandException() {
    }

    /**
     * Gets the exception message of the InvalidCommandException.
     * @return Exception message.
     */
    @Override
    public String getMessage() {
        return "Invalid command.";
    }

    /**
     * Returns the getMessage method output.
     * @return The getMessage method output.
     */
    @Override
    public String toString() {
        return getMessage();
    }
}
