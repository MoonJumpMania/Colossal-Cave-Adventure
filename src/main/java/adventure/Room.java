package adventure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class Room {
    /* you will need to add some private member variables */
    private Adventure adventure;
    private String name;
    private String shortDescription;
    private String longDescription;
    private long id;
    private HashMap<String, Room> entrances;
    private ArrayList<Item> itemList;
    private JSONArray entranceJSON;
    private JSONArray itemJSON;

    /**
     * Default constructor
     */
    public Room() {
        adventure = new Adventure();
        name = new String();
        shortDescription = new String();
        longDescription = new String();
        entrances = new HashMap<>();
        itemList = new ArrayList<>();
    }

    /**
     * @param advObj adventure object initialized in main
     * @param roomJSON
     */
    public Room(Adventure advObj, JSONObject roomJSON) {
        adventure = advObj;
        name = (String) roomJSON.get("name");
        shortDescription = (String) roomJSON.get("short_description");
        longDescription = (String) roomJSON.get("long_description");
        id = (long) roomJSON.get("id");
        entranceJSON = (JSONArray) roomJSON.get("entrance"); // Cannot initialize yet
        itemJSON = (JSONArray) roomJSON.get("loot");
    }

    /* required public methods */

    /**
     * Prints all items in room and returns the list of items.
     * @return list of items in this instance
     */
    public ArrayList<Item> listItems(){
        for (Item item:itemList) {
            System.out.println(item);
        }
        return itemList;
    }

    /**
     * Returns the name of the room.
     * @return Name of this room.
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the short of this room.
     * @return The short description of this room.
     */
    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription(){
        return longDescription;
    }

    /**
     * Gets this room's list of items.
     * @return This room's item list.
     */
    public ArrayList<Item> getItemList() {
        return itemList;
    }

    /**
     * @param direction one of: "N", "S", "E", "W", "up", "down"
     * @return Room in given direction
     */
    public Room getConnectedRoom(String direction) {
        return entrances.get(direction);
    }

    /**
     * Sets the entrances of room.
     */
    public void setEntrances() {
        for (Object obj: entranceJSON) {
            JSONObject entranceJSON = (JSONObject) obj ;
            String key = (String) entranceJSON.get("dir");
            Room room = adventure.getRoomFromID((Long) entranceJSON.get("id"));
            entrances.putIfAbsent(key, room);
        }
    }

    /**
     * Returns each entrance room that is in the hashmap.
     */
    public String getEntrances() {
        AtomicReference<String> output = new AtomicReference<>("");
        // Goes through each hashmap entry and adds their information to the output string.
        entrances.entrySet().stream().forEach(e ->
                output.set(output + e.getKey() + ": " + e.getValue().getName()));
        return output.get();
    }

    /**
     * @return Getter for this room's ID.
     */
    public long getID() {
        return id;
    }

    /**
     * Adds items from the JSON file into the array list.
     */
    public void setItemList() {
        for (Object obj:itemJSON) {
            JSONObject itemJSON = (JSONObject) obj;
            itemList.add(new Item(adventure, this,itemJSON));
        }
    }

    /**
     * Add item to this room's list of items.
     * @param item Item that will be removed from this room's list.
     */
    public void removeItem(Item item) {
        itemList.remove(item);
    }

    /**
     * Remove an item from this room's list of items.
     * @param item Item that will be added to this room's list.
     */
    public void addItem(Item item) {
        itemList.add(item);
    }

    /**
     * @param roomName Name of this room.
     */
    public void setName(String roomName) {
        name = roomName;
    }

    /**
     * @return Some formatted information about the room.
     */
    @Override
    public String toString() {
        String output = String.format("Room name: %s\nItems:");
        for (Item item:itemList) {
            output = output + "\n" + item;
        }
        output = output + "\n" + getEntrances();
        return output;
    }
}
