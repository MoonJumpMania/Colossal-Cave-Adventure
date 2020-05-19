package adventure;
import java.io.FileReader;
import java.util.Scanner;
import java.io.BufferedReader;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public final class Game{

    public static void main(String args[]){
        Game theGame = new Game();

        boolean isExited = false;
        String fileName = "src/main/java/adventure/example_adventure.json"; // Default file
        Scanner scanner = new Scanner(System.in); // User input
        Adventure adventureObject;

        System.out.println("Welcome to Colossal Cave Adventure!");
        System.out.println("Would you like to load a JSON file? (Y/n)");
        String input = scanner.nextLine().trim();

        JSONObject jsonObject;
        do { // Keeps looping until file is found
            if (input.equalsIgnoreCase("y")){
                System.out.println("Enter your file name: ");
                fileName = scanner.nextLine().trim(); // User inputs file name
            }
            jsonObject = theGame.loadAdventureJson(fileName);
        } while (jsonObject == null);

        adventureObject = theGame.generateAdventure(jsonObject);

        while (!isExited){ // Continue until exited
            System.out.println(adventureObject.getCurrentRoom().getShortDescription());
            input = scanner.nextLine().toLowerCase().trim();
            String command = theGame.getCommand(adventureObject, input);
            if (command == "quit"){
                isExited = true;
            }
        }
    }

    public JSONObject loadAdventureJson(String filename){
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();

        // Reads file
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            jsonObject = (JSONObject) parser.parse(reader);
        } catch (Exception e){
            System.out.println("File not found. Please try again.");
            jsonObject = null;
        }

        return jsonObject;
    }

    public Adventure generateAdventure(JSONObject obj){
        Game theGame = new Game(); // I want static functions :(
        Adventure adventureObject = new Adventure();

        JSONObject adventureJSON = (JSONObject) obj.get("adventure");

        if (adventureJSON == null){ // Invalid if true
            return null;
        }

        JSONArray itemList = (JSONArray) adventureJSON.get("item"); // List of items
        JSONArray roomList = (JSONArray) adventureJSON.get("room"); // List of rooms

        for (Object room : roomList){
            JSONObject roomJSON = (JSONObject) room;

            Room roomObject = theGame.jsonToRoom(roomJSON); // Turn JSON into
            roomObject.setAdventure(adventureObject); // Sets the current adventure to the room
            adventureObject.addRoom(roomObject); // Adds room to adventure

            if (roomJSON.get("start") != null){ // Finds the initial room
                adventureObject.setCurrentRoom(roomObject);
            }
        }

        return adventureObject;
    }

    // Turns the Room json object into a room object
    public Room jsonToRoom(JSONObject roomJSON){
        Room roomObject = new Room();

        // Gets all needed tags
        roomObject.setName((String) roomJSON.get("name"));
        roomObject.setId((long) roomJSON.get("id"));
        roomObject.setShortDescription((String) roomJSON.get("short_description"));
        roomObject.setLongDescription((String) roomJSON.get("long_description"));

        // Set neighboring rooms
        JSONArray entrances = (JSONArray) roomJSON.get("entrance");
        for (Object entranceObject : entrances){
            JSONObject entrance = (JSONObject) entranceObject;

            // Set which id's are connected to each room
            long id = (long) entrance.get("id");
            String direction = (String) entrance.get("dir");
            roomObject.setEntrance(direction, id);
        }
        return roomObject;
    }

    // Turns the Item json object into a item object
    public Item jsonToItem(JSONObject itemJSON){
        Item itemObject = new Item();

        // Gets all needed tags
        itemObject.setName((String) itemJSON.get("name"));
        itemObject.setId((long) ((long) itemJSON.get("id")));
        itemObject.setDescription((String) itemJSON.get("description"));

        return itemObject;
    }

    // Gets input commands
    public String getCommand(Adventure adventure, String input){
        String[] command = input.split(" "); // Splits into commands

        switch (command[0].charAt(0)){ // Matches the first letter of command
            case 'q':
                System.out.println("Thank you for playing!");
                return "quit";
            case 'g':
                String dir = Character.toString(command[1].charAt(0));
                long id = adventure.getCurrentRoom().getConnectedRoom(dir).getId();
                adventure.setCurrentRoom(adventure.getRoomFromID(id));
                return "go";
            case 'l':
                if (command[1] == null){
                    String look = adventure.getCurrentRoom().getShortDescription();
                    System.out.println(look);
                }else{
                    for(Item item : adventure.getCurrentRoom().listItems()){
                        if (command[1] == item.getName()){
                            System.out.println(item.getDescription());
                        }
                    }
                    System.out.println("This room doesn't contain this item.");
                }
                return "look";
            default:
                System.out.println("Invalid input.");
                return null;
        }
    }
}