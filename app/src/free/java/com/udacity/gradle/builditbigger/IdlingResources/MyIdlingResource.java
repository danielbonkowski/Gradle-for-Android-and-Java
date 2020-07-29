package com.udacity.gradle.builditbigger.IdlingResources;

import androidx.test.espresso.idling.CountingIdlingResource;

public class MyIdlingResource  {

    private static final String RESOURCE = "RESOURCE";
    public final static CountingIdlingResource idlingResource = new CountingIdlingResource(RESOURCE);

    public static void increment(){
        idlingResource.increment();
    }

    public static void decrement(){
        if(!idlingResource.isIdleNow()){
            idlingResource.decrement();
        }
    }
}
