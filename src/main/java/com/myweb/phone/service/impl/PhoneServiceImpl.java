package com.myweb.phone.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.myweb.core.PageResult;
import com.myweb.phone.dao.PhoneMapper;
import com.myweb.phone.model.PhoneArgs;
import com.myweb.phone.model.PhoneInfo;
import com.myweb.phone.service.PhoneService;
import com.myweb.stock.dao.StockMapper;
import com.myweb.stock.model.StockArgs;
import com.myweb.stock.model.StockInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("phoneService")
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneMapper phoneMapper;

    @Autowired
    private StockMapper stockMapper;

    @Override
    public PageResult<PhoneInfo> listPhone(PhoneArgs args) {
        //开启分页
        Page<Object> startPage = PageHelper.startPage(args.getPage(), args.getLimit());
        //获取手机档案信息列表
        List<PhoneInfo> phones = phoneMapper.listPhone(args);
        PageResult<PhoneInfo> result = new PageResult<>();
        result.setData(phones);
        result.setCount(startPage.getTotal());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addPhone(PhoneInfo phone) {
        StockArgs args = new StockArgs();
        args.setBrand(phone.getBrand());
        args.setColor(phone.getColor());
        args.setMemory(phone.getMemory());
        args.setModel(phone.getModel());
        List<StockInfo> stockInfos = stockMapper.findStock(args);
        if (stockInfos.size() > 0) {
            if(stockMapper.addNumber(stockInfos.get(0).getBrandId()) <= 0 ) {
                return false;
            }
        } else {
            StockInfo stockInfo = new StockInfo();
            stockInfo.setBrand(phone.getBrand());
            stockInfo.setColor(phone.getColor());
            stockInfo.setModel(phone.getModel());
            stockInfo.setMemory(phone.getMemory());
            if (stockMapper.save(stockInfo) <= 0) {
                return false;
            }
        }
        return phoneMapper.addPhone(phone) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editPhone(PhoneInfo phoneInfo) {
        return false;
    }
}
