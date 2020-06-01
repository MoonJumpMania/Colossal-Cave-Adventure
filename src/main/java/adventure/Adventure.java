package adventure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public final class Adventure implements Serializable {
    private static final long serialVersionUID = 7116032258125813447L;

    /* you will need to add some private member variables */
    private ArrayList<Room> roomList;
    private ArrayList<Item> itemList;
    private Player player1;

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
     * @param advJSON sets the adventure from the adventure json
     */
    public Adventure(JSONObject advJSON) {
        this();
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
     * @return prints and returns list of all rooms
     */
    public ArrayList<Room> listAllRooms() {
        for (Room room:roomList) {
            System.out.println(room);
        }
        return roomList;
    }

    /**
     * @return prints and returns list of all items
     */
    public ArrayList<Item> listAllItems() {
        for (Item item:itemList) {
            System.out.println(item);
        }
        return itemList;
    }

    /**
     * @return description of the current room
     */
    public String getCurrentRoomDescription() {
        return getCurrentRoom().getShortDescription();
    }

    /**
     * @return this adventure's current room
     */
    public Room getCurrentRoom() {
        return player1.getCurrentRoom();
    }

    /* you may wish to add additional methods*/

    /**
     * @param id wanted room's tag
     * @return room from list with given id
     */
    public Room getRoomFromID(long id) {
        if (roomList != null) {
            for (Room room : roomList) {
                if (id == room.getID()) {
                    return room;
                }
            }
        }
        return null;
    }

    /**
     * Searches for an item from a specified ID tag.
     * @param id The wanted item's ID.
     * @return Returns the item from the itemList with the specified ID.
     */
    public Item getItemFromID(long id) {
        if (itemList != null) {
            for (Item item : itemList) {
                if (id == item.getID()) {
                    return item;
                }
            }
        }
        return null;
    }

    /**
     * Moves the player to another room.
     * @param direction Direction of the next room.
     */
    public void movePlayer(String direction) {
         Room newRoom = getCurrentRoom().getConnectedRoom(direction);

         // Only moves if the room is valid
         if (newRoom != null) {
             player1.setCurrentRoom(newRoom);
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
     * @param itemName
     */
    public String takeItem(String itemName) {
        Item item = getItem(itemName);
        getCurrentRoom().getLootList().remove(item);
        player1.pickItem(item);
        return "You grabbed a " + itemName;
    }

    /**
     * Creates room array list from JSONArray.
     * @param roomArray JSONArray that contains the information on every room type.
     */
    public void setRoomList(JSONArray roomArray) {
        for (Object object:roomArray) {
            JSONObject roomJSON = (JSONObject) object;
            Room newRoom = new Room(roomList, itemList, roomJSON);
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
    public Item getItem(String itemName) {
        for (Item item:getCurrentRoom().getLootList()) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    // Looks at the contents of the current room
    private String lookAtCurrentRoom() {
        return getCurrentRoom().getLongDescription() + "\n" + getCurrentRoom().toString();
    }

    // Reads the toString method of a specified item
    private String lookAtItem(String itemName) {
        Item item = getItem(itemName);
        if (item != null) {
            return item.toString();
        } else {
            return "Item not found.";
        }
    }

    @Override
    public String toString() {
        return null;
    }

    public Player getPlayer() {
        return player1;
    }
}
