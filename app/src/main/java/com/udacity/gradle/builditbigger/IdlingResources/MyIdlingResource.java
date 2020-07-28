package com.udacity.gradle.builditbigger.IdlingResources;


import androidx.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;



public class MyIdlingResource implements IdlingResource {

    private volatile ResourceCallback mResourceCallback;

    private AtomicBoolean mIsIdle = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdle.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mResourceCallback = callback;
    }

    public void setIdleState(boolean isIdle){
        mIsIdle.set(isIdle);
        if(isIdle && mResourceCallback != null){
            mResourceCallback.onTransitionToIdle();
        }
    }
}
