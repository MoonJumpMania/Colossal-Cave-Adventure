package adventure;
import java.awt.geom.AffineTransform;
import java.io.FileReader;
import java.util.Scanner;
import java.io.BufferedReader;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public final class Game {
    private Parser parser = new Parser();
    private Adventure adventure;

    /**
     * @param args -a/-l flag, filename or no arguments
     */
    public static void main(String[] args) {
        Game theGame = new Game();
        theGame.adventure = theGame.compileAdventure(args);
        Boolean isQuit = true;

        do {
            isQuit =  theGame.followCommand(theGame.getInputCommand());
            if (isQuit == null) {
                System.out.println("Invalid command.");
            }
        } while (isQuit == false);

        System.out.println("Thanks for playing!");
    }

    /**
     * @param filename name of json file
     * @return JSONObject of entire adventure
     */
    public JSONObject loadAdventureJson(String filename) {
        JSONObject jsonObject;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            jsonObject = (JSONObject) new JSONParser().parse(reader);
        } catch (Exception e) {
            System.out.println("File not found. Please try again.");
            jsonObject = null;
        }
        return jsonObject;
    }

    /**
     * Generates an adventure from the adventure tag in the game's json
     * @param obj JSONObject of the adventure
     * @return parsed adventure
     */
    public Adventure generateAdventure(JSONObject obj) {
        return new Adventure((JSONObject) obj.get("adventure"));
    }

    // Private methods

    // Combination of both loadAdventureJson() and generateAdventure()
    private Adventure compileAdventure(String[] args) {
        return generateAdventure(loadAdventureJson(getFilename(args)));
    }

    // Calls the parseUserInput method from the Parser class
    private Command parse(String input) throws InvalidCommandException {
        return parser.parseUserInput(input);
    }

    // Calls the getFilename method from the Parser class
    private String getFilename(String[] args) {
        return parser.getFilename(args);
    }

    // Follows the command from user input
    // Returns false if player wants to quit
    private Boolean followCommand(Command command) {
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
            default:
                return null;
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

    @Override
    public String toString() {
        return null;
    }
}