package org.tonzoc.Model;

import org.tonzoc.Common.Utility;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/11/19 0019.
 */
public class Province {
    private  long id;
    private  String name;
    private  String code;
    private  Timestamp createdAt;
    private  Timestamp updatedAt;

    public Province(long id, String name, String code, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Province() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getCreatedAt() {
        return Utility.formatTimestamp(createdAt);
    }

    public String getUpdatedAt() {
        return Utility.formatTimestamp(updatedAt);
    }
}
