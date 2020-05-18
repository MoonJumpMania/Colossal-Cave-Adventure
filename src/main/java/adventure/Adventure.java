package adventure;
import java.util.ArrayList;

public class Adventure{
    /* you will need to add some private member variables */
    private ArrayList<Room> roomList = new ArrayList<>();
    private ArrayList<Item> itemList = new ArrayList<>();

    private Room currentRoom;

    /* ======== Required public methods ========== */
        /* note,  you don't have to USE all of these
        methods but you do have to provide them.
        We will be using them to test your code */

    public ArrayList<Room> listAllRooms(){
        System.out.println("All rooms:");
        for (Room room : roomList) {
            System.out.println(room);
        }
        return roomList;
    }

    public ArrayList<Item> listAllItems(){
        System.out.println("All items:");
        for (Item item : itemList) {
            System.out.println(item);
        }
        return itemList;
    }

    public String getCurrentRoomDescription(){
        return currentRoom.getLongDescription();
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /* you may wish to add additional methods*/
    public void addRoom(Room room){
        this.roomList.add(room);
    }

    public void addItem(Item item){
        this.itemList.add(item);
    }
}