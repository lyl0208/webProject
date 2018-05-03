package com.myweb.repair.model;

import com.myweb.core.BaseArgs;

public class RepairArgs extends BaseArgs{

    /**
     * 手机状态
     */
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
