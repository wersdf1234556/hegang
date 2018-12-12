package org.tonzoc.Model;

import org.tonzoc.Common.Utility;

import java.sql.Timestamp;
import java.util.List;

public class Step {
    private long id;
    private long flowId;
    private String name;
    private long nextStepId;
    private User user;
    private Flow flow;
    private Step nextStep;

    public Step getNextStep() {
        return nextStep;
    }

    public void setNextStep(Step nextStep) {
        this.nextStep = nextStep;
    }

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //    public Step(long id, String name, long nextStepId, String nextStepName) {
//        this.id = id;
//        this.name = name;
//        this.nextStepId = nextStepId;
//        this.nextStepName = nextStepName;
//    }

    public Step(long id, long flowId, String name, long nextStepId,  Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.flowId = flowId;
        this.name = name;
        this.nextStepId = nextStepId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private Timestamp createdAt;
    private Timestamp updatedAt;

    public long getId() {
        return id;
    }

    public long getFlowId() {
        return flowId;
    }

    public String getName() {
        return name;
    }

    public long getNextStepId() {
        return nextStepId;
    }

//    public String getNextStepName() {
//        return nextStepId == 0 ? "结束" : nextStepName;
//    }

    public String getCreatedAt() {
        return Utility.formatTimestamp(createdAt);
    }

    public String getUpdatedAt() {
        return Utility.formatTimestamp(updatedAt);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFlowId(long flowId) {
        this.flowId = flowId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNextStepId(long nextStepId) {
        this.nextStepId = nextStepId;
    }

//    public void setNextStepName(String nextStepName) {
//        this.nextStepName = nextStepName;
//    }
}
