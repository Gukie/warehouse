package com.baijia.warehouse.controller;

import java.util.List;

import javax.annotation.Resource;

import com.baijia.warehouse.base.ResponseResult;
import com.baijia.warehouse.model.dto.StorageUnitDTO;
import com.baijia.warehouse.service.WarehouseService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gushu
 * @date 2018/03/17
 */
@RestController
public class IndexController {
	@Resource(name = "warehouseService")
	private WarehouseService warehouseService;


//	@RequestMapping("/addUnit")
//	public ResponseResult addUnit(StorageUnitDTO storageUnitDTO) {
//		int added = warehouseService.add(storageUnitDTO);
//		return ResponseResult.success(added);
//	}
	
	@RequestMapping("/addUnit")
	public ResponseResult addUnit(@RequestBody StorageUnitDTO storageUnitDTO) {
		int added = warehouseService.add(storageUnitDTO);
		return ResponseResult.success(added);
	}


	/**
	 * 获取没有存放货物的货位，返回前10条
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/getAvailableUnit")
	public ResponseResult getAvailableUnit() {
		List<StorageUnitDTO> result = warehouseService.getAvailableUnit();
		return ResponseResult.success(result);
	}

	/**
	 * 根据货物条形码获取货位信息. 按放入的时间顺序排列. 返回前10条
	 * 
	 * @param goodsCode
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/getStorageUnitByGoodsCode")
	public ResponseResult getStorageUnitByGoodsCode(@RequestParam("goodsCode") String goodsCode) {
		System.err.println("received param:"+goodsCode);
		List<StorageUnitDTO> result = warehouseService.getUnitByGoodsCode(goodsCode);
		return ResponseResult.success(result);
	}
	

//	/**
//	 * 将货物放到货位上
//	 * 
//	 * @param storageUnitId
//	 * @param goodsCode
//	 * @param goodsNum
//	 * @return
//	 */
//	@RequestMapping("/addGoods2Unit")
//	public ResponseResult addGoods2Unit(StorageUnitDTO storageUnitDTO) {
//		Integer updated = warehouseService.updateUnit(storageUnitDTO);
//		return ResponseResult.success(updated);
//	}
	
	/**
	 * 将货物放到货位上
	 * 
	 * @param storageUnitId
	 * @param goodsCode
	 * @param goodsNum
	 * @return
	 */
	@RequestMapping("/addGoods2Unit")
	public ResponseResult addGoods2Unit(@RequestBody StorageUnitDTO storageUnitDTO) {
		Integer updated = warehouseService.updateUnit(storageUnitDTO);
		return ResponseResult.success(updated);
	}

	/**
	 * 将货物从货位上取出(出库操作)
	 * 
	 * @param storageUnitId
	 * @return
	 */
	@RequestMapping("/recycleUnit")
	public ResponseResult recycleUnit(@RequestParam("storageUnitId") Integer storageUnitId) {
		int recycled = warehouseService.recycleUnit(storageUnitId);
		return ResponseResult.success(recycled);
	}
}
