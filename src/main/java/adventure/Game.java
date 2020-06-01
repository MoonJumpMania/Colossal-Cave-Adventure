package adventure;
import java.io.*;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public final class Game {
    private final Parser parser = new Parser();
    private Scanner scanner = new Scanner(System.in);
    private Adventure adventure;

    /**
     * @param args -a/-l flag, filename or no arguments
     */
    public static void main(String[] args) {
        Game theGame = new Game();
        boolean isQuit = false; // Checks if the player wants to quit

        theGame.setAdventure(args); // Creates adventure
        if (theGame.adventure == null) {
            System.exit(-1);
        }

        while (!isQuit) {
            System.out.println("\n" + theGame.adventure.getCurrentRoom());
            try {
                Command command = theGame.getInputCommand(theGame.getLine());
                isQuit = !theGame.followCommand(command);
            } catch (InvalidCommandException e) {
                System.out.println("Invalid command.");
            }
        }

        theGame.promptSave();
    }

    /**
     * Loading json from default file in jar.
     * @param inputStream File input stream.
     * @return JSONObject of entire adventure.
     */
    public JSONObject loadAdventureJson(InputStream inputStream) {
        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
            return (JSONObject) new JSONParser().parse(reader);
        } catch (Exception e) {
            System.out.println("File does not exist.");
            return null;
        }
    }

    /**
     * Loading json file from chosen file.
     * @param input File name.
     * @return JSONObject of entire adventure.
     */
    public JSONObject loadAdventureJson(String input) {
        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            return (JSONObject) new JSONParser().parse(reader);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Generates an adventure from the adventure tag in the game's JSON.
     * @param obj JSONObject of the adventure.
     * @return The parsed adventure.
     */
    public Adventure generateAdventure(JSONObject obj) {
        if (obj == null) {
            return null;
        }
        return new Adventure((JSONObject) obj.get("adventure"));
    }

    public void setAdventure(String[] args) {
        if (args.length < 2) {
            InputStream stream = Game.class.getResourceAsStream("/default.json");
            adventure = generateAdventure(loadAdventureJson(stream));
            promptUsername();
        } else {
            switch (args[0]) {
                case "-l":
                    adventure = deserializeAdventure(args[1]);
                    break;
                case "-a":
                    adventure = generateAdventure(loadAdventureJson(args[1]));
                    String playerName = promptUsername();
                    adventure.setPlayerName(playerName);
                    break;
            }
        }
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

    private Command getInputCommand(String input) throws InvalidCommandException {
        try {
            return parser.parseUserInput(input);
        } catch (InvalidCommandException e) {
            throw new InvalidCommandException();
        }
    }

    private String promptUsername() {
        System.out.println("What is your name?");
        String name = getLine();
        System.out.printf("Your name is %s.\n", name);
        return name;
    }

    // Gets line from user input.
    private String getLine() {
        return scanner.nextLine();
    }

    private void promptSave() {
        System.out.println("Would you like to save? (y/n)");
        while (true) {
            String input = getLine();
            switch (input) {
                case "n":
                    return;
                case "y":
                    System.out.println("What would like to name your save file?");
                    serializeAdventure(getLine());
                    return;
                default:
                    System.out.println("Invalid input. (y/n)");
            }
        }
    }

    // Creates a save file for the adventure
    private void serializeAdventure(String saveName) {
        try {
            FileOutputStream outputStream = new FileOutputStream(saveName);
            ObjectOutputStream outputDest = new ObjectOutputStream(outputStream);
            outputDest.writeObject(adventure);
            outputDest.close();
            outputStream.close();

            System.out.println("File was saved.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private Adventure deserializeAdventure(String saveName) {
        Adventure adventureObj;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(saveName))) {
            adventureObj = (Adventure) input.readObject();

            System.out.println("Welcome back " + adventureObj.getPlayer().getName() + ".");
        } catch (Exception e) {
            adventureObj = null;
            System.out.println(e.getMessage());
        }

        return adventureObj;
    }

    @Override
    public String toString() {
        return adventure.toString();
    }
}