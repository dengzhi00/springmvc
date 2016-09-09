package com.yuansq.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.util.StringUtils;

public class JsonConverter{

	private ObjectMapper mapper;

	private JsonConverter(){}// 私有化构造函�?

	private JsonConverter(Inclusion inclusion){
		this.mapper=new ObjectMapper();
		this.mapper.setSerializationInclusion(inclusion);// 设置输出时包含属性的风格
		this.mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
	}

	private JsonConverter(Inclusion inclusion,DateFormat format){
		this.mapper=new ObjectMapper();
		this.mapper.setSerializationInclusion(inclusion);// 设置输出时包含属性的风格
		this.mapper.setDateFormat(new SimpleDateFormat(format.toString()));// 默认的日期转换格�?
		this.mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属�?
	}

	/**
	 * 创建JSON转换器（转换�?有属性）
	 * 
	 * @return JSON转换�?
	 */
	public static JsonConverter buildNormalConverter(){
		return new JsonConverter(Inclusion.ALWAYS);
	}

	/**
	 * 创建JSON转换器（转换�?有属性）
	 * 
	 * @param format 日期格式
	 * @return JSON转换�?
	 */
	public static JsonConverter buildNormalConverter(DateFormat format){
		return new JsonConverter(Inclusion.ALWAYS,format);
	}

	/**
	 * 创建JSON转换器（仅转换非空属性）
	 * 
	 * @return JSON转换�?
	 */
	public static JsonConverter buildNonNullConverter(){
		return new JsonConverter(Inclusion.NON_NULL);
	}

	/**
	 * 创建JSON转换器（仅转换非空属性）
	 * 
	 * @param format 日期格式
	 * @return JSON转换�?
	 */
	public static JsonConverter buildNonNullConverter(DateFormat format){
		return new JsonConverter(Inclusion.NON_NULL,format);
	}

	/**
	 * 创建JSON转换器（仅转换�?�被改变的属性）
	 * 
	 * @return JSON转换�?
	 */
	public static JsonConverter buildNonDefaultConverter(){
		return new JsonConverter(Inclusion.NON_DEFAULT);
	}

	/**
	 * 创建JSON转换器（仅转换�?�被改变的属性）
	 * 
	 * @param format 日期格式
	 * @return JSON转换�?
	 */
	public static JsonConverter buildNonDefaultConverter(DateFormat format){
		return new JsonConverter(Inclusion.NON_DEFAULT,format);
	}

	/**
	 * 将JSON字符串转化为对象（字符串为null�?"null"字符�?, 返回null；字符串�?"[]", 返回空集合）
	 * 
	 * @param json JSON字符�?
	 * @param clazz �?要转换的�?
	 * @return 转换后的类型
	 */
	public <T>T toBean(String json,Class<T> clazz) throws IOException{
		return !StringUtils.hasText(json)?null:this.mapper.readValue(json,clazz);
	}

	/**
	 * 将对象转换成JSON字符串（如果对象为Null, 返回"null"�?
	 * 
	 * @param object �?要转换的对象
	 * @return JSON字符�?
	 */
	public String toJson(Object object) throws IOException{
		return this.mapper.writeValueAsString(object);
	}

	/**
	 * 取出Mapper做进�?步的设置或使用其他序列化API
	 */
	public ObjectMapper getMapper(){
		return this.mapper;
	}
}