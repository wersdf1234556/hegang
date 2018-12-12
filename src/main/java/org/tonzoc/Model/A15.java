package org.tonzoc.Model;

import org.tonzoc.Common.Utility;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class A15 {

    private  long id;
    private  long meterageListId;
    private  String startSection;
    private  String endSection;
    private  String part;
    private  String pictureNo;
    private  String certNo;
    private  String imageUrl;
    private  String formula;
    private  BigDecimal num;
    private  String date;
    private  long creatorUserId;
    private String status;
    private MeterageList meterageList;
    private FlowPaths flowPaths;

    public FlowPaths getFlowPaths() {
        return flowPaths;
    }

    public void setFlowPaths(FlowPaths flowPaths) {
        this.flowPaths = flowPaths;
    }
    //    private  String listNo;
//    private  String itemName;
//    private  String unit;
//    private  BigDecimal total;
//    private  BigDecimal price;
//    private  BigDecimal balance;

    public void setId(long id) {
		this.id = id;
	}

	public void setMeterageListId(long meterageListId) {
		this.meterageListId = meterageListId;
	}

	public void setStartSection(String startSection) {
		this.startSection = startSection;
	}

	public void setEndSection(String endSection) {
		this.endSection = endSection;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public void setPictureNo(String pictureNo) {
		this.pictureNo = pictureNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setCreatorUserId(long creatorUserId) {
		this.creatorUserId = creatorUserId;
	}

	public void setMeterageList(MeterageList meterageList) {
		this.meterageList = meterageList;
	}

	private  Timestamp createdAt;
    private  Timestamp updatedAt;


	public A15() {
	}

    public A15(long id, long meterageListId, String startSection, String endSection, String part, String pictureNo, String certNo, String imageUrl, String formula, BigDecimal num, String date, long creatorUserId, String status, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.meterageListId = meterageListId;
        this.startSection = startSection;
        this.endSection = endSection;
        this.part = part;
        this.pictureNo = pictureNo;
        this.certNo = certNo;
        this.imageUrl = imageUrl;
        this.formula = formula;
        this.num = num;
        this.date = date;
        this.creatorUserId = creatorUserId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public long getMeterageListId() {
        return meterageListId;
    }

    public String getStartSection() {
        return startSection;
    }

    public String getEndSection() {
        return endSection;
    }

    public String getPart() {
        return part;
    }

    public String getPictureNo() {
        return pictureNo;
    }

    public String getCertNo() {
        return certNo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getFormula() {
        return formula;
    }

    public BigDecimal getNum() {
        return num;
    }

    public String getDate() {
        return date;
    }

    public MeterageList getMeterageList() {
        return meterageList;
    }

    public String getCreatedAt() {
        return Utility.formatTimestamp(createdAt);
    }

    public String getUpdatedAt() {
        return Utility.formatTimestamp(updatedAt);
    }

    public long getCreatorUserId() {
        return creatorUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
