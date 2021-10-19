package com.perez.christophe.mareu.repository;

import com.perez.christophe.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Christophe on 03/09/2021.
 */
public abstract class RoomGenerator {


    public static List<Room> ROOMS_LIST = Arrays.asList(
            new Room(1, "Salle 1", 0xff81D4FA),
            new Room(2, "Salle 2", 0xff80DEEA),
            new Room(3, "Salle 3", 0xff80CBC4),
            new Room(4, "Salle 4", 0xffA5D6A7),
            new Room(5, "Salle 5", 0xffC5E1A5),
            new Room(6, "Salle 6", 0xffE6EE9C),
            new Room(7, "Salle 7", 0xffFFF59D),
            new Room(8, "Salle 8", 0xffFFE082),
            new Room(9, "Salle 9", 0xffFFCC80),
            new Room(10, "Salle 10", 0xffFFAB91)
    );

    public static List<Room> generateListOfRoons() {
        return new ArrayList<>(ROOMS_LIST);
    }

    // Generate list String of room in a ArrayList
    public static List<String> ROOMS_LIST_STRING = Arrays.asList(
            "Salle 1",
            "Salle 2",
            "Salle 3",
            "Salle 4",
            "Salle 5",
            "Salle 6",
            "Salle 7",
            "Salle 8",
            "Salle 9",
            "Salle 10");

    // Generate list String [] of room in a board
    public static String[] ROOMS_LIST_STRING_TABLEAU = {
            "Salle 1",
            "Salle 2",
            "Salle 3",
            "Salle 4",
            "Salle 5",
            "Salle 6",
            "Salle 7",
            "Salle 8",
            "Salle 9",
            "Salle 10"
    };

    // Generate list String [] of room witch ROOM_LIST
    public static String[] generateListStringRoom() {
        String[] roomListStringTab = new String[ROOMS_LIST.size()];
        for (int i = 0; i < ROOMS_LIST.size(); i = i + 1) {
            String nameOfRoom = ROOMS_LIST.get(i).getNameOfRoom();
            roomListStringTab[i] = nameOfRoom;
        }
        return roomListStringTab;
    }

    // Generate HashMap <nameOfRoom , colorOfRoom> witch ROOM_LIST
    public static Map<String, Integer> generateHashMapRoom() {
        Map<String, Integer> roomHashMap = new LinkedHashMap<>();
        for (int i = 0; i < ROOMS_LIST.size(); i = i + 1) {
            Room room = ROOMS_LIST.get(i);
            roomHashMap.put(room.getNameOfRoom(), room.getColorOfRoom());
        }
        return roomHashMap;
    }
}
