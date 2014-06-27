package org.beetl.corssmvc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jdom2.Element;

import pygmy.core.HttpRequest;
import pygmy.core.HttpResponse;

public class Controller {
	DomainBaseConfig config ;
	String requestPath;
	String responsePath;
	String modelPath;
	String responseJson;
	String runScript ;
	String method ;
	Map<String,String> paras = new HashMap<String,String>();
	Set<String> importValues = new HashSet<String>();
	
	
	
	public Controller(Element el,DomainBaseConfig config ){
		this.config = null;
		this.requestPath = el.getAttributeValue("request");
		int index = 0;
		if((index=requestPath.indexOf("?"))!=-1){
			String queryString = requestPath.substring(index+1);
			
			String[] parasString = queryString.split("&");
			for(String paraItem:parasString){
				String[] para = paraItem.split("=");
				paras.put(para[0], para[1]);
			}
			
			requestPath = requestPath.substring(0,index);
		}
		
	
		
		this.responsePath = el.getAttributeValue("response");
		this.modelPath = el.getAttributeValue("model");
		this.method = el.getAttributeValue("method");
		this.responseJson = el.getAttributeValue("responseJson");
		this.runScript = el.getAttributeValue("runScript");
		String impt = el.getAttributeValue("import");
		if(impt!=null)importValues.add(impt);
		List<Element> paramsEle = el.getChildren("param");
		for(Element paraEle:paramsEle){
			paras.put(paraEle.getAttributeValue("name"), paraEle.getAttributeValue("value"));
		}
		
		List<Element> imptsEle = el.getChildren("import");
		for(Element imptEle:imptsEle){
			importValues.add(imptEle.getAttributeValue("value"));
		}
		
		
		this.requestPath = config.getBasePath()+this.requestPath;
		this.responsePath = config.getBasePath()+this.responsePath;
		this.modelPath = config.getBaseModelPath()+modelPath;
		
	}
	
	public void execute(HttpRequest request, HttpResponse response){
		
	}
	
	/** 逐个验证请求是否能被此Controller处理
	 * @param request
	 * @return
	 */
	protected boolean isMatch(HttpRequest request){
		if(request.getUrl().equals(this.requestPath)){
			if(this.method!=null&&!method.equalsIgnoreCase(request.getMethod())){
				return false ;
			}
			
			if(!this.paras.isEmpty()){
				for(Entry<String,String> entry:paras.entrySet()){
					String real = request.getParameter(entry.getKey());
					if(entry.getValue().equals(real)){
						continue ;
					}else{
						return false;
					}
				}
			}
			
			return true ;
			
		}else{
			return false ;
		}
	}
}
