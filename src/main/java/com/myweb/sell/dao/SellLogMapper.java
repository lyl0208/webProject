package com.myweb.sell.dao;

import com.myweb.history.model.SellHistoryArgs;
import com.myweb.sell.model.SellLog;

import java.util.List;

public interface SellLogMapper {

    int save(SellLog sellLog);

    List<SellLog> listSellLog(SellHistoryArgs args);

}
