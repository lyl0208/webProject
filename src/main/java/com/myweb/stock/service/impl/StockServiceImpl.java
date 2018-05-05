package com.myweb.stock.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.myweb.core.PageResult;
import com.myweb.stock.dao.StockMapper;
import com.myweb.stock.model.StockArgs;
import com.myweb.stock.model.StockDto;
import com.myweb.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Override
    public PageResult listStockDto(StockArgs stockArgs) {
        Page<Object> page = PageHelper.startPage(stockArgs.getPage(),stockArgs.getLimit());
        List<StockDto> stockDtos = stockMapper.listStockDto(stockArgs);
        PageResult<StockDto> result = new PageResult<>();
        result.setData(stockDtos);
        result.setCount(page.getTotal());
        return result;
    }
}
