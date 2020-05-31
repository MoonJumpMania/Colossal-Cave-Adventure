package adventure;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public final class Game {
    private Parser parser = new Parser();
    private Adventure adventure;

    /**
     * @param args -a/-l flag, filename or no arguments
     */
    public static void main(String[] args) {
        Game theGame = new Game();
        Boolean isQuit; // Checks if the player wants to quit
        theGame.setAdventure(args); // Creates adventure
        String playerName = theGame.promptUsername();
        if (theGame.adventure == null) {
            System.out.println("Adventure Error.");
            return;
        }
        do {
            System.out.println(theGame.adventure.getCurrentRoom());
            isQuit = theGame.followCommand(theGame.getInputCommand());
            if (isQuit == null) {
                System.out.println("Invalid command.");
            }
        } while (isQuit != true);
        theGame.promptSave();
    }

    /**
     * @param inputStream File input stream.
     * @return JSONObject of entire adventure.
     */
    public JSONObject loadAdventureJson(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
            return (JSONObject) new JSONParser().parse(reader);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Generates an adventure from the adventure tag in the game's json
     * @param obj JSONObject of the adventure
     * @return parsed adventure
     */
    public Adventure generateAdventure(JSONObject obj) {
        if (obj == null) {
            return null;
        }
        System.out.println(obj);
        return new Adventure((JSONObject) obj.get("adventure"));
    }

    // Private methods

    // Combination of both loadAdventureJson() and generateAdventure()
    public void setAdventure(String[] args) {
        adventure =  generateAdventure(loadAdventureJson(getInputStream(args)));
    }

    // Calls the getFilename method from the Parser class
    private InputStream getInputStream(String[] args) {
        return parser.getInputStream(args);
    }

    // Follows the command from user input
    // Returns false if player wants to quit
    private boolean followCommand(Command command) {
        switch (command.getActionWord()) {
            case "go":
                adventure.movePlayer(command.getNoun());
                break;
            case "look":
                System.out.println(adventure.lookAt(command.getNoun()));
                break;
            case "take":
                System.out.println(adventure.takeItem(command.getNoun()));
                break;
            case "inventory":
                System.out.println(adventure.checkInventory());
                break;
            case "quit":
                return false;
        }
        return true;
    }

    private Command getInputCommand() {
        try {
            return parser.parseUserInput(parser.getLine());
        } catch (InvalidCommandException e) {
            return null;
        }
    }

    private String promptUsername() {
        System.out.println("What is your name?");
        String name = parser.getLine();
        System.out.printf("Your name is %s.\n", name);
        return name;
    }

    private void promptSave() {
        System.out.printf("Would you like to save?");
        parser.getLine();

    }

    @Override
    public String toString() {
        return null;
    }
}