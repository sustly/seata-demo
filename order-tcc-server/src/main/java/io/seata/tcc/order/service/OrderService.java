package io.seata.tcc.order.service;

import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tcc.order.action.TccOrderAction;
import io.seata.tcc.order.entity.Order;
import io.seata.tcc.order.feign.StockApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private TccOrderAction tccOrderAction;
    @Autowired
    private StockApi stockApi;

    /**
     * 创建订单
     *
     * @param order
     * @return 测试结果：
     * 1.添加本地事务：仅仅扣减库存
     * 2.不添加本地事务：创建订单，扣减库存
     */
    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        LOGGER.info("------->交易开始");
        //本地方法
        tccOrderAction.prepare(null, order);

        //远程方法 扣减库存
        stockApi.decrease(order.getProductId(), order.getCount());

    }
}
