package adventure;

/**
 * Command handling class.
 * @author Nasif Mauthoor
 */
public final class Command {
    private String actionWord;
    private String noun;

    /**
     * A List of all valid actions.
     */
    public static final String[] COMMANDS = {
            "go",
            "look",
            "quit",
            "take",
            "inventory",
    };

  /**
     * Create a command object with default values.
     * both instance variables are set to null.
     */
    public Command() throws InvalidCommandException {
        this(null, null);
    }

  /**
     * Create a command object given only an action.  this.noun is set to null
     *
     * @param command The first word of the command.
     *
     */
    public Command(String command) throws InvalidCommandException {
        this(command, null);
    }

    /**
     * Create a command object given both an action and a noun
     *
     * @param command The first word of the command.
     * @param what The second word of the command.
     */
    public Command(String command, String what) throws InvalidCommandException {
        if (!isValidCommand(command, what)) {
            throw new InvalidCommandException();
        }

        this.actionWord = command;
        this.noun = what;
    }

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     * @return The command word.
     */
    public String getActionWord() {
        return this.actionWord;
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getNoun() {
        return this.noun;
    }

    /**
     * Mutator for actionWord.
     * @param aw New action word.
     */
    public void setActionWord(String aw) {
        actionWord = aw;
    }

    /**
     * Mutator for noun.
     * @param n New noun.
     */
    public void setNoun(String n) {
        noun = n;
    }

    /**
     * Checks if the command has a second word.
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord() {
        return (noun != null);
    }

    /**
     * Displays a list of all commands.
     * @return A string with the names of each valid command.
     */
    public String allCommands() {
        String output = "Commands:";
        for (String command: COMMANDS) {
            output = output + "\n" + command;
        }
        return output;
    }

    /**
     * Overridden toString method.
     * @return Command on two separate lines.
     */
    @Override
    public String toString() {
        return String.format("Action: %s\nNoun: %s", actionWord, noun);
    }


    /* Private methods */

    // Checks if command is valid
    private boolean isValidCommand(String command, String what) {
        switch (command) {
            case "go":
                return isValidDir(what);
            case "look": case "take":
                return (what != null) ? !what.isEmpty() : true;
            case "inventory": case "quit":
                return what == null;
            default:
        }
        return false;
    }

    // Checks if the given direction is valid
    private boolean isValidDir(String what) {
        switch (what) {
            case "N": case "S": case "E": case "W": case "up": case "down":
                return true;
            default:
                return false;
        }
    }
}
