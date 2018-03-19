package com.baijia.warehouse.dao;

import java.util.List;

import com.baijia.warehouse.model.db.StorageUnitDO;
import com.baijia.warehouse.model.query.StorageUnitQuery;
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
	private StorageUnitDAO unitDAO;
	
	@Test
	public void testInsert(){
		StorageUnitDO storageUnitDO = new StorageUnitDO();
		storageUnitDO.setChannel("channel1");
		storageUnitDO.setGoodsCode("goods-code");
		storageUnitDO.setGoodsNum(12);
		storageUnitDO.setLayer("layer1");
		storageUnitDO.setName("junit-unit");
		storageUnitDO.setSlot("slot1");
		int affected = unitDAO.insert(storageUnitDO);
		Assert.assertEquals(1, affected);
	}
	
	@Test
	public void testGetByList(){
		StorageUnitQuery unitQuery = new StorageUnitQuery();
		unitQuery.setHasGoodsStored(false);
		List<StorageUnitDO> result = unitDAO.getByList(unitQuery);
		Assert.assertNotNull(result);
		Assert.assertEquals(15, result.size());
	}
	
	@Test
	public void testUpdate(){
		StorageUnitDO unitDO = new StorageUnitDO();
		unitDO.setId(5);
		unitDO.setChannel("channel-110");
		unitDO.setLayer("layer-100");
		int affected = unitDAO.update(unitDO);
		Assert.assertEquals(1, affected);
	}
	
	@Test
	public void testGetById(){
		int unitId = 5;
		StorageUnitDO unitDO = unitDAO.getById(unitId);
		Assert.assertNotNull(unitDO);
		Assert.assertEquals("channel-110", unitDO.getChannel());
	}
	
	@Test
	public void testRecycleById(){
		Integer unitId = 3;
		int affected = unitDAO.recycleById(unitId);
		Assert.assertEquals(1, affected);
	}
}
