package adventure.item;
import org.json.simple.JSONObject;

public class Spell extends Item implements Readable {

    /* Constructors */
    /**
     * Default constructor.
     */
    public Spell() {
        super();
    }

    /**
     * Parses JSONObject into Spell instance.
     * @param object JSONObject with all information
     *               about this Spell instance.
     */
    public Spell(JSONObject object) {
        // TODO
    }


    /* Overridden methods */
    /**
     * TODO
     * @return Read message.
     */
    @Override
    public String read() {
        return null;
    }
}
