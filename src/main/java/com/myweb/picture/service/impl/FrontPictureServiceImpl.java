package com.myweb.picture.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.myweb.core.PageResult;
import com.myweb.phone.model.PhoneModel;
import com.myweb.picture.model.FrontPictureArgs;
import com.myweb.picture.model.FrontPictureDto;
import com.myweb.picture.service.FrontPictureService;
import com.myweb.stock.dao.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("frontPictureService")
public class FrontPictureServiceImpl implements FrontPictureService {

    @Autowired
    private StockMapper stockMapper;


    @Override
    public PageResult list(FrontPictureArgs args) {
        Page<Object> page = PageHelper.startPage(args.getPage(),args.getLimit());
        List<FrontPictureDto> list = stockMapper.listFrontPictureDto(args);
        PageResult<FrontPictureDto> pageResult = new PageResult<>();
        pageResult.setCount(page.getTotal());
        pageResult.setData(list);
        return pageResult;
    }

    @Override
    public boolean add(PhoneModel phoneModel) {
        return stockMapper.addFrontPicture(phoneModel) > 0;
    }
}
