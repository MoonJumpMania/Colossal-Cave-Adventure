package adventure.item;
import adventure.Room;
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
     * @param room The room this spell resides in.
     * @param object JSONObject with all information
     *               about this Spell instance.
     */
    public Spell(Room room, JSONObject object) {
        super(room, object);
    }


    /* Overridden methods */

    /**
     * Tells the user that they read this spell.
     * @return Read message.
     */
    @Override
    public String read() {
        return "You read "
                + super.getName()
                + ". You feel enlightened.";
    }
}
