package org.tonzoc.Model;

import org.tonzoc.Common.Utility;

import java.sql.Timestamp;

public class Period {

    private  long id;
    private  String name;
    private long projectId;
    private  String startDate;
    private  String endDate;
    private  Timestamp createdAt;
    private  Timestamp updatedAt;
    private Project project;

    public Period(long id, String name, long projectId, String startDate, String endDate, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.projectId = projectId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Period() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getCreatedAt() {
        return Utility.formatTimestamp(createdAt);
    }

    public String getUpdatedAt() {
        return Utility.formatTimestamp(updatedAt);
    }
}
