package adventure.item.wearable;
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
     * @param object JSONObject with all information
     *               about this BrandedClothing instance.
     */
    public BrandedClothing(JSONObject object) {
        // TODO: 6/13/2020
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
