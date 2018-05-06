package com.myweb.sell.model;

import java.math.BigDecimal;

public class SellArgs {

    /**
     * imei
     */
    private String imei;

    /**
     * 销售价格
     */
    private BigDecimal transactionPrice;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public BigDecimal getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(BigDecimal transactionPrice) {
        this.transactionPrice = transactionPrice;
    }
}
