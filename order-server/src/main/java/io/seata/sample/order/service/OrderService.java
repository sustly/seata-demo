package io.seata.sample.order.service;

import io.seata.sample.order.dao.OrderDao;
import io.seata.sample.order.entity.Order;
import io.seata.sample.order.feign.AccountApi;
import io.seata.sample.order.feign.StockApi;
import io.seata.spring.annotation.GlobalTransactional;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private StockApi stockApi;
    @Autowired
    private AccountApi accountApi;

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
        orderDao.create(order);

        //远程方法 扣减库存
        stockApi.decrease(order.getProductId(), order.getCount());

        //远程方法 扣减账户余额

        LOGGER.info("------->扣减账户开始order中");
        accountApi.decrease(order.getUserId(), order.getMoney());
        LOGGER.info("------->扣减账户结束order中");

        LOGGER.info("------->交易结束");
    }

    /**
     * 修改订单状态
     */
    public void update(Long userId, BigDecimal money, Integer status) {
        LOGGER.info("修改订单状态，入参为：userId={},money={},status={}", userId, money, status);
        orderDao.update(userId, money, status);
    }
}
