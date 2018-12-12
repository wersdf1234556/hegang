package org.tonzoc.Model;

import org.tonzoc.Common.Utility;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/11/15 0015.
 */
public class Flow {
    private  long id;
    private  String name;
    private long projectId;
    private  Timestamp createdAt;
    private  Timestamp updatedAt;
    private Project project;

    public Flow(long id, String name, long projectId, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.projectId = projectId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Flow() {
		super();
		// TODO Auto-generated constructor stub
	}

    public String getCreatedAt() {
        return Utility.formatTimestamp(createdAt);
    }

    public String getUpdatedAt() {
        return Utility.formatTimestamp(updatedAt);
    }

	public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getProjectId() {
        return projectId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
