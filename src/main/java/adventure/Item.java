package adventure;

public final class Item{
    private String name;
    private String description;
    private Room room;
    private long id;

    /* required public methods */

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public Room getContainingRoom(){
        //returns a reference to the room that contains the item
        return this.room;
    }

    public void setRoom(Room room){
        this.room = room;
    }

    // For printing in Adventure.java
    @Override
    public String toString(){
        return String.format("%s:\n%s\n", this.name, this.description);
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }
}