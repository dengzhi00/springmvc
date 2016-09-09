package com.yuansq.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static boolean isBlank(String str){
		return !isNotBlank(str);
	}
	public static boolean isNotBlank(String str){
		return !"".equals(str)&&str!=null;
	}

    public static final char openCurly = '{';
    public static final char closeCurly = '}';
    public static final char openSquare = '[';
    public static final char closeSquare = ']';
    public static final char comma = ',';
    public static final char quote = '"';
    public static final char colon = ':';
    public static final char slash = '\\';
    public static final char minus = '-';
    public static final char point = '.';

    /**
     * 字符串为空返回true,否则返回false.
     * @param str
     * @return boolean true:为空
     */
    public static boolean isEmptyStr(String str) {
        return (str == null) || (str.trim().length() == 0);
    }

    /**
     * 数组按指定分隔符拼接成字符串.
     * <P>
     * @param params   数组参数
     * @param delimiter 分隔�?
     * @return String 按分隔符拼接成字符串
     */
    public static String join(Object[] params, String delimiter) {
        if (params != null) {
            StringBuffer paramStr = new StringBuffer("");
            for (Object param : params) {
                paramStr.append(param);
                paramStr.append(delimiter);
            }
            String result = paramStr.toString();
            int srcLen = result.length();
            if (srcLen > 0) {
                result = result.substring(0, srcLen - delimiter.length());
            }
            return result;
        } else {
            return null;
        }
    }

    /**
     * 数组按指定分隔符拼接成字符串.
     * <P>
     * @param list   列表参数
     * @param delimiter 分隔�?
     * @return String 按分隔符拼接成字符串
     */
    public static String join(List <Object>list, String delimiter) {
        if (list != null) {
            StringBuffer paramStr = new StringBuffer("");
            for (Object param : list) {
                paramStr.append(param);
                paramStr.append(delimiter);
            }
            String result = paramStr.toString();
            int srcLen = result.length();
            if (srcLen > 0) {
                result = result.substring(0, srcLen - delimiter.length());
            }
            return result;
        } else {
            return null;
        }
    }

    /**
     * 数组�? 空格 分隔符拼接成字符�?.
     * <P>
     * @param params   数组参数
     * @return String 按空格分隔符拼接成字符串
     */
    public static String join(Object[] params) {
        if (params != null) {
            StringBuffer paramStr = new StringBuffer("");
            for (Object param : params) {
                paramStr.append(param);
                paramStr.append(" ");
            }
            return paramStr.toString().trim();
        } else {
            return null;
        }
    }

    /**
     * 截取字节数，包含中英混合字符�?.
     * @param s 字符�?
     * @param length 长度
     * @return String
     * @throws UnsupportedEncodingException 
     */
    public static String bSubstring(String s, int length) throws UnsupportedEncodingException {
        if (s == null) {
            return null;
        }
        byte[] bytes = s.getBytes("Unicode");
        // 表示当前的字节数
        int n = 0;
        // 要截取的字节数，从第3个字节开�?
        int i = 2;
        for (; i < bytes.length && n < length; i++) {
            // 奇数位置，如3�?5�?7等，为UCS2编码中两个字节的第二个字�?  
            if (i % 2 == 1) {
                // 在UCS2第二个字节时n�?1
                n++;
            } else {
                // 当UCS2编码的第�?个字节不等于0时，该UCS2字符为汉字，�?个汉字算两个字节  
                if (bytes[i] != 0) {
                    n++;
                }
            }
        }
        // 如果i为奇数时，处理成偶数  
        if (i % 2 == 1) {
            // 该UCS2字符是汉字时，去掉这个截�?半的汉字  
            if (bytes[i - 1] != 0) {
                i = i - 1;
            } else {
                // 该UCS2字符是字母或数字，则保留该字�?
                i = i + 1;
            }
        }
        return new String(bytes, 0, i, "Unicode");
    }

    /**
     * 字符串替�?.
     * <P>
     * @param text 原始字符�?
     * @param repl  想被替换的内�?
     * @param with 替换后的内容
     * @return String
     */
    public static String replace(String text, String repl, String with) {
        if (text == null || repl == null || with == null || repl.length() == 0) {
            return text;
        }

        StringBuffer buf = new StringBuffer(text.length());
        int searchFrom = 0;
        while (true) {
            int foundAt = text.indexOf(repl, searchFrom);
            if (foundAt == -1) {
                break;
            }

            buf.append(text.substring(searchFrom, foundAt)).append(with);
            searchFrom = foundAt + repl.length();
        }
        buf.append(text.substring(searchFrom));

        return buf.toString();
    }

    /**
     * 去掉html标签
     * @param html
     * @return String 去掉html标签字符�?
     */
    public static String removeHtml(String html) {
        if (html != null) {
            html = html.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "").replaceAll("</[a-zA-Z]+[1-9]?>", "");
        }
        return html;
    }

    /**
     * 字符串替�?
     * <P>
     *  原始消息=21312{a}  map={"a":"c"} 替换�?  21312c
     * <P>
     * @param message  原始消息
     * @param map�?要替换的参数
     * @return 替换后的
     */
    public static String format(String message, Map<Object,Object> map) {
        return format(message, map, null);
    }

    /**
     * 字符串替�?
     * <P>
     *  原始消息=21312{a}  map={"a":"c"} 替换�?  21312c
     * <P>
     * @param message  原始消息
     * @param map�?要替换的参数
     * @param replace�?找不到替换�?�，默认替换成的�?
     * @return 替换后的
     */
    public static String format(String message, Map<Object,Object> map, String replace) {
        StringBuffer newMessage = new StringBuffer("");
        if (message != null) {
            int start = message.indexOf("{");
            if (start > 0) {
                int end = message.indexOf("}", start);
                if (end > 0) {
                    String key = message.substring(start + 1, end);
                    if (map != null && map.containsKey(key)) {
                        newMessage.append(message.substring(0, start));
                        newMessage.append(String.valueOf(map.get(key)));
                        newMessage.append(message.substring(end + 1));
                        return format(newMessage.toString(), map, replace);
                    } else if (replace != null) {
                        newMessage.append(message.substring(0, start));
                        newMessage.append(replace);
                        newMessage.append(message.substring(end + 1));
                        return format(newMessage.toString(), map, replace);
                    } else {
                        return message;
                    }
                } else {
                    return message;
                }
            } else {
                return message;
            }
        } else {
            return message;
        }
    }

    /**
     * 通过根目录和文件路径获取绝对路径
     * @param dir 根目�?
     * @param path 路径
     * @return String 绝对路径
     */
    public static String getFilePath(String dir, String path) {
        int dirIndex = dir.lastIndexOf("/");
        int pathIndex = path.indexOf("/");
        //首尾都有,去掉�?�?
        if (dirIndex > 0 && dirIndex == (dir.length() - 1) && pathIndex == 0) {
            return dir.substring(0, dirIndex) + path;
        }
        //都没�?
        if (dirIndex != (dir.length() - 1) && pathIndex != 0) {
            return dir + "/" + path;
        } else {
            return dir + path;
        }
    }

    /**
     * 去掉回车,换行,Tab
     * @param text
     * @return
     */
    public static String removeFormat(String text) {
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(text);
        return m.replaceAll("");
    }

    /**
     * 判断是否是数字，包含小数点数�?
     * @param str�?入参
     * @return�?boolean true:是数�?
     */
    public static boolean isDigits(String str) {
        if (isEmptyStr(str)) {
            return false;
        }
        int point = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.' && i > 0 && i != str.length() - 1) {
                point = point + 1;
                if (point == 1) {
                    continue;
                }
            }
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 对key添加双引�?
     * @param keys key
     * @return String 对key添加双引号后字符�?
     */
    private static String keyAddQuote(String keys) {
        StringBuilder build = new StringBuilder();
        int i = 0;
        int startIndex = 0;
        int endIndex = 0;
        int beign = 0;
        boolean end = false;
        while (i < keys.length()) {
            char ch = keys.charAt(i);
            switch (ch) {
                case colon: {
                    endIndex = i;
                    end = true;
                    break;
                }
                case comma:
                case openCurly: {
                    if (!end) {
                        startIndex = i;
                    }
                    break;
                }
            }
            i++;
            if (end && startIndex < endIndex) {
                String key = keys.substring(startIndex + 1, endIndex);
                build.append(keys.substring(beign, startIndex + 1));
                build.append(quote);
                build.append(key);
                build.append(quote);
                beign = endIndex;
                end = false;
            }
        }
        build.append(colon);
        return build.toString();
    }

    /**
     * 将Map字符串格式转换成JSon字符串格�?
     * @param mapFormat
     * @return String 转换成JSon字符串格�?
     */
    public static String mapToJson(String mapFormat) {
        String jsonFormat = mapFormat.replaceAll("=", ":");
        jsonFormat = removeFormat(jsonFormat);
        StringBuilder build = new StringBuilder();
        int i = 0;
        int startIndex = 0;
        int endIndex = 0;
        int beign = 0;
        boolean start = false;
        while (i < jsonFormat.length()) {
            char ch = jsonFormat.charAt(i);
            switch (ch) {
                case colon: {
                    startIndex = i;
                    start = true;
                    break;
                }
                case comma:
                case closeCurly: {
                    if (start) {
                        endIndex = i;
                    }
                    break;
                }
            }
            i++;
            if (start && startIndex < endIndex) {
                String startString = jsonFormat.substring(beign, startIndex + 1);
                build.append(keyAddQuote(startString));
                String value = jsonFormat.substring(startIndex + 1, endIndex);
                if (isDigits(value)) {
                    build.append(value);
                } else {
                    build.append(quote);
                    build.append(value);
                    build.append(quote);
                }
                beign = endIndex;
                start = false;

            }
        }
        build.append(jsonFormat.substring(beign));

        return build.toString();
    }

    /**
     * inputStream转换String
     * @param is InputStream
     * @param charName 默认�? UTF-8
     * @return String
     */
    public static String is2String(InputStream is, String charName) {
        InputStreamReader isr = null;
        int i = -1;
        char[] b = new char[1024];
        StringBuffer sb = new StringBuffer();
        try {
            isr = new InputStreamReader(is, charName);
            while ((i = isr.read(b)) != -1) {
                sb.append(new String(b, 0, i));
            }
        } catch (IOException e) {
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {}
            }
        }
        String content = sb.toString();
        return content;
    }

    /**
     * inputStream转换String
     * @param is InputStream
     * @return String
     */
    public static String is2String(InputStream is) {
        return is2String(is, "UTF-8");
    }

    /**
     * 指定长度替换内容
     * @param src
     * @param begin
     * @param end
     * @param replaceStr
     * @return
     * @see
     */
    public static String replaceSubStr(String src, int begin, int end, String replaceStr) {
        StringBuffer rp = new StringBuffer(100);
        if (src == null || "".equals(src))
            return "";
        if (replaceStr == null || "".equals(replaceStr))
            return src;
        if (src.length() < begin || src.length() < end || begin < 0 || end < 0)
            return src;
        for (int i = 0; i < src.length(); i++) {
            if (i < begin || i > end)
                rp.append(src.charAt(i));
            else
                rp.append(replaceStr);
        }
        return rp.toString();

    }
    
    public static String transforLowerCase(String str) {
        String returnStr = "";
        String a[] = str.split("_");
        if (a.length == 1) {
            return str.toLowerCase();
        }
        for (int i = 0; i < a.length; i++) {
            if (i == 0) {
                returnStr = a[i].toLowerCase();
            } else {
                returnStr = returnStr + a[i].substring(0, 1).toUpperCase() + a[i].substring(1).toLowerCase();
            }
        }
        return returnStr;
    }
   /**
    * 数组扩容
    * @param source
    * @param template
    * @return
    */
    private static String[] arrayCapacity(String[] source,String[] template){
    	String[] result=new String[source.length+template.length];
    	for(int i=0;i<result.length;i++){
    			result[i]=i<source.length?source[i]:template[i-source.length];
    	}
    	return result;
    }
    /**
     * 阿拉伯正整数转中文小�? 
     * @param number
     * @return
     */
	public static String transition(String number) {
		String [] a = { "", "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?" };
		String [] b = { "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?" };
		String [] c ={"�?", "�?", "�?", "�?","�?", "�?", "�?", "�?"};
		//去除非数字与前面多余�?"0"
		number=Pattern.compile("[^0-9]").matcher(number).replaceAll("").trim();
		number=number.replaceAll("^[0]+", "");
		number=number.equals("")?"0":number;
		//arr存储的为阿拉伯数字对应的ASCII�?
		char[] arr = number.toCharArray();
		String result = "";
		if (arr.length > 1) {
			for (int i = arr.length - 1, j = 0; i >= 0; i--, j++) {
				String res = "";
				//扩容
				a=j>=a.length?arrayCapacity(a,c):a;
				if (j % 4 == 0) {
					res = b[arr[i] - 48].equals(b[0]) ? a[j] : b[arr[i] - 48] + a[j];
				} else if (j % 4 == 1) {
					res = b[arr[i + 1] - 48].equals(b[0]) ? (b[arr[i] - 48].equals(b[0]) ? "" : b[arr[i] - 48] + a[j])
							: b[arr[i] - 48].equals(b[0]) ? b[0] : b[arr[i] - 48] + a[j];
				} else {
					res = b[arr[i] - 48].equals(b[0]) ? b[0] : b[arr[i] - 48] + a[j];
				}
				result = res + result;
			}
			result = result.replaceAll("[〇]+", b[0]);
			result = result.replaceAll("�?$", "");
			result = result.replaceAll("〇万", "�?");
			result = result.replaceAll("〇亿", "�?");
			result = result.replaceAll("亿万", "�?");
		} else if (arr.length == 1) {
			result = b[arr[0] - 48];
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(transition("1群问哦扔�?56赢u�?12文娥娥额1"));
	}

}
