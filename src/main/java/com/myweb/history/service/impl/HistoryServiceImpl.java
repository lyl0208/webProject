package com.myweb.history.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.myweb.core.PageResult;
import com.myweb.history.model.RecycleHistoryArgs;
import com.myweb.history.model.SellHistoryArgs;
import com.myweb.history.service.HistoryService;
import com.myweb.phone.dao.RecycleLogMapper;
import com.myweb.phone.model.RecycleLog;
import com.myweb.sell.dao.SellLogMapper;
import com.myweb.sell.model.SellLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("historyService")
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private SellLogMapper sellLogMapper;

    @Autowired
    private RecycleLogMapper recycleLogMapper;

    @Override
    public PageResult sellHistory(SellHistoryArgs args) {
        Page<Object> page = PageHelper.startPage(args.getPage(),args.getLimit());
        List<SellLog> sellLogs = sellLogMapper.listSellLog(args);
        PageResult<SellLog> pageResult = new PageResult<>();
        pageResult.setData(sellLogs);
        pageResult.setCount(page.getTotal());
        return pageResult;
    }

    @Override
    public PageResult recycleHistory(RecycleHistoryArgs args) {
        Page<Object> page = PageHelper.startPage(args.getPage(),args.getLimit());
        List<RecycleLog> recycleLogs = recycleLogMapper.listRecycleLog(args);
        PageResult<RecycleLog> pageResult = new PageResult<>();
        pageResult.setData(recycleLogs);
        pageResult.setCount(page.getTotal());
        return pageResult;
    }
}

