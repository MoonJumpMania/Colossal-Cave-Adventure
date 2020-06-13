package adventure.item;
import adventure.Room;
import org.json.simple.JSONObject;

public class Weapon extends Item implements Tossable {

    /* Member variables */

    private long damage;


    /* Constructors */

    /**
     * Default constructor.
     */
    public Weapon() {
        super();
    }

    /**
     * Parses JSONObject into Weapon instance.
     * @param room The room this weapon resides in.
     * @param object JSONObject with all information
     *               about this Weapon instance.
     */
    public Weapon(Room room, JSONObject object) {
        super(room, object);

        damage = (long) object.get("damage");
    }


    /* Getters */

    /**
     * Accessor for this weapon's damage.
     * @return The damage of this weapon.
     */
    public long getDamage() {
        return damage;
    }


    /* Setters */

    /**
     * Mutator for the damage variable.
     * @param dam The new damage stat.
     */
    public void setDamage(long dam) {
        damage = dam;
    }


    /* Overridden methods */

    /**
     * Returns a message telling the player
     * that they picked up this weapon.
     * @return Toss message.
     */
    @Override
    public String toss() {
        return "You threw a "
                + super.getName()
                + ". That was a lot of damage.";
    }
}
