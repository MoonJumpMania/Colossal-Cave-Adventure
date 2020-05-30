package adventure;
import java.util.Scanner;

/**
 * @author Nasif Mauthoor
 */
public class Parser {
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
     * @param args arguments from command line
     * @return filename
     */
    public String getFilename(String[] args) {
        if (args.length >= 2) {
            switch (args[0]) {
                case "-l":
                    // TODO: 5/25/2020
                case "-a":
                    return args[1];
            }
        }

        return "src/main/resources/default.json";
    }
}
