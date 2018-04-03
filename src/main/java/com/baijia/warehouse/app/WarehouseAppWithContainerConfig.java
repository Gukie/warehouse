package com.baijia.warehouse.app;

import com.baijia.warehouse.filter.WarehouseFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 这个是用于部署在容器中的
 * 
 * refer:
 * <li>https://spring.io/blog/2014/03/07/deploying-spring-boot-applications
 * 
 * @author gushu
 * @date 2018/04/03
 */
@SpringBootApplication(scanBasePackages = { "com.baijia.warehouse.controller", "com.baijia.warehouse.service",
		"com.baijia.warehouse.config" })
@ImportResource(locations = { "classpath:mybatis/mybatis-spring.xml", "classpath:mybatis/datasource.xml",
		"classpath:spring-conf/spring-beans.xml" })
@Configuration
public class WarehouseAppWithContainerConfig extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseAppWithContainerConfig.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		WarehouseFilter warehouseFilter = new WarehouseFilter();
		builder.sources(warehouseFilter);
		return builder;
	}

}
