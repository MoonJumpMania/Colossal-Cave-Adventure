package adventure;
import java.util.ArrayList;

public final class Room{
    /* you will need to add some private member variables */
    private ArrayList<Item> itemList = new ArrayList<>();
    private Adventure adventure;

    // Neighbors ID
    private long north = 0;
    private long south = 0;
    private long east = 0;
    private long west = 0;

    private String name;
    private String longDescription;
    private String shortDescription;

    private long id;

    /* required public methods */

    public ArrayList<Item> listItems(){
        //lists all the items in the room
        return this.itemList;
    }

    // Getters and setters
    public String getName(){
        return this.name;
    }

    public String getLongDescription(){
        return this.longDescription;
    }

    public long getId(){
        return this.id;
    }

    public String getShortDescription(){
        return shortDescription;
    }

    public void setLongDescription(String longDescription){
        this.longDescription = longDescription;
    }

    public void setShortDescription(String shortDescription){
        this.shortDescription = shortDescription;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setNorth(long north){
        this.north = north;
    }

    public void setSouth(long south){
        this.south = south;
    }

    public void setEast(long east){
        this.east = east;
    }

    public void setWest(long west){
        this.west = west;
    }

    public void setId(long id){
        this.id = id;
    }

    public Room getConnectedRoom(String direction){
        Room connectedRoom = null;

        switch (direction.charAt(0)){
            case 'n':
                connectedRoom = adventure.getRoomFromID(this.north);
                break;
            case 's':
                connectedRoom = adventure.getRoomFromID(this.south);
                break;
            case 'e':
                connectedRoom = adventure.getRoomFromID(this.east);
                break;
            case 'w':
                connectedRoom = adventure.getRoomFromID(this.west);
                break;
        }

        return connectedRoom;
    }

    // Returns an item with a designated name
    public Item getItemFromName(String name){
        for (Item item : itemList){
            if (item.getName() == name){
                return item;
            }
        }
        return null;
    }

    public void setEntrance(String direction, long room){
        switch (direction.toLowerCase().charAt(0)){
            case 'n':
                this.setNorth(room);
            case 's':
                this.setSouth(room);
            case 'e':
                this.setEast(room);
            case 'w':
                this.setWest(room);
        }
    }

    // toString for listing methods in Adventure.java
    @Override
    public String toString(){
        return String.format("%s:\n%s\n", this.name, this.longDescription);
    }

    public void setAdventure(Adventure adventure){
        this.adventure = adventure;
    }
}