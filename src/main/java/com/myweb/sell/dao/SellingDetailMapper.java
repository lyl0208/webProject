package com.myweb.sell.dao;

import com.myweb.sell.model.SellingDetail;
import org.springframework.transaction.annotation.Transactional;

public interface SellingDetailMapper {

    @Transactional(rollbackFor = Exception.class)
    int save(SellingDetail sellingDetail);


    int countBySellingId(String sellingId);

}
