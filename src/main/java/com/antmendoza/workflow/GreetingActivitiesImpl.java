package com.antmendoza.workflow;

import io.temporal.spring.boot.ActivityImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Simple activity implementation, that concatenates two strings.
 */
@Component
@ActivityImpl(taskQueues = "DemoTaskQueue")
public class GreetingActivitiesImpl implements GreetingActivities {
    private static final Logger log = LoggerFactory.getLogger(GreetingActivitiesImpl.class);


    @Override
    public String sleepForSeconds(int seconds) {

        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Sleep for ["+seconds+"] seconds";
    }
}
