package io.seata.tcc.order.action.impl;

import com.alibaba.fastjson.JSONObject;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.tcc.order.action.ResultHolder;
import io.seata.tcc.order.action.TccOrderAction;
import io.seata.tcc.order.dao.OrderDao;
import io.seata.tcc.order.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class TccOrderActionImpl implements TccOrderAction {

    @Autowired
    private OrderDao orderDao;

    public boolean prepare(BusinessActionContext actionContext, Order order) {
        log.info("tcc prepare...");
        orderDao.create(order);
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
        JSONObject order = (JSONObject)actionContext.getActionContext("order");

        orderDao.deleteByUserIdAndProductId(order.getLong("userId"), order.getLong("productId"));
        ResultHolder.removeResult(actionContext.getXid());
        return true;
    }
}
