package adventure.item.wearable;
import adventure.item.Item;
import org.json.simple.JSONObject;

public abstract class Clothing extends Item implements Wearable {

    /* Constructors */
    /**
     * Default constructor.
     */
    public Clothing() {
        super();
    }

    /**
     * Parses JSONObject into Clothing instance.
     * @param object JSONObject with all information
     *               about this Clothing instance.
     */
    public Clothing(JSONObject object) {
        // TODO: 6/13/2020
    }


    /* Overridden methods */
    /**
     * TODO
     * @return Wear message.
     */
    @Override
    public String wear() {
        return null;
    }
}
