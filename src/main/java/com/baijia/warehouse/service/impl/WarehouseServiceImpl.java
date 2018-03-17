package com.baijia.warehouse.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.baijia.warehouse.dao.StorageUnitDAO;
import com.baijia.warehouse.model.db.StorageUnitDO;
import com.baijia.warehouse.model.dto.StorageUnitDTO;
import com.baijia.warehouse.model.query.StorageUnitQuery;
import com.baijia.warehouse.service.WarehouseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
		refineDTO(storageUnitDTO);
		StorageUnitDO storageUnitDO = convert2DO(storageUnitDTO);
		return storageUnitDAO.insert(storageUnitDO);
	}

	private void refineDTO(StorageUnitDTO storageUnitDTO) {
		if (storageUnitDTO == null) {
			return;
		}
		String name = storageUnitDTO.getName();
		if (!StringUtils.isEmpty(name)) {
			return;
		}
		StringBuilder newName = new StringBuilder();
		newName.append(storageUnitDTO.getChannel()).append("-");
		newName.append(storageUnitDTO.getLayer()).append("-");
		newName.append(storageUnitDTO.getSlot());

		storageUnitDTO.setName(newName.toString());
	}

	private StorageUnitDO convert2DO(StorageUnitDTO storageUnitDTO) {
		StorageUnitDO result = new StorageUnitDO();
		BeanUtils.copyProperties(storageUnitDTO, result);
		return result;
	}

	@Override
	public List<StorageUnitDTO> getUnit() {
		StorageUnitQuery unitQuery = new StorageUnitQuery();
		List<StorageUnitDO> unitDOList = storageUnitDAO.getByList(unitQuery);
		return convert2DTO(unitDOList);
	}

	private List<StorageUnitDTO> convert2DTO(List<StorageUnitDO> unitDOList) {
		List<StorageUnitDTO> result = new ArrayList<>();
		if (CollectionUtils.isEmpty(unitDOList)) {
			return result;
		}
		for (StorageUnitDO item : unitDOList) {
			StorageUnitDTO dtoItem = new StorageUnitDTO();
			BeanUtils.copyProperties(item, dtoItem);
			result.add(dtoItem);
		}
		return result;
	}

	@Override
	public List<StorageUnitDTO> getUnitByGoodsCode(String goodsCode) {
		if (StringUtils.isEmpty(goodsCode)) {
			return new ArrayList<>();
		}

		StorageUnitQuery unitQuery = new StorageUnitQuery();
		unitQuery.setGoodsCodeList(Arrays.asList(goodsCode));
		List<StorageUnitDO> unitDOList = storageUnitDAO.getByList(unitQuery);
		return convert2DTO(unitDOList);
	}

	@Override
	public int updateUnit(StorageUnitDTO storageUnitDTO) {
		if (storageUnitDTO == null) {
			return 0;
		}
		StorageUnitDO unitDO = storageUnitDAO.getById(storageUnitDTO.getId());
		if (unitDO == null) {
			return 0;
		}
		updateDOWithDTOInfo(unitDO, storageUnitDTO);
		return storageUnitDAO.update(unitDO);
	}

	private void updateDOWithDTOInfo(StorageUnitDO unitDO, StorageUnitDTO dtoInfo) {
		if (unitDO == null || dtoInfo == null) {
			return;
		}
		if (!StringUtils.isEmpty(dtoInfo.getChannel())) {
			unitDO.setChannel(dtoInfo.getChannel());
		}
		if (!StringUtils.isEmpty(dtoInfo.getGoodsCode())) {
			unitDO.setGoodsCode(dtoInfo.getGoodsCode());
		}
		if (dtoInfo.getGoodsNum() != null && dtoInfo.getGoodsNum() != 0) {
			unitDO.setGoodsNum(dtoInfo.getGoodsNum());
		}
		if (!StringUtils.isEmpty(dtoInfo.getLayer())) {
			unitDO.setLayer(dtoInfo.getLayer());
		}
		if (!StringUtils.isEmpty(dtoInfo.getName())) {
			unitDO.setName(dtoInfo.getName());
		}
		if (!StringUtils.isEmpty(dtoInfo.getSlot())) {
			unitDO.setSlot(dtoInfo.getSlot());
		}
	}

	@Override
	public int recycleUnit(Integer storageUnitId) {
		if(storageUnitId == null || storageUnitId == 0){
			return 0;
		}
		return storageUnitDAO.recycleById(storageUnitId);
	}

}
