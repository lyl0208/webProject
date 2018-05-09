package com.myweb.front.controller;

import com.myweb.phone.model.PhoneBrand;
import com.myweb.phone.model.PhoneInfo;
import com.myweb.phone.model.PhoneModel;
import com.myweb.phone.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/front")
public class FrontController {

    @Autowired
    private PhoneService phoneService;

    @PostMapping("/listBrand")
    public List<PhoneBrand> listBrand() {
        return phoneService.getBrands();
    }

    @PostMapping("/listPhoneItem")
    public List<PhoneModel> listPhoneItem(Long brandId) {
        return phoneService.getModels(brandId);
    }

    @PostMapping("/listPhoneInfo")
    public List<PhoneInfo> listPhoneInfo(Long modelId) {
        return phoneService.listPhoneInfoByModelId(modelId);
    }


}
