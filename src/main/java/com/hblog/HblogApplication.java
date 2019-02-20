package com.hblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hblog.mapper")
public class HblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(HblogApplication.class, args);
	}

}
