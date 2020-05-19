package adventure;
import java.util.ArrayList;

public final class Adventure{
    /* you will need to add some private member variables */
    private ArrayList<Room> roomList = new ArrayList<>();
    private ArrayList<Item> itemList = new ArrayList<>();

    private Room currentRoom;

    public ArrayList<Room> listAllRooms(){
        System.out.println("All rooms:");
        for (Room room : roomList){
            System.out.println(room);
        }
        return roomList;
    }

    public ArrayList<Item> listAllItems(){
        System.out.println("All items:");
        for (Item item : itemList){
            System.out.println(item);
        }
        return itemList;
    }

    public String getCurrentRoomDescription(){
        return currentRoom.getLongDescription();
    }

    public void setCurrentRoom(Room currentRoom){
        this.currentRoom = currentRoom;
    }

    public Room getCurrentRoom(){
        return this.currentRoom;
    }

    public void addRoom(Room room){
        this.roomList.add(room);
    }

    public void addItem(Item item){
        this.itemList.add(item);
    }

    // Returns the chosen room
    public Room getRoomFromID(long id){
        for (Room room : roomList){
            if (room.getId() == id)
            {
                return room;
            }
        }
        return null;
    }

    public ArrayList<Item> getItemList(){
        return this.itemList;
    }
}