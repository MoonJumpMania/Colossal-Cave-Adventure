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

        if (inputs.length == 1) {
            return new Command(inputs[0]);

        } else if (inputs.length == 2) {
            return new Command(inputs[0], inputs[1]);

        } else {
            return new Command(null, null);
        }
    }

    /**
     * Outputs a list of all commands.
     * @return A string of each command in the command class.
     */
    public String allCommands() {
        StringBuilder output = new StringBuilder("Commands:");
        for (String command:Command.COMMANDS) {
            output.append("\n").append(command);
        }
        return output.toString();
    }

    @Override
    public String toString() {
        return allCommands();
    }
}
