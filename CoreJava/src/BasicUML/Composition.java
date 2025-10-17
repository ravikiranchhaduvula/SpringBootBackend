package BasicUML;

import java.util.ArrayList;
import java.util.List;

class Room {
    private String name;

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class House {
    private List<Room> rooms;

    public House() {
        rooms = new ArrayList<>();
        rooms.add(new Room("Living Room"));
        rooms.add(new Room("Kitchen"));
    }

    public void showRooms() {
        for(Room room:rooms) {
            System.out.println(room.getName());
        }
    }
}

public class Composition {
    public static void main(String[] args) {
        House house = new House();
        //If house is destroyed rooms also gets destroyed
        house.showRooms();
    }
}
