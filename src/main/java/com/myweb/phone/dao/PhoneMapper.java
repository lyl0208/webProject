package com.myweb.phone.dao;

import com.myweb.phone.model.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PhoneMapper {

    List<PhoneInfo> listPhone(PhoneArgs args);

    @Transactional(rollbackFor = Exception.class)
    int addPhone(PhoneInfo phoneInfo);

    @Transactional(rollbackFor = Exception.class)
    int editPhone(PhoneInfo phoneInfo);

    List<PhoneBrand> getBrands();

    @Transactional(rollbackFor = Exception.class)
    int addBrand(PhoneBrand brand);

    List<PhoneModel> getModels(Long brandId);

    @Transactional(rollbackFor = Exception.class)
    int addModel(PhoneModel phoneModel);

    List<PhoneColor> getColors(Long modelId);

    @Transactional(rollbackFor = Exception.class)
    int addColor(PhoneColor phoneColor);

    List<PhoneMemory> getMemorys(Long modelId);

    @Transactional(rollbackFor = Exception.class)
    int addMemory(PhoneMemory phoneMemory);

    PhoneInfo findPhoneById(Long phoneId);

    PhoneInfo findPhoneByImei(String imei);

}
