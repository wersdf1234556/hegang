package org.tonzoc.Model;

import org.tonzoc.Common.Utility;

import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
/**
 * Created by Administrator on 2018/11/20 0020.
 */
public class RoleBind {
	
	private final long id;
	private final long moduleId;
	private final long roleId;
//	private  Role roles;
//	private final String moduleName;
//	private final String targetUrl;
//	private final long parentId;
//	private final Timestamp createdAt;
//	private final Timestamp updatedAt;


	public RoleBind(long id, long moduleId, long roleId) {
		this.id = id;
		this.moduleId = moduleId;
		this.roleId = roleId;
	}

	public long getId() {
		return id;
	}

	public long getModuleId() {
		return moduleId;
	}

	public long getRoleId() {
		return roleId;
	}
}
