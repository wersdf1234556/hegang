package org.tonzoc.Model;

import org.tonzoc.Common.Utility;

import java.sql.Timestamp;
import java.util.List;

public class User {

    private  long id;
    private  String code;
    private  long roleId;
    private  String name;
    private  String staffName;
    private  String mobile;
    private  String birthday;
    private  long gender;
    private  String email;
    private List<Step> step;
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Step> getStep() {
        return step;
    }

    public void setStep(List<Step> step) {
        this.step = step;
    }

//    public User(long id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    public User(long id, String code, long roleId, String name, String staffName, String mobile, String birthday, long gender, String email, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.code = code;
        this.roleId = roleId;
        this.name = name;
        this.staffName = staffName;
        this.mobile = mobile;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private  Timestamp createdAt;
    private  Timestamp updatedAt;

    public String getCreatedAt() {
        return Utility.formatTimestamp(createdAt);
    }

    public String getUpdatedAt() {
        return Utility.formatTimestamp(updatedAt);
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getStaffName() {
        return staffName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getBirthday() {
        return birthday;
    }

    public long getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public long getRoleId() {
        return roleId;
    }
}
