package adventure;

import java.util.*;

public class Room{
    /* you will need to add some private member variables */
    private ArrayList<Item> itemList = new ArrayList<Item>();

    private Room north;
    private Room south;
    private Room east;
    private Room west;

    private String name;
    private String description;

    private int id;

    /* required public methods */

    public ArrayList<Item> listItems(){
        //lists all the items in the room
        return this.itemList;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongDescription(){
        return this.description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room getConnectedRoom(String direction) {
        Room connectedRoom;

        switch (direction.charAt(0)){
            case 'n':
                connectedRoom = this.north;
                break;
            case 's':
                connectedRoom = this.south;
                break;
            case 'e':
                connectedRoom = this.east;
                break;
            case 'w':
                connectedRoom = this.west;
                break;
            default:
                connectedRoom = null;
                break;
        }

        return connectedRoom;
    }

    /* you may wish to add some helper methods*/
    @Override
    public String toString(){
        return String.format("%s:\n%s\n", this.name, this.description);
    }
}