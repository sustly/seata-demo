package com.example.tcc.storage.action.impl;

import com.example.tcc.storage.action.ResultHolder;
import com.example.tcc.storage.action.TccStorageAction;
import com.example.tcc.storage.dao.StockDao;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class TccStorageActionImpl implements TccStorageAction {

    @Autowired
    private StockDao stockDao;

    public boolean prepare(BusinessActionContext actionContext, Long productId, int count) {
        log.info("tcc prepare...");
        stockDao.decrease(productId, count);
        ResultHolder.setActionOrderResult(actionContext.getXid(), UUID.randomUUID().toString());
        return true;
    }

    public boolean commit(BusinessActionContext actionContext) {
        log.info("tcc commit...");
        if (ResultHolder.getActionOrderResult(actionContext.getXid()) == null) {
            return true;
        }
        ResultHolder.removeResult(actionContext.getXid());
        return true;
    }

    public boolean rollback(BusinessActionContext actionContext) {
        log.info("tcc rollback...");
        if (ResultHolder.getActionOrderResult(actionContext.getXid()) == null) {
            return true;
        }
        int productId = (Integer) actionContext.getActionContext("productId");
        int count = (Integer) actionContext.getActionContext("count");
        stockDao.increase(productId, count);
        ResultHolder.removeResult(actionContext.getXid());
        return true;
    }
}
