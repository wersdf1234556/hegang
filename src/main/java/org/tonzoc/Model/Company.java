package org.tonzoc.Model;

import org.tonzoc.Common.Utility;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2018/11/11 0011.
 */
public class Company {
    private  Integer id;
    private  String name;
    private  String contact;
    private  String tel;
    private  Timestamp createdAt;
    private  Timestamp updatedAt;
//    private List<Project> projects;
//
//    public List<Project> getProjects() {
//        return projects;
//    }
//
//    public void setProjects(List<Project> projects) {
//        this.projects = projects;
//    }

    public Company(Integer id, String name, String contact, String tel, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.tel = tel;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Company(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Company() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getTel() {
        return tel;
    }

    public String getCreatedAt() {
        return Utility.formatTimestamp(createdAt);
    }

    public String getUpdatedAt() {
        return Utility.formatTimestamp(updatedAt);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
