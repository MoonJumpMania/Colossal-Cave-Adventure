package adventure;

/* TODO add a static data structure or another enum class
that lists all the valid commands.  Then add methods for validating
commands 

You may add other methods to this class if you wish*/

import java.util.ArrayList;

public final class Command {
    private String action;
    private String noun;
    private ArrayList<Item> itemList;

    /**
     * A List of all valid actions.
     */
    public static final String[] commands =
            {
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
     * @param what      The second word of the command.
     */
    public Command(String command, String what) throws InvalidCommandException {
        if (!isValidCommand(command, what)) {
            throw new InvalidCommandException();
        }

        this.action = command;
        this.noun = what;
    }

    private boolean isValidCommand(String command, String what) {
        switch (command) {
            case "go":
                return isValidDir(what);
            case "look": case "take":
                if (what != null) {
                    if (what.isEmpty()) {
                        return false;
                    }
                }
                return true;
            case "inventory": case "quit":
                if (what == null) {
                    return true;
                }
        }
        return false;
    }

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     *
     * @return The command word.
     */
    public String getActionWord() {
        return this.action;
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getNoun() {
        return this.noun;
    }

    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord() {
        return (noun != null);
    }

    /**
     *
     * @return
     */
    public String allCommands() {
        String output = "Commands:";
        for (String command:commands) {
            output = output + "\n" + command;
        }
        return output;
    }

    /**
     * Overrided toString method.
     * @return Command on two separate lines.
     */
    @Override
    public String toString() {
        return String.format("Action: %s\nNoun: %s", action, noun);
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
