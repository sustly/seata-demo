package io.seata.tcc.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("io.seata.tcc.order.dao")
@EnableDiscoveryClient
@EnableFeignClients
public class OrderTccServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderTccServerApplication.class, args);
    }

}
