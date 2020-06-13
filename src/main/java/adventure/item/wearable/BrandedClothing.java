package adventure.item.wearable;
import adventure.Room;
import adventure.item.Readable;
import org.json.simple.JSONObject;

public class BrandedClothing extends Clothing implements Readable {

    /* Constructors */

    /**
     * Default constructor.
     */
    public BrandedClothing() {
        super();
    }

    /**
     * Parses JSONObject into BrandedClothing instance.
     * @param room The room these branded clothing reside in.
     * @param object JSONObject with all information
     *               about this BrandedClothing instance.
     */
    public BrandedClothing(Room room, JSONObject object) {
        super(room, object);
    }


    /* Overridden methods */

    /**
     * Tells the user that they read these branded clothing.
     * @return Read message.
     */
    @Override
    public String read() {
        return "You read "
                + super.getName()
                + "'s brand name.";
    }
}
