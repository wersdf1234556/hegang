package org.tonzoc.Model;

import org.tonzoc.Common.Utility;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/11/15.
 */
public class MemberBind {

//    id       主键
//    stepId   关联steps表
//    userId   关联users表

    private Integer id;
    private User user;
    private long stepId;
    private long flowId;
    private String name;
    private long nextStepId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public MemberBind(Integer id, long stepId, long flowId, String name, long nextStepId, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.stepId = stepId;
        this.flowId = flowId;
        this.name = name;
        this.nextStepId = nextStepId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public MemberBind(long stepId, long flowId, String name, long nextStepId, Timestamp createdAt, Timestamp updatedAt) {
        this.stepId = stepId;
        this.flowId = flowId;
        this.name = name;
        this.nextStepId = nextStepId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getStepId() {
        return stepId;
    }

    public void setStepId(long stepId) {
        this.stepId = stepId;
    }

    public long getFlowId() {
        return flowId;
    }

    public void setFlowId(long flowId) {
        this.flowId = flowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNextStepId() {
        return nextStepId;
    }

    public void setNextStepId(long nextStepId) {
        this.nextStepId = nextStepId;
    }


    public String getCreatedAt() {
        return Utility.formatTimestamp(createdAt);
    }

    public String getUpdatedAt() {
        return Utility.formatTimestamp(updatedAt);
    }

   
}
