package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Pair;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import gradle.udacity.displaylibrary.DisplayActivity;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskAndroidTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testCheckGCEResponse() throws ExecutionException, InterruptedException {
        String joke =  new MainActivity.EndpointsAsyncTask().execute(new Pair<Context, String>(InstrumentationRegistry.getInstrumentation().getContext(), "Daniel")).get();
        Assert.assertNotEquals("", joke);
        Assert.assertNotEquals(null, joke);
    }
}
