package com.myweb.finance.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.myweb.core.PageResult;
import com.myweb.finance.dao.FinanceMapper;
import com.myweb.finance.model.FinanceArgs;
import com.myweb.finance.model.FinanceDetailDto;
import com.myweb.finance.model.FinanceDto;
import com.myweb.finance.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 */
@Service("financeService")
public class FinanceServiceImpl implements FinanceService{

    @Autowired
    private FinanceMapper financeMapper;


    @Override
    public PageResult<FinanceDto> listFinanceDto(FinanceArgs financeArgs) {
        Page<Object> page = PageHelper.startPage(financeArgs.getPage(),financeArgs.getLimit());
        List<FinanceDto> financeDtos = financeMapper.listFinanceDto(financeArgs);
        PageResult<FinanceDto> pageResult = new PageResult<>();
        pageResult.setData(financeDtos);
        pageResult.setCount(page.getTotal());
        return pageResult;
    }

    @Override
    public PageResult<FinanceDetailDto> listFinanceDetailDto(FinanceArgs args) {
        Page<Object> page = PageHelper.startPage(args.getPage(),args.getLimit());
        List<FinanceDetailDto> financeDetailDtos = financeMapper.listFinanceDetailDto(args);
        PageResult<FinanceDetailDto> pageResult = new PageResult<>();
        pageResult.setData(financeDetailDtos);
        pageResult.setCount(page.getTotal());
        return pageResult;
    }
}
