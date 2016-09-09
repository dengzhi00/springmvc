package com.yuansq.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.yuansq.exception.BaseException;
import com.yuansq.exception.BusinessException;

public class JsonUtil {
	
//	private static Logger logger= Logger.getLogger(JsonUtil.class);

	/**
	 * 把JSON序列化成String
	 * @param obj
	 * @return
	 */
	public static String getJsonAsString(Object obj){
		try {
			return JsonConverter.buildNormalConverter().toJson(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	/**
	 * 把json转换成需要的对象
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return
	 * @throws BaseException
	 */
	public static <T> T fromJSON(String json, Class<T> clazz) throws BaseException{
		try {
			return JsonConverter.buildNormalConverter().toBean(json, clazz);
		} catch (IOException ioe){
			throw new BaseException("JSON格式数据转换�?"+clazz.getSimpleName()+
					"对象出现错误，或不是JSON类型!"+ioe.getMessage());
		}
	}
	
	/**
	 * 把json直接转换成MAP
	 * @param json
	 * @return
	 * @throws BaseException 若json不是MAP则会报错
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> fromJSON(String json) throws BaseException{
		try {
			return JsonConverter.buildNormalConverter().toBean(json, Map.class);
		} catch (JsonParseException jpe){
//			logger.error("json报文转换错误�?"+json+"/"+jpe.getMessage());
			throw new BaseException("JSON格式数据转换成MAP出现错误，或不是JSON类型!"+jpe.getMessage());
		} catch (JsonMappingException jme){
//			logger.error("json报文转换错jme误："+json+"/"+jme.getMessage());
			throw new BaseException("JSON格式数据转换成MAP出现错误，或不是JSON类型!"+jme.getMessage());
		} catch (IOException ioe){
			throw new BaseException("JSON格式数据转换错误!"+ioe.getMessage());
		}
	}
	
	/**
	 * 
	 * @param jsonPath 从传入的paramMap根开始以"/"表示层级
	 * @param paramMap JSON对应的map
	 * @param isMustExist 说明节点是否必须存在</br>
	 * &nbsp;&nbsp;&nbsp;当为true�?:当节点不存在会throw BusinessException</br>
	 * &nbsp;&nbsp;&nbsp;当为false�?,若节点不存在则返回NULL
	 * @return �?后一个节点对应的�?
	 * @throws BaseException基础异常，BusinessException业务异常
	 */
	@SuppressWarnings("rawtypes")
	public static Object getObjByPath(String jsonPath, Map<String,Object> paramMap,boolean isMustExist)
	throws BusinessException, BaseException{
		//result/attrList/8
		String []paths = jsonPath.split("/");
		if(!paramMap.containsKey(paths[0])) {
			if(isMustExist) {
				throw new BusinessException("报文协议中必传的节点["+paths[0]+"]不存�?");
			}
			return null;
		}
		
		Object obj = paramMap.get(paths[0]);
		String fullPath = paths[0]+"/";
		for(int i = 1;i<paths.length;i++) {
			String item = paths[i];
			fullPath +=item+"/";
			if(obj instanceof Map) {
				if(!((Map)obj).containsKey(item)){
					if(isMustExist) {
						throw new BusinessException("报文协议中必传的节点["+fullPath+"]不存�?");
					}
					return null;
				}
				obj = ((Map)obj).get(item);
				continue;
			} else if(obj instanceof List){
				int idx = 0;
				try{
					idx = Integer.parseInt(item);
				}catch(NumberFormatException e){
					throw new BusinessException("取list中某�?项的值时传入的IDX不是数字");
				}
				obj = ((List)obj).get(idx);
				continue;
			} else if(obj instanceof String) {
				if(isMustExist) {
					if(obj == null || "".equals(String.valueOf(obj))) {
						throw new BusinessException("报文协议中必传的节点["+fullPath+"]不能为空");
					}
				}
				return obj;
			} else {
				return obj;
			}
		}
		if(isMustExist) {
			if(obj == null || "".equals(String.valueOf(obj))) {
				throw new BusinessException("报文协议中必传的节点["+fullPath+"]不能为空");
			}
		}
		return obj;
	}
	
	/**
     * JSON字符串的格式�?
     * @author XIONGPS
     * @param JSON �?要格式的JSON�?
     * @param unit每一层之前的占位符号比如空格 制表�? 
     * @return
     */ 
    public static String formatJson(String jsonStr, String unit) { 
        if (jsonStr == null || jsonStr.trim().length() == 0) { 
            return null; 
        } 
         
        int fixedLenth = 0; 
        ArrayList<String> tokenList = new ArrayList<String>(); 
        { 
            String jsonTemp = jsonStr; 
            //预读�? 
            while (jsonTemp.length() > 0) { 
                String token = getToken(jsonTemp); 
                jsonTemp = jsonTemp.substring(token.length()); 
                token = token.trim(); 
                tokenList.add(token); 
            }            
        } 
         
        for (int i = 0; i < tokenList.size(); i++) { 
            String token = tokenList.get(i); 
            int length = token.getBytes().length; 
            if (length > fixedLenth && i < tokenList.size() - 1 && tokenList.get(i + 1).equals(":")) { 
                fixedLenth = length; 
            } 
        } 
         
        StringBuilder buf = new StringBuilder(); 
        int count = 0; 
        for (int i = 0; i < tokenList.size(); i++) { 
             
            String token = tokenList.get(i); 
             
            if (token.equals(",")) { 
                buf.append(token); 
                doFill(buf, count, unit); 
                continue; 
            } 
            if (token.equals(":")) { 
                buf.append(" ").append(token).append(" "); 
                continue; 
            } 
            if (token.equals("{")) { 
                String nextToken = tokenList.get(i + 1); 
                if (nextToken.equals("}")) { 
                    i++; 
                    buf.append("{ }"); 
                } else { 
                    count++; 
                    buf.append(token); 
                    doFill(buf, count, unit); 
                } 
                continue; 
            } 
            if (token.equals("}")) { 
                count--; 
                doFill(buf, count, unit); 
                buf.append(token); 
                continue; 
            } 
            if (token.equals("[")) { 
                String nextToken = tokenList.get(i + 1); 
                if (nextToken.equals("]")) { 
                    i++; 
                    buf.append("[ ]"); 
                } else { 
                    count++; 
                    buf.append(token); 
                    doFill(buf, count, unit); 
                } 
                continue; 
            } 
            if (token.equals("]")) { 
                count--; 
                doFill(buf, count, unit); 
                buf.append(token); 
                continue; 
            } 
             
            buf.append(token); 
            //左对�? 
            if (i < tokenList.size() - 1 && tokenList.get(i + 1).equals(":")) { 
                int fillLength = fixedLenth - token.getBytes().length; 
                if (fillLength > 0) { 
                    for(int j = 0; j < fillLength; j++) { 
                        buf.append(" "); 
                    } 
                } 
            } 
        } 
        return buf.toString(); 
    } 
     
    private static String getToken(String json) { 
        StringBuilder buf = new StringBuilder(); 
        boolean isInQuotationMarks = false; 
        while (json.length() > 0) { 
            String token = json.substring(0, 1); 
            json = json.substring(1); 
             
            if (!isInQuotationMarks &&  
                    (token.equals(":") || token.equals("{") || token.equals("}")  
                            || token.equals("[") || token.equals("]") 
                            || token.equals(","))) { 
                if (buf.toString().trim().length() == 0) {                   
                    buf.append(token); 
                } 
                 
                break; 
            } 
 
            if (token.equals("\\")) { 
                buf.append(token); 
                buf.append(json.substring(0, 1)); 
                json = json.substring(1); 
                continue; 
            } 
            if (token.equals("\"")) { 
                buf.append(token); 
                if (isInQuotationMarks) { 
                    break; 
                } else { 
                	isInQuotationMarks = true; 
                    continue; 
                }                
            } 
            buf.append(token); 
        } 
        return buf.toString(); 
    } 
 
    private static void doFill(StringBuilder buf, int count, String fillStringUnit) { 
        buf.append("\n"); 
        for (int i = 0; i < count; i++) { 
            buf.append(fillStringUnit); 
        } 
    }
}
