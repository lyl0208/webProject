package com.myweb.finance.controller;

import com.myweb.core.PageResult;
import com.myweb.finance.model.FinanceArgs;
import com.myweb.finance.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 财务统计controller
 */
@RestController
@RequestMapping("/api/finance")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    @PostMapping("/list")
    public PageResult list(FinanceArgs args) {
        return financeService.listFinanceDto(args);
    }

    @PostMapping("/listDetail")
    public PageResult listDetail(FinanceArgs args) {
        return financeService.listFinanceDetailDto(args);
    }

}
