package com.example.tcc.storage.service;

import com.example.tcc.storage.action.TccStorageAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);

    @Autowired
    private TccStorageAction tccStorageAction;

    /**
     * 扣减库存
     *
     * @param productId 产品id
     * @param count     数量
     * @return
     */
    public void decrease(Long productId, Integer count) {
        LOGGER.info("------->扣减库存开始");
        tccStorageAction.prepare(null, productId, count);
        LOGGER.info("------->扣减库存结束");
        int i = 1/0;
    }
}
