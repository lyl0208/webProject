package com.myweb.finance.service;

import com.myweb.core.PageResult;
import com.myweb.finance.model.FinanceArgs;
import com.myweb.finance.model.FinanceDetailDto;
import com.myweb.finance.model.FinanceDto;

import java.math.BigDecimal;


/**
 * 财务相关service
 */
public interface FinanceService {

    /**
     * 查询财务报表
     * @param financeArgs
     * @return
     */
    PageResult<FinanceDto> listFinanceDto(FinanceArgs financeArgs);

    /**
     * 查询具体品牌下的手机财务报表
     * @param args
     * @return
     */
    PageResult<FinanceDetailDto> listFinanceDetailDto(FinanceArgs args);

    BigDecimal getTotalProfit(FinanceArgs args);

}
