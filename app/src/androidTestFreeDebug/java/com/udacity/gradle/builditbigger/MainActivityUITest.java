package com.udacity.gradle.builditbigger;

import android.content.pm.ActivityInfo;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.IdlingResources.MyIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource(){
        IdlingRegistry.getInstance().register(MyIdlingResource.idlingResource);
    }

    @After
    public void unregisterIdlingResource(){
        IdlingRegistry.getInstance().unregister(MyIdlingResource.idlingResource);
    }

    @Test
    public void testDefaultUI(){
        onView(withId(R.id.main_progress_bar))
                .check(matches(not(isDisplayed())));

        onView(withId(R.id.joke_button))
                .check(matches(isEnabled()));

        onView(withId(R.id.instructions_text_view))
                .check(matches(withText(R.string.instructions)));

        onView(withId(R.id.adView))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void testScreenRotation(){

        mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.main_progress_bar))
                .check(matches(not(isDisplayed())));

        onView(withId(R.id.joke_button))
                .check(matches(isEnabled()));

        onView(withId(R.id.instructions_text_view))
                .check(matches(withText(R.string.instructions)));

        onView(withId(R.id.adView))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

}
