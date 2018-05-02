package com.myweb.stock.dao;

import com.myweb.stock.model.StockArgs;
import com.myweb.stock.model.StockInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StockMapper {

    @Transactional(rollbackFor = Exception.class)
    int save(StockInfo stockInfo);

    List<StockInfo> findStock(StockArgs stockArgs);

    @Transactional(rollbackFor = Exception.class)
    int addNumber(Long brandId);

    @Transactional(rollbackFor = Exception.class)
    int descNumber(Long brandId);

}
