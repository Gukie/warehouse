package com.baijia.warehouse.service;

import java.util.List;

import com.baijia.warehouse.model.dto.StorageUnitDTO;

/**
 * @author gushu
 * @date 2018/03/16
 */
public interface WarehouseService {

	int add(StorageUnitDTO goodsDTO);

	List<StorageUnitDTO> getAvailableUnit();

	List<StorageUnitDTO> getUnitByGoodsCode(String goodsCode);

	int updateUnit(StorageUnitDTO storageUnitDTO);

	int recycleUnit(Integer storageUnitId);

}
