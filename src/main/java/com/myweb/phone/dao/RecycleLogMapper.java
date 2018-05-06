package com.myweb.phone.dao;

import com.myweb.history.model.RecycleHistoryArgs;
import com.myweb.phone.model.RecycleLog;

import java.util.List;

public interface RecycleLogMapper {

    int countBySerialNumber(String serialNumber);

    List<RecycleLog> listRecycleLog(RecycleHistoryArgs recycleHistoryArgs);

    RecycleLog getRecycleLogByImei(String imei);

    int save(RecycleLog recycleLog);

    int update(RecycleLog recycleLog);

}
