package adventure.item.edible;
import adventure.Room;
import adventure.item.Item;
import org.json.simple.JSONObject;

public class Food extends Item implements Edible {

    /* Constructors */

    /**
     * Default constructor.
     */
    public Food() {
        super();
    }

    /**
     * Parses JSONObject into Food instance.
     * @param room The room this food resides in.
     * @param object JSONObject with all information
     *               about this Food instance.
     */
    public Food(Room room, JSONObject object) {
        super(room, object);
    }


    /* Overridden methods */

    /**
     * Tells the user that they ate this food.
     * @return Eat message.
     */
    @Override
    public String eat() {
        return "You ate "
                + super.getName()
                + ". You are feeling refreshed";
    }
}
