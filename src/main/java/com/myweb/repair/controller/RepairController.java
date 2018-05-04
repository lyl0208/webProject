package com.myweb.repair.controller;

import com.myweb.core.PageResult;
import com.myweb.core.ResultMap;
import com.myweb.repair.model.Repair;
import com.myweb.repair.model.RepairArgs;
import com.myweb.repair.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/repair")
public class RepairController {

    @Autowired
    private RepairService repairService;


    @PostMapping("/list")
    public PageResult list(RepairArgs args) {
        return repairService.listPhoneRepairDto(args);
    }

    @PostMapping("/save")
    public ResultMap save(Repair repair) {
        if (repairService.save(repair)) {
            return ResultMap.ok("处理成功，手机已进入维修状态");
        } else {
            return ResultMap.error("处理失败，请稍后重试");
        }
    }

}
