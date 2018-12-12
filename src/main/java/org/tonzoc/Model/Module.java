package org.tonzoc.Model;

import org.tonzoc.Common.Utility;

import java.sql.Timestamp;
import java.util.List;

public class Module {

    private final long id;
    private final String name;
    private final String targetUrl;
    private final long parentId;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    private List children;


    public Module(long id, String name, String targetUrl, long parentId, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.targetUrl = targetUrl;
        this.parentId = parentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public long getParentId() {
        return parentId;
    }

    public String getCreatedAt() {
        return Utility.formatTimestamp(createdAt);
    }

    public String getUpdatedAt() {
        return Utility.formatTimestamp(updatedAt);
    }

    public List getChildren() {
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }
}
