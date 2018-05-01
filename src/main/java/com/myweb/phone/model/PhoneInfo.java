package com.myweb.phone.model;

import java.math.BigDecimal;

public class PhoneInfo {


    /**
     * 手机IMEI
     */
    private String IMEI;

    /**
     * 手机品牌
     */
    private String brand;

    /**
     * 型号
     */
    private String model;

    /**
     * 颜色
     */
    private String color;

    /**
     * 内存
     */
    private String memory;

    /**
     * 损坏程度
     */
    private String degree;

    /**
     * 是否在保
     */
    private String protection;

    /**
     * 损坏部位
     */
    private String damagedPart;

    /**
     * 状态
     */
    private String state;

    /**
     * 回收价
     */
    private BigDecimal recoveryPrice;

    /**
     * 参考销售价
     */
    private BigDecimal referenceSellingPrice;

    /**
     * 销售明细单号
     */
    private String sellingId;



    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getProtection() {
        return protection;
    }

    public void setProtection(String protection) {
        this.protection = protection;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDamagedPart() {
        return damagedPart;
    }

    public void setDamagedPart(String damagedPart) {
        this.damagedPart = damagedPart;
    }

    public BigDecimal getRecoveryPrice() {
        return recoveryPrice;
    }

    public void setRecoveryPrice(BigDecimal recoveryPrice) {
        this.recoveryPrice = recoveryPrice;
    }

    public BigDecimal getReferenceSellingPrice() {
        return referenceSellingPrice;
    }

    public void setReferenceSellingPrice(BigDecimal referenceSellingPrice) {
        this.referenceSellingPrice = referenceSellingPrice;
    }

    public String getSellingId() {
        return sellingId;
    }

    public void setSellingId(String sellingId) {
        this.sellingId = sellingId;
    }
}
