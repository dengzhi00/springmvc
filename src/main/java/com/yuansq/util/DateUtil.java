package com.yuansq.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String DATAFORMAT = "yyyy-MM-dd HH:mm:ss";
	public static String DATAFORMAT_ZH = "yyyy年MM月dd�?   HH时mm分ss�?";
	public static String DATAFORMAT_ZHDATE = "yyyy年MM月dd�?";
	
	public static String formatDate(Date date, String formmat){
		SimpleDateFormat formatter = new SimpleDateFormat(formmat);
		String dateStr = formatter.format(date);
		return dateStr;
	}
	
}
