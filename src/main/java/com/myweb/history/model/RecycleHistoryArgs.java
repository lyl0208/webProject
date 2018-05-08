package com.myweb.history.model;

import com.myweb.core.BaseArgs;

public class RecycleHistoryArgs extends BaseArgs {

    /**
     * 回收单号
     */
    private String serialNumber;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
