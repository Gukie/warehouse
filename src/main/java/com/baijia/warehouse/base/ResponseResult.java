package com.baijia.warehouse.base;

/**
 * @author gushu
 * @date 2018/03/16
 */
public class ResponseResult {
	
	private Object data;
	
	public static ResponseResult success(Object data) {
		ResponseResult result = new ResponseResult();
		result.setData(data);
		return result;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
