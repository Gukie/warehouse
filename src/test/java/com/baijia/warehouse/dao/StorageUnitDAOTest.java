package com.baijia.warehouse.dao;

import com.baijia.warehouse.model.db.StorageUnitDO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * @author gushu
 * @date 2018/03/16
 */
@Rollback(value = false)
public class StorageUnitDAOTest extends BaseDAOTest {

	@Autowired
	private StorageUnitDAO storageUnitDAO;
	
	@Test
	public void testInsert(){
		StorageUnitDO storageUnitDO = new StorageUnitDO();
		storageUnitDO.setChannel("channel1");
		storageUnitDO.setGoodsCode("goods-code");
		storageUnitDO.setGoodsNum(12);
		storageUnitDO.setLayer("layer1");
		storageUnitDO.setName("junit-unit");
		storageUnitDO.setSlot("slot1");
		int affected = storageUnitDAO.insert(storageUnitDO);
		Assert.assertEquals(1, affected);
	}
}
