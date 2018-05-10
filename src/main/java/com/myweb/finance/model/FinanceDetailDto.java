package com.myweb.finance.model;

import java.math.BigDecimal;

/**
 * 具体到某个品牌下的所有手机财务报表
 */
public class FinanceDetailDto {

    /**
     * 手机imei
     */
    private String imei;

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

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
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
}
