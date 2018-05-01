package com.myweb.stock.dao;

import com.myweb.stock.model.StockArgs;
import com.myweb.stock.model.StockInfo;

import java.util.List;

public interface StockMapper {

    int save(StockInfo stockInfo);

    List<StockInfo> findStock(StockArgs stockArgs);

    int addNumber(Long brandId);

}
