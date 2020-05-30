package adventure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;

/**
 * @author Nasif Mauthoor
 * @version A2
 */
public class Adventure {
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
        setRoomList((JSONArray) advJSON.get("room"));
        setItemList((JSONArray) advJSON.get("item"));
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
        for (Room room:roomList) {
            if (id == room.getID()) {
                return room;
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
        for (Item item:itemList) {
            if (id == item.getID()) {
                return item;
            }
        }
        return null;
    }

    public void movePlayer(String direction) {
         Room newRoom = getCurrentRoom().getConnectedRoom(direction);

         // Only moves if the room is valid
         if (newRoom != null) {
             player1.setCurrentRoom(newRoom);
         }
    }

    public String lookAt(String itemName) {
        if (itemName != null) {
            return lookAtItem(itemName);
        } else {
            return lookAround();
        }
    }

    public String checkInventory() {
        if (player1.getInventory().size() == 0) {
            return "Player1's inventory is empty.";
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
    public void takeItem(String itemName) {
        Item item = getItem(itemName);
        player1.getCurrentRoom().getItemList().remove(item);
        player1.pickItem(item);
    }

    /* Private functions  */

    // Gets an item by specifying its name
    private Item getItem(String itemName) {
        for (Item item:getCurrentRoom().getItemList()) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    private String lookAround() {
        return getCurrentRoom().toString();
    }

    private String lookAtItem(String itemName) {
        Item item = getItem(itemName);
        if (item != null) {
            return item.toString();
        } else {
            return "Item not found.";
        }
    }

    private void setRoomList(JSONArray roomObjs) {
        for (Object object:roomObjs) {
            JSONObject roomJSON = (JSONObject) object;
            Room newRoom = new Room(this, roomJSON);
            roomList.add(newRoom);
            if (roomJSON.get("start").equals("true")) {
                player1.setCurrentRoom(newRoom);
            }
        }
    }

    private void setItemList(JSONArray itemJSONArray) {
        for (Object object:itemJSONArray) {
            JSONObject itemJSON = (JSONObject) object;
            itemList.add(new Item(this, null, itemJSON));
        }
    }
}
