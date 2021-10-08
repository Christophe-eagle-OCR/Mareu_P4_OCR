package com.perez.christophe.mareu.repository;

import com.perez.christophe.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christophe on 03/09/2021.
 */
public class MeetingRepositoryImpl implements MeetingRepository {

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

    // Pour fitrer par date : on compare 2 date de type String
    @Override
    public List<Meeting> getMeetingFilteredByDate(String filterByDate) {
        List<Meeting> filteredMeetingByDateList = new ArrayList<>();

        for (int i = 0; i < mMeetings.size(); i = i + 1) {
            Meeting currentMeeting = mMeetings.get(i);
            if (currentMeeting.getDate().equals(filterByDate)) {
                filteredMeetingByDateList.add(currentMeeting);
            }
        }
        return filteredMeetingByDateList;
    }

}
