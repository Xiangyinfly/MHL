package com.domain;

import java.time.LocalDateTime;
import java.util.Date;

public class Bill {
    private Integer id;
    private String billId;
    private Integer foodId;
    private Integer num;
    private double money;
    private LocalDateTime billDate;
    private Integer tableId;
    private String state;

    public Bill() {
    }

    public Bill(Integer id, String billId, Integer foodId, Integer num, Integer money, LocalDateTime billDate, Integer tableId, String state) {
        this.id = id;
        this.billId = billId;
        this.foodId = foodId;
        this.num = num;
        this.money = money;
        this.billDate = billDate;
        this.tableId = tableId;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDateTime billDate) {
        this.billDate = billDate;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
