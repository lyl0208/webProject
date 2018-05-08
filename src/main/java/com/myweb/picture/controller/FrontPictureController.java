package com.myweb.picture.controller;

import com.myweb.core.PageResult;
import com.myweb.core.ResultMap;
import com.myweb.phone.model.PhoneModel;
import com.myweb.picture.model.FrontPictureArgs;
import com.myweb.picture.service.FrontPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台图片展示管理controller
 */
@RestController
@RequestMapping("/api/frontPicture")
public class FrontPictureController {

    @Autowired
    private FrontPictureService frontPictureService;

    @PostMapping("/list")
    public PageResult list(FrontPictureArgs args) {
        return frontPictureService.list(args);
    }

    @PostMapping("/add")
    public ResultMap add(PhoneModel phoneModel) {
        if (frontPictureService.add(phoneModel)) {
            return ResultMap.ok("处理成功");
        } else {
            return ResultMap.error("处理失败");
        }
    }


}
