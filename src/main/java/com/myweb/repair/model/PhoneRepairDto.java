package com.myweb.repair.model;

import java.math.BigDecimal;

/**
 * 维修情况网页展示
 */
public class PhoneRepairDto {

    /**
     * 手机编号
     */
    private String IMEI;

    /**
     * 手机状态 1：待翻新 2：翻新中 3：已上架 4：已下架'
     */
    private Integer state;

    /**
     * 新旧程度
     */
    private String degree;

    /**
     * 损坏部位
     */
    private String damagedPart;

    /**
     * 维修项目
     */
    private String maintenanceProject;

    /**
     * 投入金额
     */
    private BigDecimal amountComplained;

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDamagedPart() {
        return damagedPart;
    }

    public void setDamagedPart(String damagedPart) {
        this.damagedPart = damagedPart;
    }

    public String getMaintenanceProject() {
        return maintenanceProject;
    }

    public void setMaintenanceProject(String maintenanceProject) {
        this.maintenanceProject = maintenanceProject;
    }

    public BigDecimal getAmountComplained() {
        return amountComplained;
    }

    public void setAmountComplained(BigDecimal amountComplained) {
        this.amountComplained = amountComplained;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
