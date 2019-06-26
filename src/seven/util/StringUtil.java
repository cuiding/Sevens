package seven.util;

import java.util.HashMap;
import java.util.Map;


public class StringUtil {

	
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	
	public static boolean isNotEmpty(String str){
		if((str!=null)&&!"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	public static String getParamFromUrl(String url){
		String afterQuestion=url.substring(url.indexOf("?")+1);
		return afterQuestion.substring(afterQuestion.indexOf("=")+1);
	}
}
