package com.antmendoza.workflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface WorkflowWithLocalActivity4 {

    @WorkflowMethod
    String getGreeting(String name);

}
