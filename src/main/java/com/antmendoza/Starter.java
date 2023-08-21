package com.antmendoza;

import com.antmendoza.workflow.WorkflowWithLocalActivity3;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;



@Component
public class Starter {


    final
    WorkflowClient client;


    public Starter(WorkflowClient client) {
        this.client = client;
    }

    Logger logger = LoggerFactory.getLogger(Starter.class);


    @EventListener(ApplicationReadyEvent.class)
    @Async("threadPoolTaskExecutor")
    public void postConstruct() {
        WorkflowOptions build = WorkflowOptions.newBuilder()
                .setTaskQueue("DemoTaskQueue").build();

        logger.info("Init...  ");

        while (true) {


            WorkflowWithLocalActivity3 workflow = client
                    .newWorkflowStub(WorkflowWithLocalActivity3.class, build);


            WorkflowClient.start(workflow::getGreeting, "input");
            WorkflowStub untyped = WorkflowStub.fromTyped(workflow);
            untyped.getResultAsync( String.class).thenApply(result -> {
                logger.info("Starter... result  " + result);
                return result;
            });


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

    }

}
