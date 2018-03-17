package com.baijia.warehouse.dao;

import java.util.List;

import com.baijia.warehouse.model.db.StorageUnitDO;
import com.baijia.warehouse.model.query.StorageUnitQuery;

/**
 * @author gushu
 * @date 2018/03/16
 */
public interface StorageUnitDAO extends BaseDAO {

	int insert(StorageUnitDO storageUnitDO);

	List<StorageUnitDO> getByList(StorageUnitQuery unitQuery);

	int update(StorageUnitDO unitDO);

	StorageUnitDO getById(int unitId);

	int recycleById(Integer unitId);
}
