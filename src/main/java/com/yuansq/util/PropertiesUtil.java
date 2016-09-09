package com.yuansq.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.yuansq.commom.MDA;

public class PropertiesUtil {
	
	private static Properties pro;
	/** bean name: messageSource */
	private ReloadableResourceBundleMessageSource message;

	public void setMessage(ReloadableResourceBundleMessageSource message) {
		this.message = message;
	}
	
	public void clear() {
		this.message.clearCache();
	}
	public static Properties getInstance() throws IOException, URISyntaxException{
		if(pro != null) {
			return pro;
		}
		String  path = PropertiesUtil.class.getClassLoader().getResource("").toURI().getPath();
		pro = new Properties();
		File file = new File(path+File.separator+"properties\\ei_print.properties");
		try {
			pro.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pro;
	}
	
	public static String getSourcePath(String path){
		try {
			path = PropertiesUtil.class.getClassLoader().getResource("").toURI().getPath()+ path + "/";
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return path;
	}
	public static Properties getInstance(String proName) throws IOException, URISyntaxException{
		if(pro != null) {
			return pro;
		}
		String  path = PropertiesUtil.class.getClassLoader().getResource("").toURI().getPath();
		pro = new Properties();
		File file = new File(path+File.separator+"properties\\"+proName+".properties");
		try {
			pro.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pro;
	}	/**
	 * 获取properties key 对应的值
	 * 采用MDA统一配置，所以改造原有的读取方法，判断MDA中是否有配置该属性，如果有，直接读取MDA，如果没有则读取原来的配置
	 * @param code
	 *            key
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String getMessage(String code) {
		// java反射遍历MDA类中是否存在该属性
		boolean isHasProperty = false;
		String propertyValue = "";
		Map<String, String> propertyMap = new HashMap<String, String>();
		Field[] fields = MDA.class.getDeclaredFields();
		for (Field field : fields) {
			// 遍历String类型属性
			if (field.getGenericType().equals(String.class)) {
				if (code.equals(field.getName())) {
					try {
						propertyValue = (String) field.get(new MDA());
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					isHasProperty = true;
				}
			}
			// 遍历Map类型属性
			if (field.getGenericType().toString().equals("java.util.Map<java.lang.String, java.lang.String>")) {
				try {
					propertyMap = (Map<String, String>) field.get(new MDA());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				for (String key : propertyMap.keySet()) {
					if (code.equals(key)) {
						propertyValue = propertyMap.get(key);
						isHasProperty = true;
						break;
					}
				}
			}
			if (isHasProperty) {
				break;
			}
		}
		// 判断MDA中是否有配置该属性，如果有，直接读取MDA，如果没有则读取原来的配置（注：应用启动时，MDA还未加载）
		if (isHasProperty && StringUtil.isNotBlank(propertyValue)) {
			return propertyValue;
		} else {
			try {
				return message.getMessage(code, null, null);
			} catch (NoSuchMessageException e) {
				//把ERROR级别改为INFO级别,忽略未配置的键值对
				// 20140403 ignore this exception
				//log.info("properties key不存在("+code+")", e);
				return null;
			}
		}
	}
}
