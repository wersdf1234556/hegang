package org.tonzoc.Model;

import org.tonzoc.Common.Utility;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/11/19 0019.
 */
public class Project {
    private  long id;
    private  String name;
    private  String code;
    private  long provinceId;
    private  long ownerCompanyId;
    private  BigDecimal length;
    private  String startSection;
    private  String endSection;
    private  String startDate;
    private  String note;
    private  Timestamp createdAt;
    private  Timestamp updatedAt;
    private Company company;
    private Province province;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Project() {
        super();
        // TODO Auto-generated constructor stub
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

    public void setProvinceId(long provinceId) {
        this.provinceId = provinceId;
    }

    public void setOwnerCompanyId(long ownerCompanyId) {
		this.ownerCompanyId = ownerCompanyId;
	}


	public void setLength(BigDecimal length) {
		this.length = length;
	}


	public void setStartSection(String startSection) {
		this.startSection = startSection;
	}


	public void setEndSection(String endSection) {
		this.endSection = endSection;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
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


    public Project(long id, String name, String code, long provinceId, long ownerCompanyId, BigDecimal length, String startSection, String endSection, String startDate, String note, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.provinceId = provinceId;
        this.ownerCompanyId = ownerCompanyId;
        this.length = length;
        this.startSection = startSection;
        this.endSection = endSection;
        this.startDate = startDate;
        this.note = note;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public long getProvinceId() {
        return provinceId;
    }

    public long getOwnerCompanyId() {
        return ownerCompanyId;
    }

    public BigDecimal getLength() {
        return length;
    }

    public String getStartSection() {
        return startSection;
    }

    public String getEndSection() {
        return endSection;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getNote() {
        return note;
    }


    public String getCreatedAt() {
        return Utility.formatTimestamp(createdAt);
    }

    public String getUpdatedAt() {
        return Utility.formatTimestamp(updatedAt);
    }

}
