package com.yuansq.util;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.yuansq.common.MDA;
/**
 * properties 属性文件值读取
 * <P>
 * 该属性文件可以配置多个，在　portalAll-spring.xml　文件 <BR>
 * bean id="sysPropertiesMessage".... 配置属性文件
 * <P>
 * 
 * @author 

 */
public class PropertiesUtils {
	/** bean name: messageSource */
	private ReloadableResourceBundleMessageSource message;

	public void setMessage(ReloadableResourceBundleMessageSource message) {
		this.message = message;
	}
	
	public void clear() {
		this.message.clearCache();
	}

	/**
	 * 获取properties key 对应的值
	 * 
	 * @param code
	 *            key
	 * @param args
	 *            按顺序替换的参数 sample:${0}代表被第一个参数替换
	 * @param locale
	 *            国际化
	 * @return String
	 */
	public String getMessage(String code, Object[] args, Locale locale) {
		try {
			return message.getMessage(code, args, locale);
		} catch (NoSuchMessageException e) {

			return null;
		}
	}

	/**
	 * 获取properties key 对应的值
	 * 
	 * @param code
	 *            key
	 * @param args
	 *            按顺序替换的参数 sample:${0}代表被第一个参数替换
	 * @return String
	 */
	public String getMessage(String code, Object[] args) {
		try {
			return message.getMessage(code, args, null);
		} catch (NoSuchMessageException e) {
			return null;
		}
	}

	/**
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
	/**
	 * 获取properties key 对应的值
	 * 
	 * @param code
	 *            key
	 * @return String
	 */
	public int getMessageAsInt(String code) {
		String msg=getMessage(code);
		if(msg!=null){
			return Integer.parseInt(msg);
		}
		return 0;
	}
	/**
	 * 获取properties key 对应的值
	 * 
	 * @param code
	 *            key
	 * @return String
	 */
	public long getMessageAsLong(String code) {
		String msg=getMessage(code);
		if(msg!=null){
			return Long.parseLong(msg);
		}
		return 0;
	}
	/**
	 * 获取properties key 对应的值
	 * 
	 * @param code
	 *            key
	 * @param locale
	 *            国际化
	 * @return String
	 */
	public String getMessage(String code, Locale locale) {
		try {
			return message.getMessage(code, null, locale);
		} catch (NoSuchMessageException e) {
			return null;
		}
	}

	/**
	 * 获取properties key 对应的值
	 * 
	 * @param code
	 *            key
	 * @param args
	 *            按顺序替换的参数 sample:${0}代表被第一个参数替换
	 * @param defaultMessage
	 *            找不到key,默认的值
	 * @param locale
	 *            国际化
	 * @return String
	 */
	public String getMessage(String code, Object[] args, String defaultMessage,
			Locale locale) {
		try {
			return message.getMessage(code, args, defaultMessage, locale);
		} catch (NoSuchMessageException e) {
			return null;
		}
	}

	/**
	 * 获取properties key 对应的值
	 * 
	 * @param code
	 *            key
	 * @param args
	 *            按顺序替换的参数 sample:${0}代表被第一个参数替换
	 * @param defaultMessage
	 *            找不到key,默认的值
	 * @return String
	 */
	public String getMessage(String code, Object[] args, String defaultMessage) {
		try {
			return message.getMessage(code, args, defaultMessage, null);
		} catch (NoSuchMessageException e) {
			return null;
		}
	}

	/**
	 * 获取properties key 对应的值
	 * 
	 * @param code
	 *            key
	 * @param defaultMessage
	 *            找不到key,默认的值
	 * @return String
	 */
	public String getMessage(String code, String defaultMessage) {
		try {
			return message.getMessage(code, null, defaultMessage, null);
		} catch (NoSuchMessageException e) {
			return null;
		}
	}

	/**
	 * 获取properties key 对应的值
	 * 
	 * @param code
	 *            key
	 * @param defaultMessage
	 *            找不到key,默认的值
	 * @param locale
	 *            国际化
	 * @return String
	 */
	public String getMessage(String code, String defaultMessage, Locale locale) {
		try {
			return message.getMessage(code, null, defaultMessage, locale);
		} catch (NoSuchMessageException e) {
			return null;
		}
	}
}
