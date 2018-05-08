package com.myweb.stock.dao;

import com.myweb.phone.model.PhoneModel;
import com.myweb.picture.model.FrontPictureArgs;
import com.myweb.picture.model.FrontPictureDto;
import com.myweb.stock.model.StockArgs;
import com.myweb.stock.model.StockDto;
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

    List<StockDto> listStockDto(StockArgs stockArgs);

    List<FrontPictureDto> listFrontPictureDto(FrontPictureArgs args);

    @Transactional(rollbackFor = Exception.class)
    int addFrontPicture(PhoneModel phoneModel);


}
