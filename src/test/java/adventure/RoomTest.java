package adventure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;


public class RoomTest{
    private Room testRoom;

    @Before
    public void setup(){
        testRoom = new Room();
    }

    /**
     * Tests a valid direction.
     */
    @Test
    public void testGetConnectedRoomValidInput() {
        System.out.println("Testing getConnectedRoom with northern room");
        Room north = new Room();
        testRoom.addToList(north);
        north.setID(0);
        testRoom.addEntrance("N", 0);
        Room test = testRoom.getConnectedRoom("N");
        assertEquals(north, test);
    }

    /**
     * Tests connection between two rooms.
     */
    @Test
    public void testGetConnectedRoomValidMultipleRooms() {
        System.out.println("Testing getConnectedRoom with northern room that connects back to original room");
        testRoom.setID(1);
        testRoom.addToList(testRoom);
        Room north = new Room();
        testRoom.addToList(north);
        north.addToList(testRoom);
        north.setID(0);
        testRoom.addEntrance("S", 0);
        Room back = testRoom.getConnectedRoom("S");
        back.addEntrance("N", 1);
        Room forth = back.getConnectedRoom("N");
        assertEquals(testRoom, forth);
    }

    /**
     * If it asserts equal, then the room wasn't
     * changed due to an invalid direction.
     */
    @Test
    public void testGetConnectedRoomInvalidDirection() {
        System.out.println("Testing getConnectedRoom with an invalid direction");
        testRoom.addEntrance("N", 2);
        testRoom.addToList(new Room());
        Room temp = testRoom.getConnectedRoom("E");
        assertTrue(temp == null);
    }

    /**
     * Room looping to itself
     */
    @Test
    public void testGetConnectedRoomLoopingToItself() {
        System.out.println("Testing getConnectedRoom with a room that loops to itself");
        testRoom.setID(0);
        testRoom.addToList(testRoom);
        testRoom.addEntrance("up", 0);
        testRoom.addEntrance("down", 1);
        Room room = testRoom;
        for (int i = 0; i < 5; i++) {
            room = room.getConnectedRoom("up");
        }
        assertEquals(room, testRoom);
    }
}
