package com.baijia.warehouse.model.dto;

/**
 * 货位. 最小存储单元
 * "channel-layer-slot"可以唯一标示一个货位
 * 
 * @author gushu
 * @date 2018/03/16
 */
public class StorageUnitDTO {

	private int id;
	private String name;
	private String channel; // 通道
	private String layer; // 货层
	private String slot; // 货槽
	private String goodsCode; // 货物条码
	private Integer goodsNum; // 货物数量
	
	private String gmtCreated;
	private String gmtModified;
	

	public String getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(String gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public String getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(String gmtModified) {
		this.gmtModified = gmtModified;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getLayer() {
		return layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
