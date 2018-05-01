package com.myweb.phone.service;

import com.myweb.core.PageResult;
import com.myweb.phone.model.PhoneArgs;
import com.myweb.phone.model.PhoneInfo;

public interface PhoneService {

    PageResult<PhoneInfo> listPhone(PhoneArgs args);

    boolean addPhone(PhoneInfo phone);

    boolean editPhone(PhoneInfo phoneInfo);


}
