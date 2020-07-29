package com.udacity.gradle.builditbigger;





import android.content.pm.ActivityInfo;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;


import com.udacity.gradle.builditbigger.IdlingResources.MyIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskAndroidTest {

    private IdlingResource mIdlingResource;
    private final static String EXTRAS_JOKE = "joke";
    private final Object LOCK = new Object();
    private ActivityScenario mActivityScenario;


    @Rule
    public ActivityTestRule<DisplayActivity> mDisplayActivityTestRule =
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
                IdlingRegistry.getInstance().register(MyIdlingResource.idlingResource);
            }
        });
    }

    @After
    public void unregisterIdlingResource(){
        IdlingRegistry.getInstance().unregister(MyIdlingResource.idlingResource);
    }


    @Test
    public void testBackPress() throws InterruptedException {
        synchronized (LOCK){
            LOCK.wait(2000);
        }

        onView(withId(R.id.joke_button))
                .perform(click());

        onView(isRoot()).perform(ViewActions.pressBack());

    }



    @Test
    public void testJokeDisplay() throws InterruptedException {
        synchronized (LOCK){
            LOCK.wait(2000);
        }

        onView(withId(R.id.joke_button))
                .perform(click());

        onView(isRoot()).perform(ViewActions.pressBack());

        synchronized (LOCK){
            LOCK.wait(2000);
        }


        onView(withId(R.id.joke_text_view))
                .check(matches(isDisplayed()));
    }


    @Test
    public void testScreenRotation() throws InterruptedException {

        synchronized (LOCK){
            LOCK.wait(2000);
        }

        onView(withId(R.id.joke_button))
                .perform(click());

        onView(isRoot()).perform(ViewActions.pressBack());

        mDisplayActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mDisplayActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.joke_text_view))
                .check(matches(isDisplayed()));

        onView(withId(R.id.joke_text_view))
                .check(matches(not(withText(""))));
    }

    @Test
    public void testIntend() throws InterruptedException {

        synchronized (LOCK){
            LOCK.wait(2000);
        }

        onView(withId(R.id.joke_button))
                .perform(click());

        onView(isRoot()).perform(ViewActions.pressBack());

        intended(hasComponent(DisplayActivity.class.getName()));
        intended(hasExtraWithKey(EXTRAS_JOKE));

    }

}
