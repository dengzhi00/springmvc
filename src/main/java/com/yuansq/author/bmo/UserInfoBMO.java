package com.yuansq.author.bmo;

import java.util.List;
import java.util.Map;

public interface UserInfoBMO {

	public List<Map<String, Object>> queryUser(Map<String, Object> paramsMap);

	public Map<String, Object> addUser(Map<String, Object> paramsMap);
}
