package com.baijia.warehouse.dao;

import com.baijia.warehouse.model.db.StorageUnitDO;

/**
 * @author gushu
 * @date 2018/03/16
 */
public interface StorageUnitDAO extends BaseDAO {

	int insert(StorageUnitDO storageUnitDO);
}
