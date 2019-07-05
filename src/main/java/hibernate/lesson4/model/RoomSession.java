package hibernate.lesson4.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomSession {

    static List<Room> bookedRooms = new ArrayList<>();



    public static void bookRoom(Room room) {

        if (room != null & !isBooked(room)) bookedRooms.add(room);


    }

    private static boolean isBooked(Room room) {

        for (Room bookedRoom : bookedRooms) {
            if (room.equals(bookedRoom)) return true;
        }

        return false;
    }

    public static void cancelReservation(Room room) {

        if (room != null && isBooked(room)) bookedRooms.remove(room);

    }




}
