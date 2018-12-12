package org.tonzoc.Model;

import org.tonzoc.Common.Utility;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class Role {

    private  long id;
    private  String name;
    private Timestamp createdAt;
    private  Timestamp updatedAt;


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

//    public Role(long id, String name, Timestamp createdAt, Timestamp updatedAt) {
//        this.id = id;
//        this.name = name;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return Utility.formatTimestamp(createdAt);
    }

    public String getUpdatedAt() {
        return Utility.formatTimestamp(updatedAt);
    }
}
