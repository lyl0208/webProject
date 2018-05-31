package com.myweb.sell.service;

import com.myweb.core.ResultMap;
import com.myweb.core.exception.BusinessException;
import com.myweb.sell.model.SellArgs;
import com.myweb.sell.model.SellLog;

import java.util.List;

public interface SellService {


    ResultMap sell(List<SellArgs> sellArgsList) throws BusinessException;

    List<SellLog> getSellLog(String serialNumber);



}
