package com.myweb.sell.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * sell_slip实体类
 */
public class SellSlip {

    /**
     * 销售单号
     */
    private String serialNumber;

    /**
     * 经办人编号
     */
    private Long operatorId;

    /**
     * 销售日期
     */
    private Date saleDate;

    /**
     * 销售数量
     */
    private Integer saleNumber;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(Integer saleNumber) {
        this.saleNumber = saleNumber;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
