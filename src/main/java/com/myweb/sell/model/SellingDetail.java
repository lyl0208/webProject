package com.myweb.sell.model;

import java.math.BigDecimal;

/**
 * selling_detail实体类
 */
public class SellingDetail {

    /**
     * 销售明细编号
     */
    private String sellingId;

    /**
     * 手机imei
     */
    private String IMEI;

    /**
     * 销售单编号
     */
    private String serialNumber;

    /**
     * 手机交易价
     */
    private BigDecimal transactionPrice;

    public String getSellingId() {
        return sellingId;
    }

    public void setSellingId(String sellingId) {
        this.sellingId = sellingId;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(BigDecimal transactionPrice) {
        this.transactionPrice = transactionPrice;
    }
}
