package adventure.item;
import org.json.simple.JSONObject;

public class Weapon extends Item implements Tossable {

    /* Constructors */
    /**
     * Default constructor.
     */
    public Weapon() {
        super();
    }

    /**
     * Parses JSONObject into Weapon instance.
     * @param object JSONObject with all information
     *               about this Weapon instance.
     */
    public Weapon(JSONObject object) {
        // TODO
    }


    /* Overridden methods */
    /**
     * TODO
     * @return Toss message.
     */
    @Override
    public String toss() {
        return null;
    }
}
