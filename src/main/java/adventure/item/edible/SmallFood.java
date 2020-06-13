package adventure.item.edible;
import adventure.Room;
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
     * @param room The room this SmallFood item resides in.
     * @param object JSONObject with all information
     *               about this SmallFood instance.
     */
    public SmallFood(Room room, JSONObject object) {
        super(room, object);
    }


    /* Overridden methods */

    /**
     * Tells the user that they tossed this small food.
     * @return Toss message.
     */
    @Override
    public String toss() {
        return "You tossed "
                + super.getName()
                + " on the ground.";
    }
}
