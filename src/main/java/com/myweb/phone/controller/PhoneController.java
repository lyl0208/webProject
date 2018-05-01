package com.myweb.phone.controller;

import com.myweb.core.BaseController;
import com.myweb.core.PageResult;
import com.myweb.core.ResultMap;
import com.myweb.phone.model.PhoneArgs;
import com.myweb.phone.model.PhoneInfo;
import com.myweb.phone.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/phone")
public class PhoneController extends BaseController {

    @Autowired
    private PhoneService phoneService;

    @PostMapping("/listPhone")
    public PageResult listPhone(PhoneArgs args) {
        return phoneService.listPhone(args);
    }

    @PostMapping("/addPhone")
    private ResultMap addPhone(PhoneInfo phoneInfo) {
        if (phoneService.addPhone(phoneInfo)) {
            return ResultMap.ok("添加成功");
        } else {
            return ResultMap.error("添加失败");
        }
    }

    @PostMapping("/editPhone")
    private ResultMap editPhone(PhoneInfo phoneInfo) {
        if (phoneService.addPhone(phoneInfo)) {
            return ResultMap.ok("修改成功");
        } else {
            return ResultMap.error("修改失败");
        }
    }

}
