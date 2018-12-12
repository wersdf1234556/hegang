package org.tonzoc.Model;

import org.tonzoc.Common.Utility;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2018/11/15 0015.
 */
public class FlowPaths {
    private  long id;
    private  long a15Id;         //a15id
    private  long stepId;        //步骤id
	private  long currentUserId;
    private  String status;       //审批状态
    private  Timestamp createdAt;
    private  Timestamp updatedAt;
    private A15 a15;
    private Step step;

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

	public A15 getA15() {
		return a15;
	}

	public void setA15(A15 a15) {
		this.a15 = a15;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getA15Id() {
		return a15Id;
	}
	public void setA15Id(long a15Id) {
		this.a15Id = a15Id;
	}
	public long getStepId() {
		return stepId;
	}
	public void setStepId(long stepId) {
		this.stepId = stepId;
	}
	public long getCurrentUserId() {
		return currentUserId;
	}
	public void setCurrentUserId(long currentUserId) {
		this.currentUserId = currentUserId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

    public String getCreatedAt() {
		return Utility.formatTimestamp(createdAt);
	}

	public String getUpdatedAt() {
		return Utility.formatTimestamp(updatedAt);
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
    
  
    
}
