package com.antmendoza;

import com.antmendoza.workflow.WorkflowWithLocalActivity4;
import io.temporal.api.workflowservice.v1.GetSystemInfoRequest;
import io.temporal.api.workflowservice.v1.GetSystemInfoResponse;
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
    private WorkflowClient client;


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


            WorkflowWithLocalActivity4 workflow = client
                    .newWorkflowStub(WorkflowWithLocalActivity4.class, build);


            WorkflowClient.start(workflow::getGreeting, "input");
            WorkflowStub untyped = WorkflowStub.fromTyped(workflow);
            untyped.getResultAsync( String.class).thenApply(result -> {
                logger.info("Starter... result  " + result);
                return result;
            });


            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

    }



    //@EventListener(ApplicationReadyEvent.class)
    //@Async("threadPoolTaskExecutor")
    public void getInfo() {
        WorkflowOptions build = WorkflowOptions.newBuilder()
                .setTaskQueue("DemoTaskQueue").build();

        logger.info("Init...  ");

        while (true) {


            GetSystemInfoResponse response = client.getWorkflowServiceStubs().blockingStub().getSystemInfo(GetSystemInfoRequest.newBuilder().build());

            System.out.println("Response ok " + response);




            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

    }

}
