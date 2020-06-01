package adventure;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author Nasif Mauthoor
 */
public final class Parser {
    private Scanner scanner;

    public Parser() {
        scanner = new Scanner(System.in);
    }

    public Parser(Scanner s) {
        scanner = s;
    }

    /**
     * @throws InvalidCommandException invalid command
     */
    public Command parseUserInput(String input) throws InvalidCommandException {
        String[] inputs = scanner.nextLine().split(" ", 2);
        Command command;

        switch (inputs.length) {
            case 1:
                command = new Command(inputs[0]);
            case 2:
                command = new Command(inputs[0], inputs[1]);
            default:
                command = new Command(null, null);
        }
        return command;
    }

    /**
     * Gets one line from user input.
     * @return Input from user.
     */
    public String getLine() {
        return scanner.nextLine();
    }
}
