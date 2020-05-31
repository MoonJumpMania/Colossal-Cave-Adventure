package adventure;
import java.io.InputStream;
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
     * Gets the InputStream from command line arguments.
     * @param args Arguments from command line.
     * @return InputStream to the file
     */
    public InputStream getInputStream(String[] args) {
        String stream = new String();
        if (args.length >= 2) {
            switch (args[0]) {
                case "-l":
                    // TODO: 5/25/2020
                    break;
                case "-a":
                    stream = args[1];
                    break;
            }
        } else {
            stream = "/src/main/resources/default.json";
        }

        InputStream steam = Parser.class.getClassLoader().getResourceAsStream(stream);
        System.out.printf(String.valueOf(steam));
        return steam;
    }

    public String getLine() {
        return scanner.nextLine();
    }
}
