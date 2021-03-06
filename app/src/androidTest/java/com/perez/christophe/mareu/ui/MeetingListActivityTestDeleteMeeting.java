package com.perez.christophe.mareu.ui;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.perez.christophe.mareu.R;
import com.perez.christophe.mareu.ui.meeting_list.MeetingListActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Christophe on 03/11/2021.
 * Add one meeting and delete this meeting
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MeetingListActivityTestDeleteMeeting {

    @Rule
    public ActivityTestRule<MeetingListActivity> mActivityTestRule = new ActivityTestRule<>(MeetingListActivity.class);

  //  @Before
  //  public void setup(){
  //      //activityScenarioRule.getScenario().onActivity(MeetingListActivity::emptyMeetingList); // OK , if use ActivityScenario
  //      mActivityTestRule.getActivity().runOnUiThread(new Runnable() {
  //          @Override
  //          public void run() {
  //              mActivityTestRule.getActivity().emptyMeetingList();
  //          }
  //      });
  //  }

  //  @After
  //  public void TearDown(){
  //  }

    @Test
    public void meetingListActivityTest_deleteMeeting() {

        // app : ADD A FIRST MEETING
        //click on button to add meeting
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_meeting_fab), withContentDescription("Ajouter une r??union"),
                        isDisplayed()));
        floatingActionButton.perform(click());

        //open the spinner displays a dropdown menu with all rooms meeting name
        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner_list_of_rooms), isDisplayed()));
        appCompatSpinner.perform(scrollTo(), click());
        //select the room meeting name "Salle 1"
        DataInteraction appCompatCheckedTextView = onData(anything()).atPosition(0);
        appCompatCheckedTextView.perform(click());

        //add text in the editText for the meeting name
        ViewInteraction textInputEditText = onView(withId(R.id.object_text_field_edit_text));
        textInputEditText.perform(scrollTo(), replaceText("Reunion 1"), closeSoftKeyboard());

        //select the date of meeting in dialog
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.select_date_button), withText("select date")));
        materialButton.perform(scrollTo(), click());  // here , current date in DatePicker
        //validate the date selected
        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        materialButton2.perform(scrollTo(), click());

        //select time of meeting
        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.select_time_button), withText("select time")));
        materialButton3.perform(scrollTo(), click());
        //validate the time
        ViewInteraction materialButton4 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        materialButton4.perform(scrollTo(), click());

        //open the spinner displays a dropdown menu with all meeting durations
        ViewInteraction materialAutoCompleteTextView = onView(
                allOf(withId(R.id.end_time2_text_field)));
        materialAutoCompleteTextView.perform(scrollTo(), click());
        //select meeting durations
        DataInteraction materialTextView = onData(equalTo("30 minutes")).inRoot(RootMatchers.isPlatformPopup());
        materialTextView.perform(click());

        //add one email address (text) in the editText for the participant name
        ViewInteraction textInputEditText2 = onView(withId(R.id.list_of_participants_text_field_edit_text));
        textInputEditText2.perform(scrollTo(), replaceText("toto@gmail,.com"), closeSoftKeyboard());

        //create the meeting on click on create button
        ViewInteraction extendedFloatingActionButton = onView(
                allOf(withId(R.id.create_meeting_fab), withText("Cr??er la reunion"), withContentDescription("cr??er une r??union")));
        extendedFloatingActionButton.perform(scrollTo(), click());

        // TEST :  Should be display screen " Ma r??u" (main screen)
        onView(withId(R.id.main_content_list_meetings)).check(matches(isDisplayed()));

        // TEST :  Should be display screen : count 1 item_rv in main
        onView(withId(R.id.list_meeting_rv)).check(matches(isDisplayed()));
        onView(withId(R.id.list_meeting_rv)).check(matches(hasChildCount(1)));
        onView(withId(R.id.list_meeting_rv)).check(matches(hasMinimumChildCount(1)));

        // TEST :  Should deleted 1 item : use delete button
        onView(withId(R.id.item_meeting_delete_room_button)).perform(click());
        onView(withId(R.id.main_content_list_meetings)).check(matches(isDisplayed()));
        onView(withId(R.id.list_meeting_rv)).check(matches(hasChildCount(0)));
    }
}
