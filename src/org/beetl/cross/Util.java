package org.beetl.cross;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Util {
	
	public static Object[]  paraseURL(String requestPath){
		ArrayList<String>  sectionPathList = new ArrayList<String>();
		Map<String,String> paras = new HashMap<String,String>();
		char[] cs = requestPath.toCharArray();		
		StringBuilder sb = new StringBuilder();
		/*  //tt/\cccc.html?type=1  */
		boolean sectionStart = false;
		boolean isPathStatus = false;
		for(int i=0;i<cs.length;i++){
			char ch = cs[i];
			if(ch=='/'||ch=='\\'){
				if(!sectionStart) continue;
				if(!isPathStatus) continue; 
				sectionPathList.add(sb.toString());
				sb.setLength(0);
				isPathStatus = false ;
			}else if(ch!='?'){
				if(!sectionStart)sectionStart = true ;
				isPathStatus = true;
				sb.append(ch);
			}else{
				if(cs.length==(i+1)){
					break;
				}else{
					String queryParam = requestPath.substring(i+1);
					String[] params = queryParam.split("&");
					for(String param:params){
						String[] keyValue = param.split("=");
						if(keyValue.length==1) continue;
						paras.put(keyValue[0], keyValue[1]);
					}
					break;
				}
		
			}
		}
		return new Object[]{sectionPathList,paras};
	}
	
}
