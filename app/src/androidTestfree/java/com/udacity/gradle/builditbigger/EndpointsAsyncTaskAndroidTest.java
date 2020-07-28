package com.udacity.gradle.builditbigger;





import android.content.pm.ActivityInfo;
import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;


import com.google.android.gms.ads.InterstitialAd;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import gradle.udacity.displaylibrary.DisplayActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskAndroidTest {

    private IdlingResource mIdlingResource;
    ActivityScenario mActivityScenario;
    private final static String EXTRAS_JOKE = "joke";

    @Rule
    public ActivityTestRule<DisplayActivity> mActivityTestRule =
            new ActivityTestRule<>(DisplayActivity.class);

    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule =
            new IntentsTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource(){
        mActivityScenario = ActivityScenario.launch(MainActivity.class);
        mActivityScenario.onActivity(new ActivityScenario.ActivityAction<MainActivity>(){

            @Override
            public void perform(MainActivity activity) {
                mIdlingResource = activity.getIdlingResource();

                IdlingRegistry.getInstance().register(mIdlingResource);
            }
        });
    }


    @Test
    public void testJokeLoading(){
        onView(withId(R.id.joke_button))
                .perform(click());

        onView(isRoot())
                .perform(ViewActions.pressBack());

        onView(withId(R.id.joke_text_view))
                .check(matches(isDisplayed()));

        onView(withId(R.id.joke_text_view))
                .check(matches(not(withText(""))));
    }



    @Test
    public void testClosingAd(){
        onView(withId(R.id.joke_button))
                .perform(click());

        onView(isRoot())
                .perform(ViewActions.pressBack());

        onView(withId(R.id.instructions_text_view))
                .check(matches(isDisplayed()));
    }


   /* @Test
    public void testScreenRotation(){
        onView(withId(R.id.joke_button))
                .perform(click());

        mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.joke_text_view))
                .check(matches(isDisplayed()));

        onView(withId(R.id.joke_text_view))
                .check(matches(not(withText(""))));
    }*/

    /*@Test
    public void testIntend(){

        onView(withId(R.id.joke_button))
                .perform(click());

        intended(hasComponent(DisplayActivity.class.getName()));
        intended(hasExtraWithKey(EXTRAS_JOKE));

    }*/

    @After
    public void unregisterIdlingResource(){
        if(mIdlingResource != null){
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}
