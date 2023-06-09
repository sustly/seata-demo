package io.seata.tcc.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "stock-tcc-server")
public interface StockApi {

    /**
     * 扣减库存
     *
     * @param productId
     * @param count
     * @return
     */
    @GetMapping(value = "/stock/decrease")
    String decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
