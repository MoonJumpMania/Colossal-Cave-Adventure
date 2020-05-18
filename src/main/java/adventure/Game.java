package adventure;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;

// JSON libraries
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Game{

    /* this is the class that runs the game.
    You may need some member variables */

    public static void main(String args[]){

        /* You will need to instantiate an object of type
        game as we're going to avoid using static methods
        for this assignment */

        Game theGame = new Game();

        boolean fileFound = false;
        boolean isExited = false;

        String defaultFile = "example_adventure.json";
        String fileName = defaultFile; // Default is default file

        ArrayList<Room> rooms = new ArrayList<Room>();

        Scanner scanner = new Scanner(System.in);

        JSONObject jsonObject;

        Adventure adventureObject;

        // 1. Print a welcome message to the user
        System.out.println("Welcome to Colossal Cave Adventure!");

        // 2. Ask the user if they want to load a json file.
        System.out.println("Would you like to load a JSON file? (Y/n)");
        String input = scanner.nextLine().trim();

        /* 3. Parse the file the user specified to create the
        adventure, or load your default adventure*/
        do {
            if (input.equalsIgnoreCase("y")) {
                System.out.println("Enter your file name: ");
                fileName = scanner.nextLine().trim(); // User inputs file name
            }

            jsonObject = theGame.loadAdventureJson(fileName);
        } while (jsonObject == null);



        adventureObject = theGame.generateAdventure(jsonObject);

        // 4. Print the beginning of the adventure


        // 5. Begin game loop here
        while (true) {
            // 6. Get the user input. You'll need a Scanner
            input = scanner.nextLine().toLowerCase().trim();
            String[] command = input.split(","); // Splits into commands

            /* 7+. Use a game instance method to parse the user
            input to learn what the user wishes to do*/
            switch (command[0].charAt(0)){ // Matches the first letter
                case 'q':
                    System.out.println("Thank you for playing!");
                    return;

                case 'g':


                case 'l':

                    break;

                default:
                    System.out.println("Invalid input.");
            }

            //use a game instance method to execute the users wishes*/

            /* if the user doesn't wish to quit,
            repeat the steps above*/
        }
    }

    /* you must have these instance methods and may need more*/

    public JSONObject loadAdventureJson(String filename){
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();

        // Reads file
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            jsonObject = (JSONObject) parser.parse(reader);
        } catch (Exception e) {
            System.out.println("File not found. Please try again.");
            jsonObject = null;
        }

        return jsonObject;
    }

    public Adventure generateAdventure(JSONObject obj) {
        Adventure adventureObject = new Adventure();

        // Gets the adventure
        JSONObject adventureJSON = (JSONObject) obj.get("adventure");

        // File is invalid if true
        if (adventureJSON == null){
            return null;
        }

        // Puts each room into array
        JSONArray roomList = (JSONArray) adventureJSON.get("room");

        for (Object room : roomList) {
            Room roomObject = new Room();
            JSONObject roomJSON = (JSONObject) room;

            roomObject.setName((String) roomJSON.get("name")); // Adds the name
            roomObject.setId((int) roomJSON.get("id")); // Adds id


            // Sets the initial room if it contains "start" tag
            if (roomJSON.get("start") != null){
                adventureObject.setCurrentRoom(roomObject);
            }

            adventureObject.addRoom(roomObject); // Room added to adventure
        }

        return adventureObject;
    }

}