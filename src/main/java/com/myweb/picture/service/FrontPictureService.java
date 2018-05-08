package com.myweb.picture.service;

import com.myweb.core.PageResult;
import com.myweb.phone.model.PhoneModel;
import com.myweb.picture.model.FrontPictureArgs;

public interface FrontPictureService {


    PageResult list(FrontPictureArgs args);

    boolean add(PhoneModel phoneModel);
}
