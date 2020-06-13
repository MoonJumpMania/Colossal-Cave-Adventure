package adventure.item.wearable;
import adventure.Room;
import adventure.item.Item;
import org.json.simple.JSONObject;

public class Clothing extends Item implements Wearable {

    /* Constructors */

    /**
     * Default constructor.
     */
    public Clothing() {
        super();
    }

    /**
     * Parses JSONObject into Clothing instance.
     * @param room The room that these clothing reside in.
     * @param object JSONObject with all information
     *               about this Clothing instance.
     */
    public Clothing(Room room, JSONObject object) {
        super(room, object);
    }


    /* Overridden methods */

    /**
     * Tells the user that they wore the clothing.
     * @return Wear message.
     */
    @Override
    public String wear() {
        return "You wore the "
                + super.getName()
                + ". It feels itchy.";
    }
}
