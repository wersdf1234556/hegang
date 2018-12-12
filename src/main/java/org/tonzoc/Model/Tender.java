package org.tonzoc.Model;

import org.tonzoc.Common.Utility;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Tender {

    private  long id;
    private  String name;
    private  long projectId;
    private  String code;
    private  String startSection;
    private  String endSection;
    private  BigDecimal length;
    private  String startDate;
    private  String endDate;
    private  String ownerCompany; //业主
    private  String contractCompany;  //承包方
    private  String contractTel;//承包单位电话
    private  String superviseCompany;//监理单位
    private  String superviseTel;//监理单位电话
    private  String testCompany;//检测单位
    private  String defaultApprovalDate;
    private  String note;//备注
    private  Timestamp createdAt;
    private  Timestamp updatedAt;

    public Tender(long id, String name, long projectId, String code, String startSection, String endSection, BigDecimal length, String startDate, String endDate, String ownerCompany, String contractCompany, String contractTel, String superviseCompany, String superviseTel, String testCompany, String defaultApprovalDate, String note, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.projectId = projectId;
        this.code = code;
        this.startSection = startSection;
        this.endSection = endSection;
        this.length = length;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ownerCompany = ownerCompany;
        this.contractCompany = contractCompany;
        this.contractTel = contractTel;
        this.superviseCompany = superviseCompany;
        this.superviseTel = superviseTel;
        this.testCompany = testCompany;
        this.defaultApprovalDate = defaultApprovalDate;
        this.note = note;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Tender() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setStartSection(String startSection) {
		this.startSection = startSection;
	}

	public void setEndSection(String endSection) {
		this.endSection = endSection;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setOwnerCompany(String ownerCompany) {
		this.ownerCompany = ownerCompany;
	}

	public void setContractCompany(String contractCompany) {
		this.contractCompany = contractCompany;
	}

	public void setContractTel(String contractTel) {
		this.contractTel = contractTel;
	}

	public void setSuperviseCompany(String superviseCompany) {
		this.superviseCompany = superviseCompany;
	}

	public void setSuperviseTel(String superviseTel) {
		this.superviseTel = superviseTel;
	}

	public void setTestCompany(String testCompany) {
		this.testCompany = testCompany;
	}

	public void setDefaultApprovalDate(String defaultApprovalDate) {
		this.defaultApprovalDate = defaultApprovalDate;
	}

	public void setNote(String note) {
		this.note = note;
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

    public String getStartSection() {
        return startSection;
    }

    public String getEndSection() {
        return endSection;
    }

    public BigDecimal getLength() {
        return length;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getOwnerCompany() {
        return ownerCompany;
    }

    public String getContractCompany() {
        return contractCompany;
    }

    public String getContractTel() {
        return contractTel;
    }

    public String getSuperviseCompany() {
        return superviseCompany;
    }

    public String getSuperviseTel() {
        return superviseTel;
    }

    public String getTestCompany() {
        return testCompany;
    }

    public String getDefaultApprovalDate() {
        return defaultApprovalDate;
    }

    public String getNote() {
        return note;
    }

    public long getProjectId() {
        return projectId;
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
