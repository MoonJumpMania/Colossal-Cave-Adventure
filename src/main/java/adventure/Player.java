package adventure;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Player's data.
 * Contains the player's inventory, save name and info about the player.
 * @author Nasif Mauthoor | ID: 1083611
 */
public final class Player implements Serializable {
    private static final long serialVersionUID = 3260259094862585L;

    /* Member variables */
    private String name;
    private String saveGameName;
    private ArrayList<Item> inventory;
    private Room currentRoom;


    /**
     * Default constructor.
     */
    public Player() {
        this("name");
    }

    /**
     * Constructor with name and initial room as input.
     * @param n Name of player.
     */
    public Player(String n) {
        name = n;
        inventory = new ArrayList<>();
        saveGameName = new String();
    }

    /**
     * Gets the player's name.
     * @return Player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's inventory.
     * @return Player's inventory.
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * Returns this player's current room.
     * @return Player's current room.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Sets the new current room.
     * @param room The next room.
     */
    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    /**
     * Sets the name of this player.
     * @param playerName Name of the player.
     */
    public void setName(String playerName) {
        name = playerName;
    }

    public void setInventory(ArrayList<Item> i) {
        inventory = i;
    }

    /**
     * Picks an item from the current room.
     * @param item Item to be picked up.
     */
    public void pickItem(Item item) {
        inventory.add(item);
        item.getContainingRoom().removeItem(item);
    }

    /**
     * Drops item into the current room.
     * @param item Item to be dropped.
     */
    public void dropItem(Item item) {
        inventory.remove(item);
        item.getContainingRoom().addItem(item);
    }

    public String getSaveGameName() {
        return saveGameName;
    }

    public void setSaveGameName(String s) {
        saveGameName = s;
    }

    /**
     * toString method for Player class.
     * @return Returns the player's name, current room's name and everything in it's inventory.
     */
    @Override
    public String toString() {
        String output =  String.format("Player name: %s\nCurrent room: %s\nInventory:",
                name, currentRoom.getName());
        if (inventory.size() == 0) {
            return output + "Empty.";
        }
        for (Item item:inventory) {
            output = output + "\n\t" + item.getName();
        }
        return output;
    }
}
