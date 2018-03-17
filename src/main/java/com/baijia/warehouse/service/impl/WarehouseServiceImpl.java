package com.baijia.warehouse.service.impl;

import com.baijia.warehouse.dao.StorageUnitDAO;
import com.baijia.warehouse.model.db.StorageUnitDO;
import com.baijia.warehouse.model.dto.StorageUnitDTO;
import com.baijia.warehouse.service.WarehouseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gushu
 * @date 2018/03/16
 */
@Service("warehouseService")
public class WarehouseServiceImpl implements WarehouseService {

	@Autowired
	private StorageUnitDAO storageUnitDAO;
	
	@Override
	public int add(StorageUnitDTO storageUnitDTO) {
		StorageUnitDO storageUnitDO = convert2DO(storageUnitDTO);
		return storageUnitDAO.insert(storageUnitDO);
	}

	private StorageUnitDO convert2DO(StorageUnitDTO storageUnitDTO) {
		StorageUnitDO result = new StorageUnitDO();
		BeanUtils.copyProperties(storageUnitDTO, result);
		return result;
	}

}
