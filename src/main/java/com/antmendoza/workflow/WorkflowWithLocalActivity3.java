package com.antmendoza.workflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface WorkflowWithLocalActivity3 {

    @WorkflowMethod
    String getGreeting(String name);

}
