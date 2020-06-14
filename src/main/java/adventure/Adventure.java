package adventure;
import adventure.item.Item;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Adventure data.
 * All information of rooms and items contained within this adventure.
 * Contains the player's data as well.
 * @author Nasif Mauthoor | ID: 1083611
 */

public final class Adventure implements Serializable {
    private static final long serialVersionUID = 7116032258125813447L;

    /* you will need to add some private member variables */
    private final ArrayList<Room> roomList;
    private final ArrayList<Item> itemList;
    private final Player player1;

    /* ======== Required public methods ========== */
        /* note,  you don't have to USE all of these
        methods but you do have to provide them.
        We will be using them to test your code */

    /**
     * Default constructor
     */
    public Adventure() {
        roomList = new ArrayList<>();
        itemList = new ArrayList<>();
        player1 = new Player();
    }

    /**
     * Constructor turn JSONObject into adventure.
     * @param name Name of the player.
     * @param advJSON JSONObject with the contents of this adventure.
     */
    public Adventure(String name, JSONObject advJSON) {
        roomList = new ArrayList<>();
        itemList = new ArrayList<>();
        player1 = new Player(name);

        setItemList((JSONArray) advJSON.get("item"));
        setRoomList((JSONArray) advJSON.get("room"));
    }

    /**
     * Sets new room for the player.
     * @param room Room the player is in.
     */
    public void setCurrentRoom(Room room) {
        player1.setCurrentRoom(room);
    }

    /**
     * Accessor to this adventure's list of rooms.
     * @return A list of all rooms in this adventure.
     */
    public ArrayList<Room> listAllRooms() {
        return roomList;
    }

    /**
     * @return returns list of all items
     */
    public ArrayList<Item> listAllItems() {
        return itemList;
    }

    /**
     * Returns this adventure's current room.
     * @return The current room.
     */
    public Room getCurrentRoom() {
        return player1.getCurrentRoom();
    }

    public String getCurrentRoomDescription() {
        return getCurrentRoom().getLongDescription();
    }

    /**
     * Accessor to the player's name.
     * @return The player's name.
     */
    public String getPlayerName() {
        return player1.getName();
    }

    /**
     * Gets an item from a given ID.
     * @param itemID ID of the wanted item.
     * @return The wanted item.
     */
    public Item getItemFromID(long itemID) {
        if (itemList != null) {
            for (Item item:itemList) {
                if (item.getID() == itemID) {
                    return item;
                }
            }
        }
        return null;
    }

    /* you may wish to add additional methods*/

    /**
     * Moves the player to another room.
     * @param direction Direction of the next room.
     */
    public void movePlayer(String direction) {
        Room newRoom = getCurrentRoom().getConnectedRoom(direction);
        // Only moves if the room is valid
        if (newRoom != null) {
            setCurrentRoom(newRoom);
        }
    }

    /**
     * Look at a specified item or looks around the room.
     * @param itemName Name of the item to look at or null.
     * @return Description of specified item or description of the room.
     */
    public String lookAt(String itemName) {
        if (itemName != null) {
            return lookAtItem(itemName);
        } else {
            return lookAtCurrentRoom();
        }
    }

    /**
     * Looks at the inventory of the player.
     * @return A description of the player's inventory.
     */
    public String checkInventory() {
        if (player1.getInventory().size() == 0) {
            return player1.getName() + "'s inventory is empty.";
        }

        String output = "Player1's Inventory:";
        for (Item item:player1.getInventory()) {
            output = output + "\n" + item.getName();
        }
        return output;
    }

    /**
     * Takes an item from a room and places it into the player's inventory.
     * @param itemName Name of the item to be taken.
     * @return Response message to picking up an existing item or when an item doesn't exist.
     */
    public String takeItem(String itemName) {
        Item item = getItemFromName(itemName);
        if (item != null) {
            player1.pickItem(item);
            return "You grabbed a " + itemName;
        } else {
            return "Item not found.";
        }
    }

    /**
     * Creates room array list from JSONArray.
     * @param roomArray JSONArray that contains the information on every room type.
     */
    public void setRoomList(JSONArray roomArray) {
        for (Object object:roomArray) {
            JSONObject roomJSON = (JSONObject) object;
            Room newRoom = new Room(this, roomJSON);
            roomList.add(newRoom);
            if (roomJSON.get("start") != null) {
                player1.setCurrentRoom(newRoom);
            }
        }
    }

    /**
     * Creates item array list from JSONArray.
     * @param itemJSONArray JSONArray with the information on every item.
     */
    public void setItemList(JSONArray itemJSONArray) {
        for (Object object:itemJSONArray) {
            JSONObject itemJSON = (JSONObject) object;
            itemList.add(new Item(itemJSON));
        }
    }

    public void setPlayerName(String playerName) {
        player1.setName(playerName);
    }

    /**
     * Gets item by searching its name.
     * @param itemName Name of the item.
     * @return The item with the same name.
     */
    public Item getItemFromName(String itemName) {
        for (Item item:getCurrentRoom().getLootList()) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Takes an ID and finds the room with that ID.
     * @param roomID ID of the wanted room.
     * @return Room with given ID.
     */
    public Room getRoomFromID(long roomID) {
        if (roomList != null) {
            for (Room room:roomList) {
                if (room.getID() == roomID) {
                    return room;
                }
            }
        }
        return null;
    }

    // Looks at the contents of the current room
    private String lookAtCurrentRoom() {
        return getCurrentRoom().getShortDescription() + "\n" + getCurrentRoom().toString();
    }

    // Reads the toString method of a specified item
    private String lookAtItem(String itemName) {
        Item item = getItemFromName(itemName);
        if (item != null) {
            return item.toString();
        } else {
            return "Item not found.";
        }
    }

    public String eatItem(String noun) {
        return player1.eatItem(noun);
    }

    /**
     * toString() method of this class to output information about adventure.
     * @return Player and current room information.
     */
    @Override
    public String toString() {
        return String.format("Player: %s\nCurrent Room: %s",
                player1.getName(),
                getCurrentRoom().getName());
    }

    public String getSaveName() {
        return player1.getSaveGameName();
    }

}
