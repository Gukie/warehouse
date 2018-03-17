package com.baijia.warehouse.app;

import java.util.List;

import javax.annotation.Resource;

import com.baijia.warehouse.model.dto.StorageUnitDTO;
import com.baijia.warehouse.service.WarehouseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author gushu
 * @date 2018/03/16
 */
@SpringBootApplication(scanBasePackages = { "com.baijia.warehouse.service" })
@ImportResource(locations = { "classpath:mybatis/mybatis-spring.xml", "classpath:mybatis/datasource.xml",
		"classpath:spring-conf/spring-beans.xml" })
@Controller
public class WarehouseApp {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApp.class, args);
	}

	@Resource(name = "warehouseService")
	private WarehouseService warehouseService;

	@RequestMapping("/")
	public String index(ModelMap modelMap) {
		// 向模板中添加属性
		modelMap.put("hello", "lokia");
		// return模板文件的名称，对应src/main/resources/templates/index.html
		return "index";
	}

	// @RequestMapping("/add")
	// public ResponseResult add(StorageUnitDTO goodsDTO) {
	// warehouseService.add(goodsDTO);
	// ResponseResult responseResult = new ResponseResult();
	// return responseResult;
	// }

	@RequestMapping("/add")
	public String add(@RequestParam(name = "channel") String channel, @RequestParam(name = "layer") String layer,
			@RequestParam(name = "slot") String slot) {
		StorageUnitDTO storageUnitDTO = new StorageUnitDTO();
		storageUnitDTO.setChannel(channel);
		storageUnitDTO.setLayer(layer);
		storageUnitDTO.setSlot(slot);
		warehouseService.add(storageUnitDTO);
		return "index";
	}

	/**
	 * 获取没有存放货物的货位，返回前10条
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/getStorageUnit")
	public String getStorageUnit(ModelMap modelMap) {
		List<StorageUnitDTO> result = warehouseService.getUnit();
		modelMap.put("unitList", result);
		return "index";
	}

	/**
	 * 根据货物条形码获取货位信息. 按放入的时间顺序排列. 返回前10条
	 * 
	 * @param goodsCode
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/getStorageUnitByGoodsCode")
	public String getStorageUnitByGoodsCode(@RequestParam("goodsCode") String goodsCode, ModelMap modelMap) {
		List<StorageUnitDTO> result = warehouseService.getUnitByGoodsCode(goodsCode);
		modelMap.put("unitList", result);
		return "index";
	}

	/**
	 * 将货物放到货位上
	 * 
	 * @param storageUnitId
	 * @param goodsCode
	 * @param goodsNum
	 * @return
	 */
	@RequestMapping("/addGoods2Unit")
	public String addGoods2Unit(@RequestParam("storageUnitId") Integer storageUnitId,
			@RequestParam("goodsCode") String goodsCode, @RequestParam("goodsNum") Integer goodsNum) {
		StorageUnitDTO storageUnitDTO = new StorageUnitDTO();
		storageUnitDTO.setId(storageUnitId);
		storageUnitDTO.setGoodsCode(goodsCode);
		storageUnitDTO.setGoodsNum(goodsNum);
		warehouseService.updateUnit(storageUnitDTO);
		return "index";
	}
	
	/**
	 * 将货物从货位上取出(出库操作)
	 * 
	 * @param storageUnitId
	 * @return
	 */
	@RequestMapping("/recycleUnit")
	public String recycleUnit(@RequestParam("storageUnitId") Integer storageUnitId) {
		warehouseService.recycleUnit(storageUnitId);
		return "index";
	}

}
