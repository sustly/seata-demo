package io.seata.sample.storage.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDao {

    /**
     * 扣减库存
     *
     * @param productId 产品id
     * @param count     数量
     * @return
     */
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
