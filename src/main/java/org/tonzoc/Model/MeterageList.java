package org.tonzoc.Model;

import org.tonzoc.Common.Utility;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class MeterageList {

    private  long id;
    private  String listNo;
    private  String itemName;
    private  String unit;
    private  BigDecimal total;
    private  BigDecimal price;
    private  BigDecimal balance;
    private  String parentId;
    private  String chapter;
    private  Timestamp createdAt;
    private  Timestamp updatedAt;

    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    private List children;

    public MeterageList(long id, String listNo, String itemName, String unit, BigDecimal total, BigDecimal price, BigDecimal balance, String parentId, String chapter, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.listNo = listNo;
        this.itemName = itemName;
        this.unit = unit;
        this.total = total;
        this.price = price;
        this.balance = balance;
        this.parentId = parentId;
        this.chapter = chapter;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public String getListNo() {
        return listNo;
    }

    public String getItemName() {
        return itemName;
    }

    public String getUnit() {
        return unit;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getParentId() {
        return parentId;
    }

    public String getChapter() {
        return chapter;
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

    public MeterageList() {
        super();
        // TODO Auto-generated constructor stub
    }


}