package com.myweb.system.model;

import com.myweb.core.BaseArgs;

public class UserArgs extends BaseArgs {

    private String searchKey;

    private String searchValue;

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
}
