package adventure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Room's data.
 * Contains the information about this room instance.
 * @author Nasif Mauthoor | ID: 1083611
 */
public final class Room implements Serializable {
    private static final long serialVersionUID = 1477531730760186040L;

    /* Member variables */
    private final Adventure adventure;
    private String name;
    private String shortDescription;
    private String longDescription;
    private long id;
    private HashMap<String, Long> entranceMap;
    private ArrayList<Item> lootList;


    /* Constructors */
    /**
     * Default constructor.
     */
    public Room() {
        name = new String();
        shortDescription = new String();
        longDescription = new String();
        id = 0;
        entranceMap = new HashMap<>();
        lootList = new ArrayList<>();
        adventure = new Adventure();
    }

    /**
     * Takes the adventure and room json to construct the room.
     * @param adventureObj The adventure object.
     * @param roomJSON JSON member with information about this room.
     */
    public Room(Adventure adventureObj, JSONObject roomJSON) {
        name = (String) roomJSON.get("name");
        shortDescription = (String) roomJSON.get("short_description");
        longDescription = (String) roomJSON.get("long_description");
        id = (long) roomJSON.get("id");
        entranceMap = new HashMap<>();
        lootList = new ArrayList<>();
        adventure = adventureObj;

        JSONArray entranceJSON = (JSONArray) roomJSON.get("entrance");
        setEntranceMap(entranceJSON);

        JSONArray itemJSON = (JSONArray) roomJSON.get("loot");
        createLootList(itemJSON);
    }


    /* required public methods */
    /**
     * @return list of items in this instance
     */
    public ArrayList<Item> listItems(){
        return lootList;
    }


    /* Getters */
    /**
     * Returns the name of the room.
     * @return Name of this room.
     */
    public String getName(){
        return name;
    }

    public Adventure getAdventure() {
        return adventure;
    }

    /**
     * Returns the short description of this room.
     * @return The short description of this room.
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Returns the long description of this room.
     * @return The long description of this room.
     */
    public String getLongDescription(){
        return longDescription;
    }

    /**
     * Gets this room's list of items.
     * @return This room's item list.
     */
    public ArrayList<Item> getLootList() {
        return lootList;
    }

    /**
     * Get's the room connected from it's direction.
     * @param direction one of: "N", "S", "E", "W", "up", "down"
     * @return Room in given direction.
     */
    public Room getConnectedRoom(String direction) {
        if (entranceMap.get(direction) == null) {
            return null;
        }
        return adventure.getRoomFromID(entranceMap.get(direction));
    }

    /**
     * Returns each entrance room that is in the hashmap.
     * @return A string of ach room and their direction.
     */
    public String getEntranceMap() {
        // Allows change to string in Stream.forEach() method
        AtomicReference<String> output = new AtomicReference<>("");

        // Goes through each hashmap entry and adds their information to the output string.
        entranceMap.entrySet().stream().forEach(e -> {
            output.set(output + "\n"
                    + e.getKey() + ": "
                    + adventure.getRoomFromID(e.getValue()).getName());
        });

        return output.get();
    }

    /**
     * @return Getter for this room's ID.
     */
    public long getID() {
        return id;
    }


    /* Setters */
    /**
     * Sets the entrances of room.
     * @param entranceArray JSONArray of each entrance of this room
     */
    public void setEntranceMap(JSONArray entranceArray) {
        for (Object obj: entranceArray) {
            JSONObject entranceJSON = (JSONObject) obj ;
            String key = (String) entranceJSON.get("dir");
            long value = (long) entranceJSON.get("id");
            entranceMap.putIfAbsent(key, value);
        }
    }

    /**
     * Adds items from the JSON file into the array list.
     * @param itemArray JSONArray of each item in this room.
     */
    public void createLootList(JSONArray itemArray) {
        if (itemArray != null) {
            for (Object obj : itemArray) {
                JSONObject itemJSON = (JSONObject) obj;
                lootList.add(new Item(this, itemJSON));
            }
        }
    }

    /**
     * Set id of this room.
     * @param roomID id given to this room.
     */
    public void setID(long roomID) {
        id = roomID;
    }


    /*  */
    /**
     * Add item to this room's list of items.
     * @param item Item that will be removed from this room's list.
     */
    public void removeItem(Item item) {
        lootList.remove(item);
    }

    /**
     * Remove an item from this room's list of items.
     * @param item Item that will be added to this room's list.
     */
    public void addItem(Item item) {
        lootList.add(item);
    }

    /**
     * Adds an entrance to the hashmap.
     * @param dir Direction of the room from the current room.
     * @param roomID ID of the room in the given direction.
     */
    public void addEntrance(String dir, long roomID) {
        entranceMap.putIfAbsent(dir, roomID);
    }

    /**
     * Mutator of this room's name.
     * @param roomName Name of this room.
     */
    public void setName(String roomName) {
        name = roomName;
    }

    public String displayItems() {
        String output = new String();
        if (!lootList.isEmpty()) {
            output = output + "\nItems: ";
            for (Item item:lootList) {
                output = output + item.getName() + " ";
            }
            return output;
        }
        return new String();
    }

    /**
     * @param sd New short description.
     */
    public void setShortDescription(String sd) {
        shortDescription = sd;
    }

    /**
     * @param ld New long description.
     */
    public void setLongDescription(String ld) {
        longDescription = ld;
    }

    /**
     * @param roomID New ID.
     */
    public void setId(long roomID) {
        id = roomID;
    }

    /**
     * @param em New entrance hashmap.
     */
    public void setEntranceMap(HashMap<String, Long> em) {
        entranceMap = em;
    }

    /**
     * @param ll New loot ArrayList.
     */
    public void setLootList(ArrayList<Item> ll) {
        lootList = ll;
    }

    /**
     * Overridden toString() method of this class.
     * @return Some formatted information about the room.
     */
    @Override
    public String toString() {
        String output = String.format("You are in: %s", name);
        output = output + displayItems();
        output = output + "\n" + "Entrances:" + getEntranceMap();
        return output;
    }
}
