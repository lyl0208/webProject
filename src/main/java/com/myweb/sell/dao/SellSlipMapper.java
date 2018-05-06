package com.myweb.sell.dao;

import com.myweb.sell.model.SellSlip;
import org.springframework.transaction.annotation.Transactional;

public interface SellSlipMapper {

    @Transactional(rollbackFor = Exception.class)
    int save(SellSlip sellSlip);

    int countBySerialNumber(String serialNumber);

}
