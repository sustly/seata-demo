package io.seata.tcc.order.dao;

import io.seata.tcc.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface OrderDao {

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    void create(Order order);

    /**
     * 修改订单金额
     *
     * @param userId
     * @param money
     */
    void update(@Param("userId") Long userId, @Param("money") BigDecimal money, @Param("status") Integer status);

    void deleteByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);
}
