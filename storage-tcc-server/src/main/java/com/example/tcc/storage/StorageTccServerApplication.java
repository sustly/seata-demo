package com.example.tcc.storage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.tcc.storage.dao")
public class StorageTccServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorageTccServerApplication.class, args);
	}

}
