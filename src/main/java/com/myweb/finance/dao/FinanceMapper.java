package com.myweb.finance.dao;

import com.myweb.finance.model.FinanceArgs;
import com.myweb.finance.model.FinanceDetailDto;
import com.myweb.finance.model.FinanceDto;

import java.util.List;

/**
 * 财务相关mapper
 */
public interface FinanceMapper {

    List<FinanceDto> listFinanceDto(FinanceArgs args);


    List<FinanceDetailDto> listFinanceDetailDto(FinanceArgs args);

}
