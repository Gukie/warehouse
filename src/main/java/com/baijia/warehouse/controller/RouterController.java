package com.baijia.warehouse.controller;

import javax.annotation.Resource;

import com.baijia.warehouse.service.WarehouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gushu
 * @date 2018/03/17
 */
@Controller

public class RouterController {
	
	@Resource(name = "warehouseService")
	private WarehouseService warehouseService;
	
	@RequestMapping("/")
	public String index(ModelMap modelMap) {
		// 向模板中添加属性
		modelMap.put("hello", "lokia");
		// return模板文件的名称，对应src/main/resources/templates/index.html
		return "index";
	}
	@RequestMapping("/2addUnit")
	public String addUnit() {
		return "unit_add";
	}
	
	@RequestMapping("/2goodsIn")
	public String goodsIn() {
		return "in_goods";
	}
	
	@RequestMapping("/2goodsOut")
	public String goodsOut() {
		return "out_goods";
	}
}
