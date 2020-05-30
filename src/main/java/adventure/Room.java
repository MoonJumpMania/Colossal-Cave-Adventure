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

    /**
     * Default constructor
     */
    public Room() {
        adventure = new Adventure();
        name = "";
        shortDescription = "";
        longDescription = "";
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
        this.setEntrances((JSONArray) roomJSON.get("entrance"));
    }

    /* required public methods */

    /**
     * Prints all items in room
     * @return list of items in this instance
     */
    public ArrayList<Item> listItems(){
        for (Item item:itemList) {
            System.out.println(item);
        }
        return itemList;
    }

    /**
     * @return name of instance
     */
    public String getName(){
        return name;
    }

    /**
     * @return short description of instance
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * @return long description of instance
     */
    public String getLongDescription(){
        return longDescription;
    }

    /**
     * @param direction one of: "N", "S", "E", "W", "up", "down"
     * @return Room in given direction
     */
    public Room getConnectedRoom(String direction) {
        return entrances.get(direction);
    }

    /* you may wish to add some helper methods*/
    private void setEntrances(JSONArray array) {
        for (Object obj:array) {
            JSONObject entranceJSON = (JSONObject) obj;
            entrances.putIfAbsent((String) entranceJSON.get("dir"),
                    adventure.getRoomFromID((Long) entranceJSON.get("id")));
        }
    }

    /**
     * Returns each entrance room that is in the hashmap.
     */
    public String entrances() {
        AtomicReference<String> output = new AtomicReference<>("");
        // Goes through each hashmap entry and adds their information to the output string.
        entrances.entrySet().stream().forEach(e -> {
            output.set(output + e.getKey() + ": " + e.getValue().getName());
        });
        return output.get();
    }

    /**
     * @return Getter for this room's ID.
     */
    public long getID() {
        return id;
    }

    /**
     * @param item Item that will be removed from this room's list.
     */
    public void removeItem(Item item) {
        itemList.remove(item);
    }

    /**
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
        return output;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }
}
