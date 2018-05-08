package com.myweb.history.model;

import com.myweb.core.BaseArgs;

public class SellHistoryArgs extends BaseArgs {

    /**
     * 销售明细编号
     */
    private String sellingId;

    public String getSellingId() {
        return sellingId;
    }

    public void setSellingId(String sellingId) {
        this.sellingId = sellingId;
    }
}
