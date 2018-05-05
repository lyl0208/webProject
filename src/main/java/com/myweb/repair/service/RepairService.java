package com.myweb.repair.service;

import com.myweb.core.PageResult;
import com.myweb.repair.model.PhoneRepairDto;
import com.myweb.repair.model.Repair;
import com.myweb.repair.model.RepairArgs;

public interface RepairService {

    PageResult<PhoneRepairDto> listPhoneRepairDto(RepairArgs args);

    boolean repairPhone(Repair repair);

    boolean finishRepair(String imei);


}
