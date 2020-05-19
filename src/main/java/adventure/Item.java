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

    public void setName(String n){
        this.name = n;
    }

    public void setDescription(String d){
        this.description = d;
    }

    public String getDescription(){
        return this.description;
    }

    public Room getContainingRoom(){
        //returns a reference to the room that contains the item
        return this.room;
    }

    public void setRoom(Room r){
        this.room = r;
    }

    public long getId(){
        return id;
    }

    public void setId(long i){
        this.id = i;
    }

    // For printing in Adventure.java
    @Override
    public String toString(){
        return String.format("%s:\n%s\n", this.name, this.description);
    }
}
