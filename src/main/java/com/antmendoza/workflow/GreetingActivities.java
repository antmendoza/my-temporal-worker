package com.antmendoza.workflow;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface GreetingActivities {

    // Define your activity method which can be called during workflow execution
    @ActivityMethod
    String sleepForSeconds(int seconds);
}
