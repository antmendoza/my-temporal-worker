package com.antmendoza.workflow;

import io.temporal.activity.LocalActivityOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.time.Duration;
import java.util.Random;

@WorkflowImpl(taskQueues = "DemoTaskQueue")
public class WorkflowWithLocalActivityImpl implements WorkflowWithLocalActivity4 {
    public static final Logger log = Workflow.getLogger(WorkflowWithLocalActivityImpl.class);

    private final GreetingActivities localActivities =
            Workflow.newLocalActivityStub(
                    GreetingActivities.class,
                    LocalActivityOptions.newBuilder()
                            .setStartToCloseTimeout(Duration.ofSeconds(7)).build());

    @Override
    public String getGreeting(String name) {


        int min = 1;
        int max = 5;
        int sleep = new Random().nextInt(max - min + 1) + min;


        String s = localActivities.sleepForSeconds(sleep);

        log.info("Result " + s);
        return s;

    }


}
