package com.perez.christophe.mareu.repository;

import com.perez.christophe.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christophe on 03/09/2021.
 */
public class MeetingRepositoryImpl implements MeetingRepository {

    //todo
    // mettre liste des meeting depuis new meeting  ,
    // add remove,et get
    // creer liste le meeting
    // voir app Entrevoisin


    private final List<Meeting> mMeetings = new ArrayList<>();

    @Override
    public List<Meeting> getMeetings() {
        return mMeetings;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }

    @Override
    public void createMeeting(Meeting meeting) {
        mMeetings.add(meeting);

    }

}
