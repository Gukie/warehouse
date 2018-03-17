package com.baijia.warehouse.app;

import javax.annotation.Resource;

import com.baijia.warehouse.base.ResponseResult;
import com.baijia.warehouse.model.dto.StorageUnitDTO;
import com.baijia.warehouse.service.WarehouseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gushu
 * @date 2018/03/16
 */
@SpringBootApplication(scanBasePackages = { "com.baijia.warehouse.service" })
@ImportResource(locations = { "classpath:mybatis/mybatis-spring.xml", "classpath:spring-conf/spring-beans.xml" })
@Controller
public class WarehouseApp {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApp.class, args);
	}

	@Resource(name = "warehouseService")
	private WarehouseService warehouseService;

	@RequestMapping("/index")
	public String hello(ModelMap modelMap) {
		// 向模板中添加属性
		modelMap.put("hello", "lokia");
		// return模板文件的名称，对应src/main/resources/templates/index.html
		return "index";
	}

	@RequestMapping("/add")
	public ResponseResult add(StorageUnitDTO goodsDTO) {
		warehouseService.add(goodsDTO);
		ResponseResult responseResult = new ResponseResult();
		return responseResult;
	}
}
