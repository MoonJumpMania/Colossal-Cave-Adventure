package adventure;
import org.json.simple.JSONObject;

public class Item {
    private Adventure currentAdventure;
    private String name;
    private String description;
    private Room containingRoom;
    private long id;

    /**
     * Default constructor
     */
    public Item() {
        currentAdventure = new Adventure();
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
     * @param advObj adventure instance
     * @param roomObj room containing this instance
     * @param itemJSON json with information for this instance
     */
    public Item(Adventure advObj, Room roomObj, JSONObject itemJSON) {
        this(advObj.getItemFromID((Long) itemJSON.get("id")));
        currentAdventure = advObj;
        containingRoom = roomObj;
    }

    /**
     * @param item usually the template held in the adventure item list
     */
    private Item(Item item) {
        name = item.name;
        description = item.description;
        id = item.id;
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

    /* you may wish to add some helper methods*/

    /**
     * @return String with information on the instance
     */
    @Override
    public String toString() {
        return String.format("Item Name: %s\n" +
                "Description: %s\n" +
                "Containing room: %s\n" +
                "ID: %s",
                name, description, containingRoom.getName(), id);
    }
}
