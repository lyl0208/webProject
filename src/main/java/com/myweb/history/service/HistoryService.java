package com.myweb.history.service;

import com.myweb.core.PageResult;
import com.myweb.history.model.RecycleHistoryArgs;
import com.myweb.history.model.SellHistoryArgs;

public interface HistoryService {


    PageResult sellHistory(SellHistoryArgs args);

    PageResult recycleHistory(RecycleHistoryArgs args);

}
