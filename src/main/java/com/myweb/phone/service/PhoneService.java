package com.myweb.phone.service;

import com.myweb.core.PageResult;
import com.myweb.phone.model.*;

import java.util.List;

public interface PhoneService {

    PageResult<PhoneInfo> listPhone(PhoneArgs args);

    boolean addPhone(PhoneInfo phone);

    boolean editPhone(PhoneInfo phoneInfo);

    List<PhoneBrand> getBrands();

    boolean addBrand(PhoneBrand phoneBrand);

    List<PhoneModel> getModels(Long brandId);

    boolean addModel(PhoneModel phoneModel);

    List<PhoneColor> getColors(Long modelId);

    boolean addColor(PhoneColor phoneColor);

    List<PhoneMemory> getMemorys(Long modelId);

    boolean addMemory(PhoneMemory phoneMemory);


}