package com.myweb.repair.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.myweb.core.PageResult;
import com.myweb.repair.dao.RepairMapper;
import com.myweb.repair.model.PhoneRepairDto;
import com.myweb.repair.model.Repair;
import com.myweb.repair.model.RepairArgs;
import com.myweb.repair.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service("repairService")
public class RepairServiceImpl implements RepairService {

    @Autowired
    private RepairMapper repairMapper;

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
    public boolean save(Repair repair) {
        repair.setRepairOrderNumber(genRandomOrderNumber());
        return repairMapper.save(repair) > 0;
    }


    /**
     * 随机产生10位数不重复的维修单号
     * @return
     */
    private String genRandomOrderNumber() {
        Random r = new Random();
        String str = "";
        for (int i = 0; i < 10; i++) {
            Integer x = r.nextInt();
            str += x.toString();
        }
        if (repairMapper.countByOrderNumber(str) > 0) {
            return genRandomOrderNumber();
        }
        return str;
    }
}
