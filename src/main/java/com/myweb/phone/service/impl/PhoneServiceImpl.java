package com.myweb.phone.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.myweb.core.PageResult;
import com.myweb.core.exception.BusinessException;
import com.myweb.phone.dao.PhoneMapper;
import com.myweb.phone.dao.RecycleLogMapper;
import com.myweb.phone.model.*;
import com.myweb.phone.service.PhoneService;
import com.myweb.stock.dao.StockMapper;
import com.myweb.stock.model.StockArgs;
import com.myweb.stock.model.StockInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service("phoneService")
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneMapper phoneMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private RecycleLogMapper recycleLogMapper;

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
    public boolean addPhone(PhoneInfo phone) throws BusinessException {
        StockArgs args = new StockArgs();
        args.setBrandId(phone.getBrandId());
        args.setColorId(phone.getColorId());
        args.setMemoryId(phone.getMemoryId());
        args.setModelId(phone.getModelId());
        List<StockInfo> stockInfos = stockMapper.findStock(args);
        if (stockInfos.size() > 0) {
            if (stockMapper.addNumber(stockInfos.get(0).getStockId()) <= 0) {
                throw new BusinessException("添加失败");
            }
        } else {
            StockInfo stockInfo = new StockInfo();
            stockInfo.setBrandId(phone.getBrandId());
            stockInfo.setColorId(phone.getColorId());
            stockInfo.setModelId(phone.getModelId());
            stockInfo.setMemoryId(phone.getMemoryId());
            if (stockMapper.save(stockInfo) <= 0) {
                throw new BusinessException("添加失败");
            }
        }
        if (phoneMapper.addPhone(phone) <= 0 ) {
            throw new BusinessException("添加失败");
        }

        //保存日志
        PhoneInfo addPhone = phoneMapper.findPhoneByImei(phone.getIMEI());
        RecycleLog recycleLog = new RecycleLog();
        recycleLog.setBrandName(addPhone.getBrandName());
        recycleLog.setColorName(addPhone.getColorName());
        recycleLog.setImei(addPhone.getIMEI());
        recycleLog.setMemoryName(addPhone.getMemoryName());
        recycleLog.setModelName(addPhone.getModelName());
        recycleLog.setRecycleDate(new Date());
        recycleLog.setRecyclePrice(addPhone.getRecoveryPrice());
        recycleLog.setSerialNumber(genRecycleLogSerialNumer());
        recycleLogMapper.save(recycleLog);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editPhone(PhoneInfo phoneInfo) throws BusinessException {
        PhoneInfo oldPhone = phoneMapper.findPhoneById(phoneInfo.getPhoneId());
        RecycleLog recycleLog = recycleLogMapper.getRecycleLogByImei(oldPhone.getIMEI());
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
            if (stocks.size() > 0 && stocks.get(0).getNumber() > 0) {
                stockMapper.descNumber(stocks.get(0).getStockId());
            }

            //增加修改后库存的数量
            args.setBrandId(phoneInfo.getBrandId());
            args.setColorId(phoneInfo.getColorId());
            args.setMemoryId(phoneInfo.getMemoryId());
            args.setModelId(phoneInfo.getModelId());
            stocks = stockMapper.findStock(args);
            if (stocks.size() > 0) {
                stockMapper.addNumber(stocks.get(0).getStockId());
            } else {
                StockInfo stockInfo = new StockInfo();
                stockInfo.setBrandId(phoneInfo.getBrandId());
                stockInfo.setColorId(phoneInfo.getColorId());
                stockInfo.setModelId(phoneInfo.getModelId());
                stockInfo.setMemoryId(phoneInfo.getMemoryId());
                stockMapper.save(stockInfo);
            }
        }
        //修改手机信息
        if (phoneMapper.editPhone(phoneInfo) <= 0) {
            throw new BusinessException("修改失败");
        }
        //由于修改了手机信息，日志也要相应的修改
        PhoneInfo editPhone = phoneMapper.findPhoneById(phoneInfo.getPhoneId());
        recycleLog.setRecyclePrice(editPhone.getRecoveryPrice());
        recycleLog.setModelName(editPhone.getModelName());
        recycleLog.setMemoryName(editPhone.getMemoryName());
        recycleLog.setImei(editPhone.getIMEI());
        recycleLog.setColorName(editPhone.getColorName());
        recycleLog.setBrandName(editPhone.getBrandName());
        recycleLogMapper.update(recycleLog);
        return true;
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

    @Override
    public List<PhoneInfo> listPhoneInfoByModelId(Long modelId) {
        return phoneMapper.listPhoneInfoByModelId(modelId);
    }

    private String genRecycleLogSerialNumer() {
        Random r = new Random();
        String str = "H";
        for (int i = 1; i < 20; i++) {
            Integer x = r.nextInt(10);
            str += x.toString();
        }
        if (recycleLogMapper.countBySerialNumber(str) > 0) {
            return genRecycleLogSerialNumer();
        }
        return str;
    }
}
