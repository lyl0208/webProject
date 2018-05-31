package com.myweb.sell.controller;

import com.myweb.core.ResultMap;
import com.myweb.core.exception.BusinessException;
import com.myweb.core.utils.JSONUtil;
import com.myweb.sell.model.SellArgs;
import com.myweb.sell.model.SellLog;
import com.myweb.sell.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/sell")
public class SellController {

    @Autowired
    private SellService sellService;



    @PostMapping("/sell")
    public ResultMap sell(String param) throws BusinessException {
        List<SellArgs> sellArgs = JSONUtil.parseArray(param,SellArgs.class);
        return sellService.sell(sellArgs);
    }

    @PostMapping("/getSellLog")
    public List<SellLog> getSellLog(String serialNumber) {
        return sellService.getSellLog(serialNumber);
    }


}
