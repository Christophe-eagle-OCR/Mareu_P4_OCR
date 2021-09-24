package com.perez.christophe.mareu.repository;

import com.perez.christophe.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Christophe on 03/09/2021.
 */
public abstract class RoomGenerator {


    public static List<Room> ROOMS_LIST = Arrays.asList(
            new Room(1,"Salle 1", 0xff81D4FA),
            new Room(2,"Salle 2",0xff80DEEA),
            new Room(3,"Salle 3",0xff80CBC4),
            new Room(4,"Salle 4",0xffA5D6A7),
            new Room(5,"Salle 5",0xffC5E1A5),
            new Room(6,"Salle 6",0xffE6EE9C),
            new Room(7,"Salle 7",0xffFFF59D),
            new Room(8,"Salle 8",0xffFFE082),
            new Room(9,"Salle 9",0xffFFCC80),
            new Room(10,"Salle 10",0xffFFAB91)
    );

    public static List<Room> generateListOfRoons(){
        return new ArrayList<>(ROOMS_LIST);
    }

}
