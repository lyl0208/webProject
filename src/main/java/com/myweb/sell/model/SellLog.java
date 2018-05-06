package com.myweb.sell.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售日志
 */
public class SellLog {

    /**
     * 主键
     */
    private Long id;

    /**
     * 销售明细编号
     */
    private String sellingId;

    /**
     * 手机编号
     */
    private String imei;

    /**
     * 操作人名字
     */
    private String operatorName;

    /**
     * 销售日期
     */
    private Date saleDate;

    /**
     * 品牌名字
     */
    private String brandName;

    /**
     * 型号名字
     */
    private String modelName;

    /**
     * 颜色名字
     */
    private String colorName;

    /**
     * 内存名
     */
    private String memoryName;

    /**
     * 成交价
     */
    private BigDecimal transactionPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellingId() {
        return sellingId;
    }

    public void setSellingId(String sellingId) {
        this.sellingId = sellingId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
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

    public BigDecimal getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(BigDecimal transactionPrice) {
        this.transactionPrice = transactionPrice;
    }
}
