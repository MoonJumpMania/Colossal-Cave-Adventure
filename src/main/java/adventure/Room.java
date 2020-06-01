package adventure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public final class Room implements Serializable {
    private static final long serialVersionUID = 1477531730760186040L;

    /* you will need to add some private member variables */
    private Adventure adventure;
    private String name;
    private String shortDescription;
    private String longDescription;
    private long id;
    private HashMap<String, Long> entranceMap;
    private ArrayList<Item> lootList;
    private ArrayList<Item> adventureItemList;
    private ArrayList<Room> adventureRoomList;

    /**
     * Default constructor
     */
    public Room() {
        name = new String();
        shortDescription = new String();
        longDescription = new String();
        entranceMap = new HashMap<>();
        lootList = new ArrayList<>();
    }

    /**
     * @param roomList
     * @param roomJSON
     */
    public Room(ArrayList<Room> roomList, ArrayList<Item> itemList, JSONObject roomJSON) {
        name = (String) roomJSON.get("name");
        shortDescription = (String) roomJSON.get("short_description");
        longDescription = (String) roomJSON.get("long_description");
        id = (long) roomJSON.get("id");
        entranceMap = new HashMap<>();
        lootList = new ArrayList<>();
        adventureRoomList = roomList;
        adventureItemList = itemList;

        JSONArray entranceJSON = (JSONArray) roomJSON.get("entrance");
        setEntranceMap(entranceJSON);

        JSONArray itemJSON = (JSONArray) roomJSON.get("loot");
        setLootList(itemJSON);
    }

    /* required public methods */

    /**
     * Prints all items in room and returns the list of items.
     * @return list of items in this instance
     */
    public ArrayList<Item> listItems(){
        for (Item item: lootList) {
            System.out.println(item);
        }
        return lootList;
    }

    /**
     * Returns the name of the room.
     * @return Name of this room.
     */
    public String getName(){
        return name;
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
     * @param direction one of: "N", "S", "E", "W", "up", "down"
     * @return Room in given direction.
     */
    public Room getConnectedRoom(String direction) {
        return getRoomFromID(entranceMap.get(direction));
    }

    private Room getRoomFromID(long roomID) {
        if (adventureRoomList != null) {
            for (Room room:adventureRoomList) {
                if (room.getID() == roomID) {
                    return room;
                }
            }
        }
        return null;
    }

    /**
     * Sets the entrances of room.
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
     * Returns each entrance room that is in the hashmap.
     */
    public String getEntranceMap() {
        // Allows change in forEach
        AtomicReference<String> output = new AtomicReference<>("");
        // Goes through each hashmap entry and adds their information to the output string.
        entranceMap.entrySet().stream().forEach(e -> {
            output.set(output + "\n" + e.getKey() + ": " + getRoomFromID(e.getValue()).getName());
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
     * Adds items from the JSON file into the array list.
     */
    public void setLootList(JSONArray itemArray) {
        if (itemArray != null) {
            for (Object obj : itemArray) {
                JSONObject itemJSON = (JSONObject) obj;
                lootList.add(new Item(adventureItemList, this, itemJSON));
            }
        }
    }

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
        String output = String.format("Room name: %s", name);
        if (lootList != null) {
           output = output + "\nItems:";
            for (Item item : lootList) {
                output = output + "\n" + item.toString();
            }
        }
        output = output + "\n" + "Entrances:" + "\n" + getEntranceMap();
        return output;
    }
}
