package com.baijia.warehouse.model.query;

import java.util.List;

/**
 * @author gushu
 * @date 2018/03/17
 */
public class StorageUnitQuery {
	
	private List<Integer> unitIdList;
	private List<String> goodsCodeList;
	private Boolean hasGoodsStored = true;
	public List<Integer> getUnitIdList() {
		return unitIdList;
	}
	public void setUnitIdList(List<Integer> unitIdList) {
		this.unitIdList = unitIdList;
	}
	public List<String> getGoodsCodeList() {
		return goodsCodeList;
	}
	public void setGoodsCodeList(List<String> goodsCodeList) {
		this.goodsCodeList = goodsCodeList;
	}
	public Boolean getHasGoodsStored() {
		return hasGoodsStored;
	}
	public void setHasGoodsStored(Boolean hasGoodsStored) {
		this.hasGoodsStored = hasGoodsStored;
	}
}
