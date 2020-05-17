package adventure;

public class Item{
    /* you will need to add some private member variables */
    private String name;
    private String description;
    private Room room;

    /* required public methods */

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongDescription(){
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Room getContainingRoom(){
        //returns a reference to the room that contains the item
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString(){
        return String.format("Name: %s\nDescription: %s\n",
                this.name,
                this.description);
    }

    /* you may wish to add some helper methods*/
}