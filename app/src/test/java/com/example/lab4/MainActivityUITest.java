package com.example.lab4;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testListViewItemClick_deletesNote() {
        // Assume that there is at least one item in the list view
        // Click on the first item in the list view
        Espresso.onData(ViewMatchers.anything())
                .inAdapterView(ViewMatchers.withId(R.id.listView))
                .atPosition(0)
                .perform(ViewActions.click());

        // Verify that the note is deleted (you might need to adapt this based on your actual implementation)
        Espresso.onView(ViewMatchers.withText("Note deleted:"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    // Add more UI tests as needed based on your app's features and UI interactions
}
