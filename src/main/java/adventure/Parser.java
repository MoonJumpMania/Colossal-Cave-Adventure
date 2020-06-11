package adventure;

/**
 * Parser class that manages commands.
 * @author Nasif Mauthoor
 */
public final class Parser {
    /**
     * Default constructor.
     */
    public Parser() {
    }

    /**
     * Takes a user input and computes it as a command.
     * @param input Command given by the user.
     * @throws InvalidCommandException When user inputs an invalid command.
     * @return The parsed command.
     */
    public Command parseUserCommand(String input) throws InvalidCommandException {
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

    /**
     * Outputs a list of all commands.
     * @return A string of each command in the command class.
     */
    public String allCommands() {
        String output = "Commands:";
        for (String command:Command.COMMANDS) {
            output = output + "\n" + command;
        }
        return output;
    }

    @Override
    public String toString() {
        return allCommands();
    }
}
