package adventure.item;
import adventure.Room;
import org.json.simple.JSONObject;
import java.io.Serializable;

/**
 * Item class.
 * @author Nasif Mauthoor | ID: 1083611
 */
public class Item implements Serializable {
    private static final long serialVersionUID = 465506458325891352L;

    /* Member variables */

    private String name;
    private String description;
    private Room containingRoom;
    private long id;

    /**
     * Default constructor
     */
    public Item() {
        name = new String();
        description = new String();
        containingRoom = new Room();
        id = 0;
    }

    /**
     * Constructor for item templates
     * @param itemJSON JSON subclass of Adventure.
     */
    public Item(JSONObject itemJSON) {
        name = (String) itemJSON.get("name");
        description = (String) itemJSON.get("desc");
        id = (long) itemJSON.get("id");
    }

    /**
     * Constructor for items that belong to a room.
     * @param roomObj The room containing this item.
     * @param itemJSON JSONObject that contains the id of this item.
     */
    public Item(Room roomObj, JSONObject itemJSON) {
        containingRoom = roomObj;
        id = (long) itemJSON.get("id");
        setItemFromTemplate();
    }

    /**
     *
     */
    public void setItemFromTemplate() {
        Item template = containingRoom.getAdventure().getItemFromID(id);
        name = template.getName();
        description = template.getLongDescription();
    }

    /* required public methods */

    /**
     * @return Name of this item.
     */
    public String getName(){
        return name;
    }

    /**
     * @return The description of this item.
     */
    public String getLongDescription(){
        return description;
    }

    /**
     * @return Room containing this item.
     */
    public Room getContainingRoom() {
        //returns a reference to the room that contains the item
        return containingRoom;
    }

    /**
     * @return ID of this item.
     */
    public long getID() {
        return id;
    }

    /**
     * Mutator for name.
     * @param n New name.
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Mutator for description.
     * @param d New description.
     */
    public void setDescription(String d) {
        description = d;
    }

    /**
     * Mutator for containingRoom.
     * @param cr New containingRoom.
     */
    public void setContainingRoom(Room cr) {
        containingRoom = cr;
    }

    /**
     * Mutator for id.
     * @param itemID New ID.
     */
    public void setId(long itemID) {
        id = itemID;
    }

    /* you may wish to add some helper methods*/

    /**
     * @return String with information on the instance
     */
    @Override
    public String toString() {
        return String.format("Item Name: %s\nDescription: %s",
                name, description);
    }
}
