package com.myweb.finance.model;

import java.math.BigDecimal;

/**
 * 财务统计返回页面dto
 */
public class FinanceDto {

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
    private BigDecimal recoveryPrice;

    /**
     * 维修价
     */
    private BigDecimal amountComplained;

    /**
     * 售价
     */
    private BigDecimal transactionPrice;

    /**
     * 利润
     */
    private BigDecimal profit;

    /**
     * 销量
     */
    private Integer saleNumber;

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

    public BigDecimal getRecoveryPrice() {
        return recoveryPrice;
    }

    public void setRecoveryPrice(BigDecimal recoveryPrice) {
        this.recoveryPrice = recoveryPrice;
    }

    public BigDecimal getAmountComplained() {
        return amountComplained;
    }

    public void setAmountComplained(BigDecimal amountComplained) {
        this.amountComplained = amountComplained;
    }

    public BigDecimal getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(BigDecimal transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public Integer getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(Integer saleNumber) {
        this.saleNumber = saleNumber;
    }
}
