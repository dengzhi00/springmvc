package com.yuansq.util;

import java.util.HashMap;
import java.util.Map;

public class Pagination {
	public static Map<String, Object> getSection(double pageIndex, double pageLength, double count) {
		double totalPage = (int) Math.ceil(count / pageLength);
		pageIndex = (pageIndex - 1) % totalPage + 1;
		double start = (pageIndex - 1) * pageLength;

		double end = pageIndex * pageLength > count ? count : (pageIndex * pageLength);
		Map<String, Object> result = new HashMap<>();
		result.put("minNum", start);
		result.put("maxNum", end);
		return result;
	}
}
