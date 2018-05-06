package com.myweb.phone.model;

import java.math.BigDecimal;
import java.util.Date;

public class RecycleLog {

    /**
     * 主键
     */
    private Long id;

    /**
     * 回收流水号
     */
    private String serialNumber;

    /**
     * 手机imei
     */
    private String imei;

    /**
     * 回收日期
     */
    private Date recycleDate;

    /**
     * 品牌
     */
    private String brandName;

    /**
     * 型号
     */
    private String modelName;

    /**
     * 颜色
     */
    private String colorName;

    /**
     * 内存
     */
    private String memoryName;

    /**
     * 回收价
     */
    private BigDecimal recyclePrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Date getRecycleDate() {
        return recycleDate;
    }

    public void setRecycleDate(Date recycleDate) {
        this.recycleDate = recycleDate;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getMemoryName() {
        return memoryName;
    }

    public void setMemoryName(String memoryName) {
        this.memoryName = memoryName;
    }

    public BigDecimal getRecyclePrice() {
        return recyclePrice;
    }

    public void setRecyclePrice(BigDecimal recyclePrice) {
        this.recyclePrice = recyclePrice;
    }
}
