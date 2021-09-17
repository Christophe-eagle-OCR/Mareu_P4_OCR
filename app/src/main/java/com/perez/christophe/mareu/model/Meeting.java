package com.perez.christophe.mareu.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Christophe on 03/09/2021.
 */
public class Meeting implements Serializable {

    private String mObject;
    //private Date mDate;
    private String mDate;
    private String mStartTime;
    private String mEndTime;
    //private List<String> mParticipants;
    private String mParticipants;
    private Room mRoom;


    /**
     * Constructor
     * @param object
     * @param date
     * @param startTime
     * @param endTime
     * @param participants
     * @param room
     */
    public Meeting(String object, String date, String startTime, String endTime, String participants, Room room) {
        mObject = object;
        mDate = date;
        mStartTime = startTime;
        mEndTime = endTime;
        mParticipants = participants;
        mRoom = room;
    }

    public String getObject() {
        return mObject;
    }

    public void setObject(String object) {
        mObject = object;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public void setStartTime(String startTime) {
        mStartTime = startTime;
    }

    public String getEndTime() {
        return mEndTime;
    }

    public void setEndTime(String endTime) {
        mEndTime = endTime;
    }

    public String getParticipants() {
        return mParticipants;
    }

    public void setParticipants(String participants) {
        mParticipants = participants;
    }

    public Room getRoom() {
        return mRoom;
    }

    public void setRoom(Room room) {
        mRoom = room;
    }

}
