package com.myweb.phone.controller;

import com.myweb.core.BaseController;
import com.myweb.core.PageResult;
import com.myweb.core.ResultMap;
import com.myweb.core.exception.BusinessException;
import com.myweb.phone.model.*;
import com.myweb.phone.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
    private ResultMap addPhone(PhoneInfo phoneInfo) throws BusinessException {
        if (phoneService.addPhone(phoneInfo)) {
            return ResultMap.ok("添加成功");
        } else {
            return ResultMap.error("添加失败");
        }
    }

    @PostMapping("/editPhone")
    private ResultMap editPhone(PhoneInfo phoneInfo) throws BusinessException {
        if (phoneService.editPhone(phoneInfo)) {
            return ResultMap.ok("修改成功");
        } else {
            return ResultMap.error("修改失败");
        }
    }

    @PostMapping("/getBrands")
    private List<PhoneBrand> getBrands() {
        return phoneService.getBrands();
    }

    @PostMapping("/getModels")
    private List<PhoneModel> getModels(Long brandId) {
        return phoneService.getModels(brandId);
    }

    @PostMapping("/getColors")
    private List<PhoneColor> getColors(Long modelId) {
        return phoneService.getColors(modelId);
    }

    @PostMapping("/getMemorys")
    private List<PhoneMemory> getMemorys(Long modelId) {
        return phoneService.getMemorys(modelId);
    }


    @PostMapping("/addBrand")
    private ResultMap addBrand(PhoneBrand phoneBrand) {
        if (phoneService.addBrand(phoneBrand)) {
            return ResultMap.ok("添加成功").put("brand",phoneBrand);
        } else {
            return ResultMap.error("添加失败");
        }
    }

    @PostMapping("/addModel")
    private ResultMap addModel(PhoneModel phoneModel) {
        if (phoneService.addModel(phoneModel)) {
            return ResultMap.ok("添加成功").put("model",phoneModel);
        } else {
            return ResultMap.error("添加失败");
        }
    }

    @PostMapping("/addColor")
    private ResultMap addColor(PhoneColor phoneColor) {
        if (phoneService.addColor(phoneColor)) {
            return ResultMap.ok("添加成功").put("color",phoneColor);
        } else {
            return ResultMap.error("添加失败");
        }
    }

    @PostMapping("/addMemory")
    private ResultMap addMemory(PhoneMemory phoneMemory) {
        if (phoneService.addMemory(phoneMemory)) {
            return ResultMap.ok("添加成功").put("memory",phoneMemory);
        } else {
            return ResultMap.error("添加失败");
        }
    }


}
