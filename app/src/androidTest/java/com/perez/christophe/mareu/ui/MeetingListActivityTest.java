package com.perez.christophe.mareu.ui;

import androidx.test.core.app.ActivityScenario;

import com.perez.christophe.mareu.R;
import com.perez.christophe.mareu.ui.meeting_list.MeetingListActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

/**
 * Created by Christophe on 28/10/2021.
 */
public class MeetingListActivityTest {

    private ActivityScenario<MeetingListActivity> mActivityScenario;

    @Before
    public void setUp() throws Exception {
        mActivityScenario = ActivityScenario.launch(MeetingListActivity.class);
    }

    @After
    public void tearDown() throws Exception {
        mActivityScenario.close();
    }

    /**
     * Test not util for app,
     * only to be sure the test passed
     */
    @Test
    public void newTest() {
        assertTrue(true);
    }


    /**
     * On start app
     * We ensure that our recyclerview is displayed without item
     */
    @Test
    public void meetingList_shouldBeEmpty() {
        // onView(ViewMatchers.withId(R.id.main_content_list_meetings)).check(matches(isDisplayed())); // OK
        onView(withId(R.id.main_content_list_meetings)).check(matches(isDisplayed()));
        onView(withId(R.id.list_meeting_rv)).check(matches(hasChildCount(0)));
        onView(withId(R.id.list_meeting_rv)).check(matches(hasMinimumChildCount(0)));
    }


    /**
     * On start app, click on button +
     * We ensure that the screen to create meeting is displayed
     */
    @Test
    public void meetingList_onSelectAddButton_shouldDisplayNewMeeting() {
        onView(withId(R.id.main_content_list_meetings)).check(matches(isDisplayed()));
        onView(withId(R.id.add_meeting_fab)).perform(click());
        onView(withId(R.id.new_meeting)).check(matches(isDisplayed()));
    }


}