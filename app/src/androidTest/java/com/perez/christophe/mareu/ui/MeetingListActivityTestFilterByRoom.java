package com.perez.christophe.mareu.ui;


import android.widget.DatePicker;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.perez.christophe.mareu.R;
import com.perez.christophe.mareu.ui.meeting_list.MeetingListActivity;
import com.perez.christophe.mareu.utils.RecyclerViewUtils;

import org.hamcrest.Matchers;
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
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.perez.christophe.mareu.utils.RecyclerViewUtils.clickChildView;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Christophe on 05/11/2021.
 * Add two meeting and filter by room , and reset
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MeetingListActivityTestFilterByRoom {

    @Rule
    public ActivityTestRule<MeetingListActivity> mActivityTestRule = new ActivityTestRule<>(MeetingListActivity.class);

    @Before
    public void setup(){
        //activityScenarioRule.getScenario().onActivity(MeetingListActivity::emptyMeetingList); // OK , if use ActivityScenario
        mActivityTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivityTestRule.getActivity().emptyMeetingList();
            }
        });
    }

    @After
    public void TearDown(){
    }

    @Test
    public void meetingListActivityTestFilterByRoom() {

        // app : ADD A FIRST MEETING
        //click on button to add meeting
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_meeting_fab), withContentDescription("Ajouter une réunion"),
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
                allOf(withId(R.id.create_meeting_fab), withText("Créer la reunion"), withContentDescription("créer une réunion")));
        extendedFloatingActionButton.perform(scrollTo(), click());

        // TEST : Should be display screen " Ma réu" (main screen)
        onView(withId(R.id.main_content_list_meetings)).check(matches(isDisplayed()));

        // TEST : Should be display screen : count 1 item_rv in main
        onView(withId(R.id.list_meeting_rv)).check(matches(isDisplayed()));
        onView(withId(R.id.list_meeting_rv)).check(matches(hasChildCount(1)));
        onView(withId(R.id.list_meeting_rv)).check(matches(hasMinimumChildCount(1)));

        // app : ADD A SECOND MEETING
        //click on button to add meeting
        ViewInteraction floatingActionButton1 = onView(
                allOf(withId(R.id.add_meeting_fab), withContentDescription("Ajouter une réunion"),
                        isDisplayed()));
        floatingActionButton1.perform(click());

        //open the spinner displays a dropdown menu with all rooms meeting name
        ViewInteraction appCompatSpinner1 = onView(
                allOf(withId(R.id.spinner_list_of_rooms), isDisplayed()));
        appCompatSpinner1.perform(scrollTo(), click());
        //select the room meeting name "Salle 2"
        DataInteraction appCompatCheckedTextView1 = onData(anything()).atPosition(1);
        appCompatCheckedTextView1.perform(click());

        //add text in the editText for the meeting name
        ViewInteraction textInputEditText1 = onView(withId(R.id.object_text_field_edit_text));
        textInputEditText1.perform(scrollTo(), replaceText("Reunion 2"), closeSoftKeyboard());

        //select the date of meeting in dialog
        ViewInteraction materialButton1 = onView(
                allOf(withId(R.id.select_date_button), withText("select date")));
        materialButton1.perform(scrollTo(), click());  // DatePicker
        // >>>> set date 2021-11-30 in DatePicker for second meeting
        //onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2021, 11, 30)); // OK
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2021, 11, 30));
        //validate the date selected
        ViewInteraction materialButton21 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        materialButton21.perform(scrollTo(), click());

        //select time of meeting
        ViewInteraction materialButton31 = onView(
                allOf(withId(R.id.select_time_button), withText("select time")));
        materialButton31.perform(scrollTo(), click());
        //validate the time
        ViewInteraction materialButton41 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        materialButton41.perform(scrollTo(), click());

        //open the spinner displays a dropdown menu with all meeting durations
        ViewInteraction materialAutoCompleteTextView1 = onView(
                allOf(withId(R.id.end_time2_text_field)));
        materialAutoCompleteTextView1.perform(scrollTo(), click());
        //select meeting durations
        DataInteraction materialTextView1 = onData(equalTo("15 minutes")).inRoot(RootMatchers.isPlatformPopup());
        materialTextView1.perform(click());

        //add one email address (text) in the editText for the participant name
        ViewInteraction textInputEditText21 = onView(withId(R.id.list_of_participants_text_field_edit_text));
        textInputEditText21.perform(scrollTo(), replaceText("titi@gmail,.com"), closeSoftKeyboard());

        //create the meeting on click on create button
        ViewInteraction extendedFloatingActionButton1 = onView(
                allOf(withId(R.id.create_meeting_fab), withText("Créer la reunion"), withContentDescription("créer une réunion")));
        extendedFloatingActionButton1.perform(scrollTo(), click());

        // TEST : Should be display screen " Ma réu" (main screen)
        onView(withId(R.id.main_content_list_meetings)).check(matches(isDisplayed()));

        // TEST : Should be display screen : count 2 item_rv in main
        onView(withId(R.id.list_meeting_rv)).check(matches(isDisplayed()));
        onView(withId(R.id.list_meeting_rv)).check(matches(hasChildCount(2)));
        onView(withId(R.id.list_meeting_rv)).check(matches(hasMinimumChildCount(2)));
        onView(withId(R.id.list_meeting_rv)).check(new RecyclerViewUtils.ItemCount(2));

        // app : Option menu : FILTER BY ROOM >>>> Room choise : Salle 1
        //
        //open menu selection
        ViewInteraction overflowMenuButton1 = onView(
                allOf(withContentDescription("Plus d'options"),
                        isDisplayed()));
        overflowMenuButton1.perform(click());

        //select filter by room of meeting in dialog
        ViewInteraction materialTextView4 = onView(
                allOf(withId(R.id.title), withText("Filtrer par salle"),
                        isDisplayed()));
        materialTextView4.perform(click());
        //select "salle 1" (atPosition(0))
        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview))).atPosition(0);
        appCompatCheckedTextView2.perform(click());
        //validate the room name selected
        ViewInteraction materialButton6 = onView(
                allOf(withId(android.R.id.button1), withText("ok")));
        materialButton6.perform(scrollTo(), click());

        // TEST : Should be display screen " Ma réu" (main screen)
        onView(withId(R.id.main_content_list_meetings)).check(matches(isDisplayed()));

        // TEST : Should be display screen : count 1 item_rv in main whith current date
        onView(withId(R.id.list_meeting_rv)).check(matches(isDisplayed()));
        onView(withId(R.id.list_meeting_rv)).check(matches(hasChildCount(1)));

        // app : Option menu : RESET (after filter room)
        ViewInteraction overflowMenuButton2 = onView(
                allOf(withContentDescription("Plus d'options"),
                        isDisplayed()));
        overflowMenuButton2.perform(click());

        ViewInteraction materialTextView2 = onView(
                allOf(withId(R.id.title), withText("reset"),
                        isDisplayed()));
        materialTextView2.perform(click());

        // TEST : Should be display screen " Ma réu" (main screen)
        onView(withId(R.id.main_content_list_meetings)).check(matches(isDisplayed()));

        // TEST : Should be display screen : count 2 item_rv in main
        onView(withId(R.id.list_meeting_rv)).check(matches(isDisplayed()));
        onView(withId(R.id.list_meeting_rv)).check(matches(hasChildCount(2)));
        onView(withId(R.id.list_meeting_rv)).check(matches(hasMinimumChildCount(2)));
        onView(withId(R.id.list_meeting_rv)).check(new RecyclerViewUtils.ItemCount(2));
    }
}
