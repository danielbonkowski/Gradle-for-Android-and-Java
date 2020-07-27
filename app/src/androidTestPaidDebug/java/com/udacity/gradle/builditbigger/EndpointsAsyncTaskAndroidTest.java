package com.udacity.gradle.builditbigger;


import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskAndroidTest {

    private IdlingResource mIdlingResource;


    @Before
    public void registerIdlingResource(){
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
        activityScenario.onActivity(new ActivityScenario.ActivityAction<MainActivity>(){

            @Override
            public void perform(MainActivity activity) {
                mIdlingResource = activity.getIdlingResource();

                IdlingRegistry.getInstance().register(mIdlingResource);
            }
        });

       //throw new RuntimeException("You won't find any tests!");
    }

    @Test
    public void testJokeLoading() throws ExecutionException, InterruptedException {
        onView(withId(R.id.joke_button))
                .perform(click());

        onView(withId(R.id.joke_text_view))
                .check(matches(isDisplayed()));

        onView(withId(R.id.joke_text_view))
                .check(matches(not(withText(""))));

    }

    @After
    public void unregisterIdlingResource(){
        if(mIdlingResource != null){
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}
