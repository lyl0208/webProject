package com.myweb.repair.dao;

import com.myweb.repair.model.PhoneRepairDto;
import com.myweb.repair.model.Repair;
import com.myweb.repair.model.RepairArgs;

import java.util.List;

public interface RepairMapper {

    List<PhoneRepairDto> listPhoneRepairDto(RepairArgs args);

    int save(Repair repair);

    int countByOrderNumber(String orderNumber);

}
