package com.myweb.stock.controller;

import com.myweb.core.BaseController;
import com.myweb.core.PageResult;
import com.myweb.stock.model.StockArgs;
import com.myweb.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stock")
public class StockController extends BaseController {

    @Autowired
    private StockService stockService;

    @PostMapping("/list")
    public PageResult list(StockArgs stockArgs) {
        return stockService.listStockDto(stockArgs);
    }


}
