package com.myweb.stock.service;

import com.myweb.core.PageResult;
import com.myweb.stock.model.StockArgs;

public interface StockService {


    PageResult listStockDto(StockArgs stockArgs);

}
