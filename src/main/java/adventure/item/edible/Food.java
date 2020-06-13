package adventure.item.edible;
import adventure.item.Item;
import org.json.simple.JSONObject;

public abstract class Food extends Item implements Edible {

    /* Constructors */
    /**
     * Default constructor.
     */
    public Food() {
        super();
    }

    /**
     * Parses JSONObject into Food instance.
     * @param object JSONObject with all information
     *               about this Food instance.
     */
    public Food(JSONObject object) {
        // TODO: 6/13/2020
    }


    /* Overridden methods */
    /**
     * TODO
     * @return Eat message.
     */
    @Override
    public String eat() {
        return null; // TODO
    }
}
