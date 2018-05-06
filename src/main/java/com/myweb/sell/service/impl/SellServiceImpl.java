package com.myweb.sell.service.impl;

import com.myweb.core.ResultMap;
import com.myweb.core.exception.BusinessException;
import com.myweb.core.utils.SecurityUtil;
import com.myweb.phone.dao.PhoneMapper;
import com.myweb.phone.model.PhoneInfo;
import com.myweb.sell.dao.SellLogMapper;
import com.myweb.sell.dao.SellSlipMapper;
import com.myweb.sell.dao.SellingDetailMapper;
import com.myweb.sell.model.SellArgs;
import com.myweb.sell.model.SellLog;
import com.myweb.sell.model.SellSlip;
import com.myweb.sell.model.SellingDetail;
import com.myweb.sell.service.SellService;
import com.myweb.stock.dao.StockMapper;
import com.myweb.stock.model.StockArgs;
import com.myweb.stock.model.StockInfo;
import com.myweb.system.model.User;
import com.myweb.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class SellServiceImpl implements SellService {

    @Autowired
    private SellingDetailMapper sellingDetailMapper;

    @Autowired
    private SellSlipMapper sellSlipMapper;

    @Autowired
    private PhoneMapper phoneMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private SellLogMapper sellLogMapper;

    @Autowired
    private UserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultMap sell(List<SellArgs> sellArgsList) throws BusinessException {
        BigDecimal totalPrice = BigDecimal.ZERO;
        String sellSlipSerialNumber = genSellSlipSerialNumber();
        User currentUser = userService.findUserById(SecurityUtil.getCurrentUserId());


        //保存销售单
        SellSlip sellSlip = new SellSlip();
        sellSlip.setOperatorId(SecurityUtil.getCurrentUserId());
        sellSlip.setSerialNumber(sellSlipSerialNumber);
        sellSlip.setSaleDate(new Date());
        sellSlip.setSaleNumber(sellArgsList.size());
        for (SellArgs sellArgs : sellArgsList) {
            //累加交易价
            totalPrice = totalPrice.add(sellArgs.getTransactionPrice());
        }
        sellSlip.setTotalPrice(totalPrice);
        sellSlipMapper.save(sellSlip);

        for (SellArgs sellArgs : sellArgsList) {
            PhoneInfo phoneInfo = phoneMapper.findPhoneByImei(sellArgs.getImei());
            if (phoneInfo == null) {
                throw new BusinessException("IMEI:"+sellArgs.getImei()+" 有误，查询不到该IMEI的手机");
            }



            //检查手机状态是否已上架
            if (!"3".equals(phoneInfo.getState())) {
                throw new BusinessException("imei:" + sellArgs.getImei() + " 该手机不是上架状态！");
            }

            //设置手机状态已下架
            phoneInfo.setState("4");
            phoneMapper.editPhone(phoneInfo);

            //检查更新库存
            StockArgs args = new StockArgs();
            args.setBrandId(phoneInfo.getBrandId());
            args.setModelId(phoneInfo.getModelId());
            args.setMemoryId(phoneInfo.getMemoryId());
            args.setColorId(phoneInfo.getColorId());
            List<StockInfo> stocks = stockMapper.findStock(args);
            if (stocks.size() > 0) {

                if (stocks.get(0).getNumber() <= 0) {
                    throw new BusinessException("imei:"+sellArgs.getImei()+"  库存不足");
                }

                stockMapper.descNumber(stocks.get(0).getStockId());
            } else {
                throw new BusinessException("imei:"+sellArgs.getImei()+"  库存不足");
            }

            //检查售价是否大于回收价+维修价
            BigDecimal cost = phoneMapper.getCostByImei(phoneInfo.getIMEI());
            if (sellArgs.getTransactionPrice().compareTo(cost) < 0) {
                //售价小于 回收价+维修价格
                throw new BusinessException("imei:"+sellArgs.getImei()+" 售价：" + sellArgs.getTransactionPrice() + " 小于成本:" + cost);
            }


            //保存销售明细
            String sellDetailSerialNumber = genSellDetailSerialNumber();
            SellingDetail sellingDetail = new SellingDetail();
            sellingDetail.setSellingId(sellDetailSerialNumber);
            sellingDetail.setSerialNumber(sellSlipSerialNumber);
            sellingDetail.setIMEI(sellArgs.getImei());
            sellingDetail.setTransactionPrice(sellArgs.getTransactionPrice());
            sellingDetailMapper.save(sellingDetail);

            //保存销售记录
            SellLog sellLog = new SellLog();
            sellLog.setBrandName(phoneInfo.getBrandName());
            sellLog.setColorName(phoneInfo.getColorName());
            sellLog.setImei(phoneInfo.getIMEI());
            sellLog.setMemoryName(phoneInfo.getMemoryName());
            sellLog.setModelName(phoneInfo.getModelName());
            sellLog.setOperatorName(currentUser.getRealName());
            sellLog.setSaleDate(new Date());
            sellLog.setSellingId(sellDetailSerialNumber);
            sellLog.setTransactionPrice(sellArgs.getTransactionPrice());
            sellLogMapper.save(sellLog);
        }

        return ResultMap.ok("处理成功");
    }

    /**
     * 生成唯一随机的销售单号
     */
    private String genSellSlipSerialNumber() {
        Random r = new Random();
        String str = "S";
        for (int i = 1; i < 20; i++) {
            Integer x = r.nextInt(10);
            str += x.toString();
        }
        if (sellSlipMapper.countBySerialNumber(str) > 0) {
            return genSellSlipSerialNumber();
        }
        return str;
    }

    /**
     * 生成唯一随机的销售明细单号
     */
    private String genSellDetailSerialNumber() {
        Random r = new Random();
        String str = "D";
        for (int i = 1; i < 20; i++) {
            Integer x = r.nextInt(10);
            str += x.toString();
        }
        if (sellingDetailMapper.countBySellingId(str) > 0) {
            return genSellSlipSerialNumber();
        }
        return str;
    }

}
