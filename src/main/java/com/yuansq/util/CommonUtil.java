package com.yuansq.util;

import java.text.DecimalFormat;
import java.util.Map;

public class CommonUtil {

	public static String getStrFromMap(Map<String, Object> map, String key) {
		if (map.containsKey(key)) {
			if (map.get(key) != null) {
				return map.get(key).toString();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static String replaceStrFromMap(Map<String, Object> map, String key) {
		if (map.containsKey(key)) {
			if (map.get(key) != null) {
				return map.get(key).toString();
			} else {
				return key;
			}
		} else {
			return key;
		}
	}
	
	public static String getNumberFromMap(Map<String, Object> map, String key) {
		if (map.containsKey(key)) {
			if (map.get(key) != null) {
				return map.get(key).toString();
			} else {
				return "0";
			}
		} else {
			return "0";
		}
	}

	public static Integer getIntFromMap(Map<String, Object> map, String key) {
		if (map.containsKey(key)) {
			if (map.get(key) != null && map.get(key) != "") {
				return Integer.valueOf(map.get(key).toString());
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static Long getLongFromMap(Map<String, Object> map, String key) {
		if (map.containsKey(key)) {
			if (map.get(key) != null && map.get(key) != "") {
				return Long.valueOf(map.get(key).toString());
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static Double getDoubleFromMap(Map<String, Object> map, String key) {
		if (map.containsKey(key)) {
			if (map.get(key) != null && map.get(key) != "") {
				return Double.valueOf(map.get(key).toString());
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static Float getFloatFromMap(Map<String, Object> map, String key) {
		if (map.containsKey(key)) {
			if (map.get(key) != null && map.get(key) != "") {
				return Float.valueOf(map.get(key).toString());
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static String formatDouble(String obj){
		DecimalFormat df = new DecimalFormat("0.00");
		String result = df.format(Double.valueOf(obj));
		return result;
	}
	
}
