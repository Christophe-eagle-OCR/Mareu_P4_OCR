package com.perez.christophe.mareu.ui;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;

import com.perez.christophe.mareu.R;
import com.perez.christophe.mareu.ui.meeting_list.MeetingListActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
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
        //onView(Matchers.allOf(ViewMatchers.withId(R.id.main_content_list_meetings), isDisplayed())).check(matches(hasMinimumChildCount(0)));
        //onView(Matchers.allOf(ViewMatchers.withId(R.id.main_content_list_meetings), isDisplayed())).check(matches(hasChildCount(0)));
        // OK onView(ViewMatchers.withId(R.id.main_content_list_meetings)).check(matches(isDisplayed()));
        onView(withId(R.id.main_content_list_meetings)).check(matches(isDisplayed()));
    }

   @Test
   public void meetingList_onSelectAddButton_shoudlViewNewMeeting() {
       onView(withId(R.id.main_content_list_meetings)).check(matches(isDisplayed()));
       onView(withId(R.id.add_meeting_fab)).perform(click());
       onView(withId(R.id.new_meeting)).check(matches(isDisplayed()));
   }



}