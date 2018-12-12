package org.tonzoc.Model;

import org.tonzoc.Common.Utility;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/11/11.
 */
public class Basic {

    /* id:id
     name: ‘城市环路南岗路改扩建工程项目’, // 项目名称
     company:公司管理。    contractCompany: 1, // 施工单位，从公司管理中获取  ownerCompany: 2, // 业主单位，从公司管理中获取
     length: 6770.000, // 总体长度
     startSection: K0+000, // 起始桩号
     endSection: K6+770, // 终止桩号
     startDate: ‘2018-11-11’, // 开工日期
     materialRatio: 0.3, // 材料预扣除比例30%
     materialPeriod: 3, // 材料预扣除期数
     startRatio: 0.3, // 开工预扣除比例30%
     startPeriod: 3, // 开工预扣除期数
     retentionRatio: 0.3, // 保留金扣除比例30%
     retentionPeriod: 3, // 保留金扣除期数
     note: ‘备注’, // 备注*/
    private Integer id;
    private String name;
    private Integer companyid;
    private BigDecimal length;
    private String startSection;
    private String endSection;
    private String startDate;
    private BigDecimal materialRatio;
    private Integer materialPeriod;
    private BigDecimal startRatio;
    private Integer startPeriod;
    private BigDecimal retentionRatio;
    private Integer retentionPeriod;
    private String note;
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Basic(Integer id, String name, Integer companyid, BigDecimal length, String startSection, String endSection, String startDate, BigDecimal materialRatio, Integer materialPeriod, BigDecimal startRatio, Integer startPeriod, BigDecimal retentionRatio, Integer retentionPeriod, String note) {
        this.id = id;
        this.name = name;
        this.companyid = companyid;
        this.length = length;
        this.startSection = startSection;
        this.endSection = endSection;
        this.startDate = startDate;
        this.materialRatio = materialRatio;
        this.materialPeriod = materialPeriod;
        this.startRatio = startRatio;
        this.startPeriod = startPeriod;
        this.retentionRatio = retentionRatio;
        this.retentionPeriod = retentionPeriod;
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCompanyid() {
        return companyid;
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

    public BigDecimal getMaterialRatio() {
        return materialRatio;
    }

    public Integer getMaterialPeriod() {
        return materialPeriod;
    }

    public BigDecimal getStartRatio() {
        return startRatio;
    }

    public Integer getStartPeriod() {
        return startPeriod;
    }

    public BigDecimal getRetentionRatio() {
        return retentionRatio;
    }

    public Integer getRetentionPeriod() {
        return retentionPeriod;
    }

    public String getNote() {
        return note;
    }

    public Basic() {
        super();
        // TODO Auto-generated constructor stub
    }


}
