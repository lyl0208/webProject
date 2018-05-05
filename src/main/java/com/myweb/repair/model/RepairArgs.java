package com.myweb.repair.model;

import com.myweb.core.BaseArgs;

public class RepairArgs extends BaseArgs{

    /**
     * 手机状态
     */
    private Integer state;

    /**
     * 维修单号
     */
    private String repairOrderNumber;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRepairOrderNumber() {
        return repairOrderNumber;
    }

    public void setRepairOrderNumber(String repairOrderNumber) {
        this.repairOrderNumber = repairOrderNumber;
    }
}
