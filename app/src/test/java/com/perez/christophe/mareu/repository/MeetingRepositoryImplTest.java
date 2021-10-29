package com.perez.christophe.mareu.repository;


import android.content.Context;
import android.os.Build;

import androidx.test.core.app.ApplicationProvider;

import com.perez.christophe.mareu.R;
import com.perez.christophe.mareu.di.DI;
import com.perez.christophe.mareu.model.Meeting;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Christophe on 21/10/2021.
 * <p>
 * Unit test on Meeting repository
 */

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.P)
public class MeetingRepositoryImplTest {

    private MeetingRepository mMeetingRepository;

    //private List<Meeting> mMeetingList;
    //private Calendar mCalendar = Calendar.getInstance();
    //private Date mDate = mCalendar.getTime();

    @Before
    public void setUp() throws Exception {
        mMeetingRepository = DI.getNewInstanceMeetingRepository();
    }

    /**
     * list of meetings
     * Should be empty at start
     */
    @Test
    public void getMeetingsWithSuccess() {
        // Given

        // When
        List<Meeting> actualMeetings = mMeetingRepository.getMeetings();

        // Then
        assertEquals(0, actualMeetings.size());
    }

    /**
     * create and Add a meeting then check if List of meeting contains it.
     * Should be with meetings
     */
    @Test
    public void addMeetingWithSuccess() {
        // Given
        Meeting testMeeting1 = new Meeting("Reunion 1", "jeudi 21 octobre 2021", "16h10", "15 minutes", "toto@gmail.com", RoomGenerator.ROOMS_LIST.get(0));
        Meeting testMeeting2 = new Meeting("Reunion 2", "vendredi 22 octobre 2021", "16h00", "30 minutes", "toto@gmail.com", RoomGenerator.ROOMS_LIST.get(1));

        mMeetingRepository.addMeeting(testMeeting1);
        mMeetingRepository.addMeeting(testMeeting2);

        // When
        List<Meeting> actualList = mMeetingRepository.getMeetings();
        List<Meeting> expectedList = Arrays.asList(testMeeting1, testMeeting2);

        // Then
        assertTrue(actualList.contains(testMeeting1));
        assertEquals(2, actualList.size());
        assertTrue(actualList.containsAll(expectedList));
        assertEquals(expectedList, actualList);
        //assertThat(actualList, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedList.toArray()));
    }

    /**
     * create , Add  and Delete a meeting then check if List of meeting not contains it.
     * Should be empty
     */
    @Test
    public void deleteMeetingWithSuccess() {
        // Given
        Meeting testMeeting1 = new Meeting("Reunion 1", "jeudi 21 octobre 2021", "16h10", "15 minutes", "toto@gmail.com", RoomGenerator.ROOMS_LIST.get(0));
        Meeting testMeeting2 = new Meeting("Reunion 2", "vendredi 22 octobre 2021", "16h00", "30 minutes", "toto@gmail.com", RoomGenerator.ROOMS_LIST.get(1));

        mMeetingRepository.getMeetings().clear();
        mMeetingRepository.addMeeting(testMeeting1);
        mMeetingRepository.addMeeting(testMeeting2);

        // When
        mMeetingRepository.deleteMeeting(testMeeting1);
        mMeetingRepository.deleteMeeting(testMeeting2);
        List<Meeting> actualMeetings = mMeetingRepository.getMeetings();

        // Then
        assertFalse(actualMeetings.contains(testMeeting2));
        assertEquals(0, actualMeetings.size());
    }


    /**
     * Filter list by date, and return array of meetings.
     * then check if size list is equals and the meeting have correct date
     */
    @Test
    public void getMeetingFilteredByDateWithSuccess() {
        // Given
        String filterDateString = "jeudi 21 octobre 2021";

        Meeting testMeeting1 = new Meeting("Reunion 1", "jeudi 21 octobre 2021", "16h10", "15 minutes", "toto@gmail.com", RoomGenerator.ROOMS_LIST.get(0));
        Meeting testMeeting2 = new Meeting("Reunion 2", "vendredi 22 octobre 2021", "16h00", "30 minutes", "titi@gmail.com", RoomGenerator.ROOMS_LIST.get(1));
        Meeting testMeeting3 = new Meeting("Reunion 3", "jeudi 21 octobre 2021", "11h00", "45 minutes", "tata@gmail.com", RoomGenerator.ROOMS_LIST.get(2));

        mMeetingRepository.getMeetings().clear();
        mMeetingRepository.addMeeting(testMeeting1);
        mMeetingRepository.addMeeting(testMeeting2);
        mMeetingRepository.addMeeting(testMeeting3);

        // When
        List<Meeting> actualMeetings = mMeetingRepository.getMeetingFilteredByDate(filterDateString);

        // Then
        assertEquals(2, actualMeetings.size());
        assertTrue(actualMeetings.contains(testMeeting1));
        assertFalse(actualMeetings.contains(testMeeting2));
        assertTrue(actualMeetings.contains(testMeeting3));
        assertTrue(actualMeetings.size() == 2);

        // Should be use for testing but in not
        //  List<Meeting> expectedMeetings = new ArrayList<>();
        //  for (int i = 0; i < actualMeetings.size(); i = i + 1) {
        //      Meeting currentMeeting = actualMeetings.get(i);
        //      assertEquals(currentMeeting.getDate(), filterDateString);
        //      expectedMeetings.add(currentMeeting);
        //  }
    }

    /**
     * Filter list by room
     */
    @Test
    public void getMeetingFilteredByRoomWithSuccess() {
        // Given
        //String filterRoonString = RoomGenerator.ROOMS_LIST_STRING_TABLEAU[0];  // OK
        //String filterRoonString = "Salle 1";  // OK
        //String filterRoonString = RoomGenerator.ROOMS_LIST.get(0).getNameOfRoom();  // OK

        Context context = ApplicationProvider.getApplicationContext();
        String filterRoonString = context.getResources().getStringArray(R.array.list_of_meeting_rooms)[0]; // OK


        Meeting testMeeting1 = new Meeting("Reunion 1", "jeudi 21 octobre 2021", "16h10", "15 minutes", "toto@gmail.com", RoomGenerator.ROOMS_LIST.get(0));
        Meeting testMeeting2 = new Meeting("Reunion 2", "vendredi 22 octobre 2021", "16h00", "30 minutes", "titi@gmail.com", RoomGenerator.ROOMS_LIST.get(1));
        Meeting testMeeting3 = new Meeting("Reunion 3", "jeudi 21 octobre 2021", "11h00", "45 minutes", "tata@gmail.com", RoomGenerator.ROOMS_LIST.get(2));
        Meeting testMeeting4 = new Meeting("Reunion 4", "lundi 25 octobre 2021", "10h00", "1 heure", "tete@gmail.com", RoomGenerator.ROOMS_LIST.get(0));

        mMeetingRepository.getMeetings().clear();
        mMeetingRepository.addMeeting(testMeeting1);
        mMeetingRepository.addMeeting(testMeeting2);
        mMeetingRepository.addMeeting(testMeeting3);
        mMeetingRepository.addMeeting(testMeeting4);

        // When
        List<Meeting> actualMeetings = mMeetingRepository.getMeetingFilteredByRoom(filterRoonString);

        // Then
        assertEquals(2, actualMeetings.size());
        assertTrue(actualMeetings.contains(testMeeting1));
        assertFalse(actualMeetings.contains(testMeeting2));
        assertFalse(actualMeetings.contains(testMeeting3));
        assertTrue(actualMeetings.contains(testMeeting4));
        assertTrue(actualMeetings.size() == 2);
    }
}