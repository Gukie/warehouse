package com.baijia.warehouse.config;

import com.baijia.warehouse.interceptor.WarehouseInteceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * do some extra configuration for whole app at start-up.
 * <li> refer: https://stackoverflow.com/questions/31082981/spring-boot-adding-http-request-interceptors
 * @author gushu
 * @date 2018/04/03
 */
//@Configuration
@Component
public class WarehouseInterceptorConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new WarehouseInteceptor()).addPathPatterns("/*");
	}
}
