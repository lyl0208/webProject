package com.myweb.history.controller;

import com.myweb.core.BaseController;
import com.myweb.core.PageResult;
import com.myweb.history.model.RecycleHistoryArgs;
import com.myweb.history.model.SellHistoryArgs;
import com.myweb.history.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/history")
public class HistoryController extends BaseController {

    @Autowired
    private HistoryService historyService;

    @PostMapping("/sellHistory")
    public PageResult sellHistory(SellHistoryArgs args) {
        return historyService.sellHistory(args);
    }

    @PostMapping("/recycleHistory")
    public PageResult recycleHistory(RecycleHistoryArgs args){return historyService.recycleHistory(args);}

}
