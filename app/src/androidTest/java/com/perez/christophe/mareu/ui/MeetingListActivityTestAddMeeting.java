package com.perez.christophe.mareu.ui;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.perez.christophe.mareu.R;
import com.perez.christophe.mareu.di.DI;
import com.perez.christophe.mareu.repository.MeetingRepository;
import com.perez.christophe.mareu.ui.meeting_list.MeetingListActivity;
import com.perez.christophe.mareu.utils.RecyclerViewUtils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
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
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.perez.christophe.mareu.utils.RecyclerViewUtils.clickChildView;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by Christophe on 28/10/2021.
 * Add one meeting
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MeetingListActivityTestAddMeeting {

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
    public void meetingListActivityTest_addMeeting() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_meeting_fab), withContentDescription("Ajouter une r??union"),
                        childAtPosition(
                                allOf(withId(R.id.main_content_list_meetings),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner_list_of_rooms),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.new_meeting),
                                        0),
                                8)));
        appCompatSpinner.perform(scrollTo(), click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
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
                allOf(withId(R.id.select_date_button), withText("select date"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.new_meeting),
                                        0),
                                6)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.select_time_button), withText("select time"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.new_meeting),
                                        0),
                                7)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction materialAutoCompleteTextView = onView(
                allOf(withId(R.id.end_time2_text_field),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.end_time_text_field),
                                        0),
                                1)));
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
                allOf(withId(R.id.create_meeting_fab), withText("Cr??er la reunion"), withContentDescription("cr??er une r??union"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.new_meeting),
                                        0),
                                9)));
        extendedFloatingActionButton.perform(scrollTo(), click());

        // TEST : Should be display screen " Ma r??u" (main screen)
        onView(withId(R.id.main_content_list_meetings)).check(matches(isDisplayed()));

        // TEST : Should be display screen : count 1 item_rv in main
        onView(withId(R.id.list_meeting_rv)).check(matches(isDisplayed()));
        onView(withId(R.id.list_meeting_rv)).check(matches(hasChildCount(1)));
        onView(withId(R.id.list_meeting_rv)).check(new RecyclerViewUtils.ItemCount(1));
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
