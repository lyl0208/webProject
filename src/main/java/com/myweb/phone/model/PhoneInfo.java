package com.myweb.phone.model;

import java.math.BigDecimal;

public class PhoneInfo {


    /**
     * 手机主键
     */
    private Long phoneId;

    /**
     * 手机IMEI
     */
    private String IMEI;

    /**
     * 手机品牌关联ID
     */
    private Long brandId;

    /**
     * 手机品牌名
     */
    private String brandName;

    /**
     * 型号关联ID
     */
    private Long modelId;

    /**
     * 型号名
     */
    private String modelName;

    /**
     * 颜色关联Id
     */
    private Long colorId;

    /**
     * 颜色名
     */
    private String colorName;

    /**
     * 内存关联Id
     */
    private Long memoryId;

    /**
     * 内存名
     */
    private String memoryName;

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

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public Long getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(Long memoryId) {
        this.memoryId = memoryId;
    }

    public String getMemoryName() {
        return memoryName;
    }

    public void setMemoryName(String memoryName) {
        this.memoryName = memoryName;
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

    public Long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Long phoneId) {
        this.phoneId = phoneId;
    }
}
