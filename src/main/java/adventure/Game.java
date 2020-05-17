package adventure;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.Reader;
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

        boolean fileFound = false;

        String defaultFile = "example_adventure.json";
        String fileName = defaultFile; // Default is default file

        Game theGame = new Game();

        Scanner scanner = new Scanner(System.in);

        JSONObject adventure_json;

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

            adventure_json = theGame.loadAdventureJson(fileName);
        } while (adventure_json == null);

        JSONArray room_list = (JSONArray) adventure_json.get("room");

        for (Object room : room_list) {
            JSONObject current_room = (JSONObject) room;

        }

        // 4. Print the beginning of the adventure


        // 5. Begin game loop here
        while () {

            // 6. Get the user input. You'll need a Scanner

            /* 7+. Use a game instance method to parse the user
            input to learn what the user wishes to do*/

            //use a game instance method to execute the users wishes*/

            /* if the user doesn't wish to quit,
            repeat the steps above*/
        }
    }

    /* you must have these instance methods and may need more*/

    public JSONObject loadAdventureJson(String filename){
        JSONObject adventure_json = null;
        JSONParser parser = new JSONParser();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            adventure_json = (JSONObject) ((JSONObject) parser.parse(reader)).get("adventure");
        } catch (Exception e) {
            System.out.println("File not found. Please try again.");
        }

        return adventure_json;
    }

    public Adventure generateAdventure(JSONObject obj) {

    }

}