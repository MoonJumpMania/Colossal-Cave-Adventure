package adventure;
import java.util.ArrayList;

public final class Room{
    /* you will need to add some private member variables */
    private ArrayList<Item> itemList = new ArrayList<>();
    private Adventure adventure;

    // Neighbors ID
    private long north;
    private long south;
    private long east ;
    private long west ;

    private String name;
    private String longDescription;
    private String shortDescription;

    private long id;

    /* required public methods */

    public ArrayList<Item> itemList(){
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

    public void setLongDescription(String ld){
        this.longDescription = ld;
    }

    public void setShortDescription(String sd){
        this.shortDescription = sd;
    }

    public void setName(String n){
        this.name = n;
    }

    public void setNorth(long n){
        this.north = n;
    }

    public void setSouth(long s){
        this.south = s;
    }

    public void setEast(long e){
        this.east = e;
    }

    public void setWest(long w){
        this.west = w;
    }

    public void setId(long i){
        this.id = i;
    }

    public Room getConnectedRoom(String direction){
        Room connectedRoom = null;
        switch (direction.charAt(0)){
            case 'n':
                connectedRoom = this.adventure.getRoomFromID(this.north);
                break;
            case 's':
                connectedRoom = this.adventure.getRoomFromID(this.south);
                break;
            case 'e':
                connectedRoom = this.adventure.getRoomFromID(this.east);
                break;
            case 'w':
                connectedRoom = this.adventure.getRoomFromID(this.west);
                break;
            default:
                connectedRoom = null;
                break;
        }

        return connectedRoom;
    }

    // Returns an item with a designated name
    public Item getItemFromID(long i){
        for (Item item : this.itemList){
            if (item.getId() == i){
                return item;
            }
        }
        return null;
    }

    public void setEntrance(String direction, long i){
        char dir = direction.toLowerCase().charAt(0);
        if (dir == 'n'){
            this.north = i;
        }else {
            this.north = 0;
        }
        if (dir == 's'){
            this.south = i;
        }else {
            this.south = 0;
        }
        if (dir == 'e'){
            this.east = i;
        }else {
            this.east = 0;
        }
        if (dir == 'w'){
            this.west = i;
        }else {
            this.west = 0;
        }
    }

    public void setAdventure(Adventure a){
        this.adventure = a;
    }

    public void addItem(Item item){
        this.itemList().add(item);
    }

    // toString for listing methods in Adventure.java
    @Override
    public String toString(){
        return String.format("%s:\n%s\n", this.name, this.longDescription);
    }

    public void printItems(){
        if (itemList.size() == 0){
            return;
        }

        System.out.println("Items:");
        for (Item item : itemList){
            System.out.println(item.getName());
        }
    }

    public void printNeighbors(){
        if (this.north != 0){
            System.out.println("There is an entrance north.");
        }
        if (this.south != 0){
            System.out.println("There is an entrance south.");
        }
        if (this.east != 0){
            System.out.println("There is an entrance east.");
        }
        if (this.west != 0){
            System.out.println("There is an entrance west.");
        }
    }
}
