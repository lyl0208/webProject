package com.myweb.phone.dao;

import com.myweb.phone.model.PhoneArgs;
import com.myweb.phone.model.PhoneInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PhoneMapper {

    List<PhoneInfo> listPhone(PhoneArgs args);

    @Transactional(rollbackFor = Exception.class)
    int addPhone(PhoneInfo phoneInfo);

    @Transactional(rollbackFor = Exception.class)
    int editPhone(PhoneInfo phoneInfo);


}
