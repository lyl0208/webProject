package com.myweb.phone.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.myweb.core.PageResult;
import com.myweb.phone.dao.PhoneMapper;
import com.myweb.phone.model.*;
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
        args.setBrandId(phone.getBrandId());
        args.setColorId(phone.getColorId());
        args.setMemoryId(phone.getMemoryId());
        args.setModelId(phone.getModelId());
        List<StockInfo> stockInfos = stockMapper.findStock(args);
        if (stockInfos.size() > 0) {
            if (stockMapper.addNumber(stockInfos.get(0).getStockId()) <= 0) {
                return false;
            }
        } else {
            StockInfo stockInfo = new StockInfo();
            stockInfo.setBrandId(phone.getBrandId());
            stockInfo.setColorId(phone.getColorId());
            stockInfo.setModelId(phone.getModelId());
            stockInfo.setMemoryId(phone.getMemoryId());
            if (stockMapper.save(stockInfo) <= 0) {
                return false;
            }
        }
        return phoneMapper.addPhone(phone) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editPhone(PhoneInfo phoneInfo) {
        PhoneInfo oldPhone = phoneMapper.findPhoneById(phoneInfo.getPhoneId());
        if (!oldPhone.getBrandId().equals(phoneInfo.getBrandId())
                || !oldPhone.getModelId().equals(phoneInfo.getModelId())
                || !oldPhone.getColorId().equals(phoneInfo.getColorId())
                || !oldPhone.getMemoryId().equals(phoneInfo.getMemoryId())) {
            //手机品牌被修改，需要调整库存
            //减少修改前库存的数量
            StockArgs args = new StockArgs();
            args.setBrandId(oldPhone.getBrandId());
            args.setModelId(oldPhone.getModelId());
            args.setMemoryId(oldPhone.getMemoryId());
            args.setColorId(oldPhone.getColorId());
            List<StockInfo> stocks = stockMapper.findStock(args);
            if (stocks.size() > 0) {
                if (stockMapper.descNumber(stocks.get(0).getStockId()) <= 0) {
                    return false;
                }
            }

            //增加修改后库存的数量
            args.setBrandId(phoneInfo.getBrandId());
            args.setColorId(phoneInfo.getColorId());
            args.setMemoryId(phoneInfo.getMemoryId());
            args.setModelId(phoneInfo.getModelId());
            stocks = stockMapper.findStock(args);
            if (stocks.size() > 0) {
                if (stockMapper.addNumber(stocks.get(0).getStockId()) <= 0) {
                    return false;
                }
            } else {
                StockInfo stockInfo = new StockInfo();
                stockInfo.setBrandId(phoneInfo.getBrandId());
                stockInfo.setColorId(phoneInfo.getColorId());
                stockInfo.setModelId(phoneInfo.getModelId());
                stockInfo.setMemoryId(phoneInfo.getMemoryId());
                if (stockMapper.save(stockInfo) <= 0) {
                    return false;
                }
            }
        }
        //修改手机信息
        return phoneMapper.editPhone(phoneInfo) > 0;
    }

    @Override
    public List<PhoneBrand> getBrands() {
        return phoneMapper.getBrands();
    }

    @Override
    public boolean addBrand(PhoneBrand phoneBrand) {
        return phoneMapper.addBrand(phoneBrand) > 0;
    }

    @Override
    public List<PhoneModel> getModels(Long brandId) {
        return phoneMapper.getModels(brandId);
    }

    @Override
    public boolean addModel(PhoneModel phoneModel) {
        return phoneMapper.addModel(phoneModel) > 0;
    }

    @Override
    public List<PhoneColor> getColors(Long modelId) {
        return phoneMapper.getColors(modelId);
    }

    @Override
    public boolean addColor(PhoneColor phoneColor) {
        return phoneMapper.addColor(phoneColor) > 0;
    }

    @Override
    public List<PhoneMemory> getMemorys(Long modelId) {
        return phoneMapper.getMemorys(modelId);
    }

    @Override
    public boolean addMemory(PhoneMemory phoneMemory) {
        return phoneMapper.addMemory(phoneMemory) > 0;
    }
}
