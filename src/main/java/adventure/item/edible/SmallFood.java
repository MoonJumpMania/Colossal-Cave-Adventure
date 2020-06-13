package adventure.item.edible;
import adventure.item.Tossable;
import org.json.simple.JSONObject;

public class SmallFood extends Food implements Tossable {

    /* Constructors */
    /**
     * Default constructor.
     */
    public SmallFood() {
        super();
    }

    /**
     * Parses JSONObject into SmallFood instance.
     * @param object JSONObject with all information
     *               about this SmallFood instance.
     */
    public SmallFood(JSONObject object) {
        // TODO: 6/13/2020
    }


    /* Overridden methods */
    /**
     * TODO
     * @return Toss message.
     */
    @Override
    public String toss() {
        return null; // TODO
    }
}
