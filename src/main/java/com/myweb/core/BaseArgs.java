package com.myweb.core;

import com.myweb.core.utils.StringUtil;

/**
 * 请求参数模板
 */
public class BaseArgs {

    private String searchKey;

    private String searchValue;

    /**
     * 当前页数
     */
    private Integer page;

    /**
     * 一页数据条数
     */
    private Integer limit;


    public boolean checkParam(Object... objects) {
        for (Object object : objects) {
            if (object == null || StringUtil.isBlank(object.toString())) {
                return false;
            }
        }
        return true;
    }


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

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
