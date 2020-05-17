package adventure;

import java.util.*;

public class Room{
    /* you will need to add some private member variables */
    private List<Item> itemList = new ArrayList<Item>();
    private List<Room> connectedRooms = new ArrayList<Room>();

    private String name;
    private String description;

    /* required public methods */

    public ArrayList<Item> listItems(){
        //lists all the items in the room

    }

    public String getName(){

    }

    public String getLongDescription(){

    }

    public Room getConnectedRoom(String direction) {

    }

    /* you may wish to add some helper methods*/
}