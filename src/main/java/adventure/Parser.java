package adventure;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author Nasif Mauthoor
 */
public final class Parser {
    private Scanner scanner;

    /**
     * Default constructor.
     */
    public Parser() {
    }

    /**
     * @throws InvalidCommandException invalid command
     */
    public Command parseUserInput(String input) throws InvalidCommandException {
        String[] inputs = input.trim().split(" ", 2);
        Command command;

        switch (inputs.length) {
            case 1:
                command = new Command(inputs[0]);
                break;
            case 2:
                command = new Command(inputs[0], inputs[1]);
                break;
            default:
                command = new Command(null, null);
        }

        return command;
    }
}
