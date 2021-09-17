package com.perez.christophe.mareu.model;

import java.io.Serializable;

/**
 * Created by Christophe on 03/09/2021.
 */
public class Room  implements Serializable {

    private long id;
    private String mNameOfRoom;
    private int mColorOfRoom;


    /**
     * Constructor
     * @param id
     * @param nameOfRoom
     * @param colorOfRoom
     */
    public Room(long id, String nameOfRoom, int colorOfRoom) {
        this.id = id;
        mNameOfRoom = nameOfRoom;
        mColorOfRoom = colorOfRoom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameOfRoom() {
        return mNameOfRoom;
    }

    public void setNameOfRoom(String nameOfRoom) {
        mNameOfRoom = nameOfRoom;
    }

    public int getColorOfRoom() {
        return mColorOfRoom;
    }

    public void setColorOfRoom(int colorOfRoom) {
        mColorOfRoom = colorOfRoom;
    }

}

