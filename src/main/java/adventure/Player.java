package adventure;
import adventure.item.Item;
import adventure.item.Readable;
import adventure.item.Tossable;
import adventure.item.edible.Edible;
import adventure.item.wearable.Wearable;

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
    private Wearable currentlyWearing;


    /* Constructors */

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


    /* Getters */

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
     * Returns this player's save game name.
     * @return The save name.
     */
    public String getSaveGameName() {
        return saveGameName;
    }


    /* Setters */

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

    /**
     * Sets inventory. (For Junit testing purposes)
     * @param i The new ArrayList.
     */
    public void setInventory(ArrayList<Item> i) {
        inventory = i;
    }

    /**
     * Sets the save name. (For Junit testing purposes)
     * @param s New save name.
     */
    public void setSaveGameName(String s) {
        saveGameName = s;
    }


    /* Command methods */

    /**
     * Picks an item from the current room.
     * @param item Item to be picked up.
     */
    public void pickItem(Item item) {
        inventory.add(item);
        item.getContainingRoom().removeItem(item);
    }

    /**
     * Eats an item.
     * Item gets deleted.
     * @param itemName The name of the edible item.
     * @return A message of the player eating the edible item.
     */
    public String eatItem(String itemName) {
        Item item = findItemInInventory(itemName);

        if (item instanceof Edible) {
            inventory.remove(item);
            return ((Edible) item).eat();
        } else if (item == null) {
            return "Item does not exist.";
        }

        return "This item is not tossable.";
    }

    /**
     * Drops tossable item into the current room.
     * @param itemName Name of item to be dropped.
     * @return A message telling the player that they tossed the item.
     */
    public String tossItem(String itemName) {
        Item item = findItemInInventory(itemName);

        if (item instanceof Tossable) {
            currentRoom.addItem(item);
            inventory.remove(item);
            return ((Tossable) item).toss();
        } else if (item == null) {
            return "Item does not exist.";
        }

        return "This item is not tossable.";
    }

    /**
     * The player wears the chosen wearable item.
     * @param itemName Name of item to be worn.
     * @return A message telling the user that they wore the item.
     */
    public String wearItem(String itemName) {
        Item item = findItemInInventory(itemName);

        if (item instanceof Wearable) {
            currentlyWearing = (Wearable) item;
            return ((Wearable) item).wear();
        } else if (item == null) {
            return "Item does not exist.";
        }

        return "This item is not tossable.";
    }

    /**
     * The player reads the chosen item.
     * @param itemName Name of item to read.
     * @return The words written on the item.
     */
    public String readItem(String itemName) {
        Item item = findItemInInventory(itemName);

        if (item instanceof Readable) {
            return ((Readable) item).read();
        } else if (item == null) {
            return "Item does not exist.";
        }

        return "This item is not tossable.";
    }


    /* toString() */

    /**
     * toString method for Player class.
     * @return The player's name, current room's name and inventory.
     */
    @Override
    public String toString() {
        String output =  String.format("Player name: %s"
                        + "\nCurrent room: %s\nInventory:",
                name, currentRoom.getName());
        if (inventory.size() == 0) {
            return output + "Empty.";
        }
        for (Item item:inventory) {
            output = output + "\n\t" + item.getName();
        }
        return output;
    }


    /* Private helper methods */

    private Item findItemInInventory(String itemName) {
        for (Item item:inventory) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }
}
