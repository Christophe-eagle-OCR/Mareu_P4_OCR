package com.perez.christophe.mareu.repository;

import com.perez.christophe.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

        for (int i = 0; i < mMeetings.size(); i = i +1){
            Meeting currentMeeting = mMeetings.get(i);
            if (currentMeeting.getDate().equals(filterByDate)){
                filteredMeetingByDateList.add(currentMeeting);
            }
        }
        return filteredMeetingByDateList;
    }

    // Pour filtre par date : on compare 2 date de type date avec 2 calendrier
    //  @Override
 //  public List<Meeting> getMeetingFilteredByDate(Date date) {
 //      List<Meeting> result = new ArrayList<>();

 //      // calendrier qui récupère la date choisie dans le filtre (@param date du filtre)
 //      Calendar cal1 = Calendar.getInstance();
 //      cal1.setTime(date);

 //      for (int i = 0; i < mMeetings.size(); i = i + 1){

 //          //calandrier qui récupère le date de la reunion courante du repository
 //          Calendar cal2 = Calendar.getInstance();
 //          cal2.setTime(mMeetings.get(i).getDate());

 //          boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
 //                  cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
 //          if (sameDay) result.add(mMeetings.get(i));
 //      }
 //      return result;
 //  }

}
