package com.perez.christophe.mareu.ui.meeting_list;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.perez.christophe.mareu.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
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
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by Christophe on 03/11/2021.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MeetingListActivityTestDeleteMeeting {

    @Rule
    public ActivityTestRule<MeetingListActivity> mActivityTestRule = new ActivityTestRule<>(MeetingListActivity.class);

    @Test
    public void meetingListActivityTest_deleteMeeting() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_meeting_fab), withContentDescription("Ajouter une réunion"),
      //                  childAtPosition(
      //                          allOf(withId(R.id.main_content_list_meetings),
      //                                  childAtPosition(
      //                                          withId(android.R.id.content),
      //                                          0)),
      //                          0),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner_list_of_rooms),isDisplayed()));
      //          allOf(withId(R.id.spinner_list_of_rooms),
      //                  childAtPosition(
      //                          childAtPosition(
      //                                  withId(R.id.new_meeting),
      //                                  0),
      //                          8)));
        appCompatSpinner.perform(scrollTo(), click());


        DataInteraction appCompatCheckedTextView = onData(anything()).atPosition(0);
      //  DataInteraction appCompatCheckedTextView = onData(anything())
      //          .inAdapterView(childAtPosition(
      //                  withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
      //                  0))
      //          .atPosition(0);
        appCompatCheckedTextView.perform(click());

        ViewInteraction textInputEditText = onView(withId(R.id.object_text_field_edit_text));
     //   ViewInteraction textInputEditText = onView(
     //           childAtPosition(
     //                   childAtPosition(
     //                           withId(R.id.object_text_field),
     //                           0),
     //                   0));
        textInputEditText.perform(scrollTo(), replaceText("Reunion 1"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.select_date_button), withText("select date")));
      //          allOf(withId(R.id.select_date_button), withText("select date"),
      //                  childAtPosition(
      //                          childAtPosition(
      //                                  withId(R.id.new_meeting),
      //                                  0),
      //                          6)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
      //          allOf(withId(android.R.id.button1), withText("OK"),
      //                  childAtPosition(
      //                          childAtPosition(
      //                                  withClassName(is("android.widget.ScrollView")),
      //                                  0),
      //                          3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.select_time_button), withText("select time")));
      //          allOf(withId(R.id.select_time_button), withText("select time"),
      //                  childAtPosition(
      //                          childAtPosition(
      //                                  withId(R.id.new_meeting),
      //                                  0),
      //                          7)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
      //          allOf(withId(android.R.id.button1), withText("OK"),
      //                  childAtPosition(
      //                          childAtPosition(
      //                                  withClassName(is("android.widget.ScrollView")),
      //                                  0),
      //                          3)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction materialAutoCompleteTextView = onView(
                allOf(withId(R.id.end_time2_text_field)));
      //         allOf(withId(R.id.end_time2_text_field),
      //                 childAtPosition(
      //                         childAtPosition(
      //                                 withId(R.id.end_time_text_field),
      //                                 0),
      //                         1)));
        materialAutoCompleteTextView.perform(scrollTo(), click());

        DataInteraction materialTextView = onData(equalTo("30 minutes")).inRoot(RootMatchers.isPlatformPopup());
      //  DataInteraction materialTextView = onData(anything())
      //          .inAdapterView(childAtPosition(
      //                  withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
      //                  0))
      //          .atPosition(0);
        materialTextView.perform(click());

        ViewInteraction textInputEditText2 = onView(withId(R.id.list_of_participants_text_field_edit_text));
     //  ViewInteraction textInputEditText2 = onView(
     //          childAtPosition(
     //                  childAtPosition(
     //                          withId(R.id.list_of_participants_text_field),
     //                          0),
     //                  0));
        textInputEditText2.perform(scrollTo(), replaceText("toto@gmail,.com"), closeSoftKeyboard());

        ViewInteraction extendedFloatingActionButton = onView(
                allOf(withId(R.id.create_meeting_fab), withText("Créer la reunion"), withContentDescription("créer une réunion")));
      //          allOf(withId(R.id.create_meeting_fab), withText("Créer la reunion"), withContentDescription("créer une réunion"),
      //                  childAtPosition(
      //                          childAtPosition(
      //                                  withId(R.id.new_meeting),
      //                                  0),
      //                          9)));
        extendedFloatingActionButton.perform(scrollTo(), click());

        // Should be display screen " Ma réu" (main screen)
        onView(withId(R.id.main_content_list_meetings)).check(matches(isDisplayed()));

        // Should be display screen : count 1 item_rv in main
        onView(withId(R.id.list_meeting_rv)).check(matches(isDisplayed()));
        onView(withId(R.id.list_meeting_rv)).check(matches(hasChildCount(1)));
        onView(withId(R.id.list_meeting_rv)).check(matches(hasMinimumChildCount(1)));

        // Should deleted 1 item : use delete button
        onView(withId(R.id.item_meeting_delete_room_button)).perform(click());
        onView(withId(R.id.main_content_list_meetings)).check(matches(isDisplayed()));
        onView(withId(R.id.list_meeting_rv)).check(matches(hasChildCount(0)));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
