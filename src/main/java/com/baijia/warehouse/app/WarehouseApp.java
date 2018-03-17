package com.baijia.warehouse.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author gushu
 * @date 2018/03/16
 */
@SpringBootApplication(scanBasePackages = { "com.baijia.warehouse.controller", "com.baijia.warehouse.service" })
@ImportResource(locations = { "classpath:mybatis/mybatis-spring.xml", "classpath:mybatis/datasource.xml",
		"classpath:spring-conf/spring-beans.xml" })
// @RestController
// @Controller
public class WarehouseApp {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApp.class, args);
	}

}
