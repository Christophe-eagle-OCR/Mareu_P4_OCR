package com.perez.christophe.mareu.repository;

import com.perez.christophe.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Christophe on 03/09/2021.
 *
 * Meeting repository interface
 */


public interface MeetingRepository {

    /**
     * Get all Meetings
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    /**
     * Delete a meeting
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Create a meeting
     * @param meeting
     */
    void createMeeting(Meeting meeting);


    /**
     * Get Meetings filtered by date
     * @param string
     * @return
     */
    List<Meeting> getMeetingFilteredByDate(String string);
}
