package com.myweb.repair.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.myweb.core.PageResult;
import com.myweb.phone.dao.PhoneMapper;
import com.myweb.phone.model.PhoneInfo;
import com.myweb.repair.dao.RepairMapper;
import com.myweb.repair.model.PhoneRepairDto;
import com.myweb.repair.model.Repair;
import com.myweb.repair.model.RepairArgs;
import com.myweb.repair.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service("repairService")
public class RepairServiceImpl implements RepairService {

    @Autowired
    private RepairMapper repairMapper;

    @Autowired
    private PhoneMapper phoneMapper;

    @Override
    public PageResult<PhoneRepairDto> listPhoneRepairDto(RepairArgs args) {
        Page<Object> startPage = PageHelper.startPage(args.getPage(),args.getLimit());
        List<PhoneRepairDto> phoneRepairDtos = repairMapper.listPhoneRepairDto(args);
        PageResult<PhoneRepairDto> pageResult = new PageResult<>();
        pageResult.setData(phoneRepairDtos);
        pageResult.setCount(startPage.getTotal());
        return pageResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean repairPhone(Repair repair) {
        repair.setRepairOrderNumber(genRandomOrderNumber());
        PhoneInfo phoneInfo = phoneMapper.findPhoneByImei(repair.getIMEI());
        if (phoneInfo == null) {
            return false;
        }
        phoneInfo.setState("2");
        return repairMapper.save(repair) > 0 && phoneMapper.editPhone(phoneInfo) > 0;
    }

    @Override
    public boolean finishRepair(String imei) {
        PhoneInfo phoneInfo = phoneMapper.findPhoneByImei(imei);
        if (phoneInfo == null) {
            return false;
        }
        phoneInfo.setState("3");
        return phoneMapper.editPhone(phoneInfo) > 0;
    }


    /**
     * 随机产生10位数不重复的维修单号
     * @return
     */
    private String genRandomOrderNumber() {
        Random r = new Random();
        String str = "";
        for (int i = 0; i < 10; i++) {
            Integer x = r.nextInt(10);
            str += x.toString();
        }
        if (repairMapper.countByOrderNumber(str) > 0) {
            return genRandomOrderNumber();
        }
        return str;
    }
}
