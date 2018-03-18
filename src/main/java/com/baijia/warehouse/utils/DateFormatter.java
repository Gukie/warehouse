package com.baijia.warehouse.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gushu
 * @date 2018/03/18
 */
public class DateFormatter {

	private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getFormattedDateStr(Date date) {
		if(date == null){
			return "";
		}
		return simpleDateFormat.format(date);
	}
}
