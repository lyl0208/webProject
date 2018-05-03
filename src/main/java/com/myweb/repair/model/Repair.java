package com.myweb.repair.model;

import java.math.BigDecimal;

public class Repair {

    /**
     * 维修单号
     */
    private String repairOrderNumber;

    /**
     * 手机IMEI编号
     */
    private String IMEI;

    /**
     * 维修项目
     */
    private String maintenanceProject;

    /**
     * 投入金额
     */
    private BigDecimal amountComplained;

    public String getRepairOrderNumber() {
        return repairOrderNumber;
    }

    public void setRepairOrderNumber(String repairOrderNumber) {
        this.repairOrderNumber = repairOrderNumber;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
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
}
