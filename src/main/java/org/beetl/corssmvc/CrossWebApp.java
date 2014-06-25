package org.beetl.corssmvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.FileResourceLoader;

public class CrossWebApp {
	
	private GroupTemplate templateGt;
	private GroupTemplate codeGt;
	private Configuration conf;
	
	private CrossWebApp(String templateRoot,String codeRoot,String configPath) throws Exception{
		
		
		conf = new Configuration();
		conf.add(configPath);
		FileResourceLoader templateFileLoader = new FileResourceLoader(templateRoot);
		templateGt = new GroupTemplate(templateFileLoader,conf);
		
		FileResourceLoader codeFileLoader = new FileResourceLoader(codeRoot);
		templateGt = new GroupTemplate(templateFileLoader,conf);
		
	}
	
	
	
	public String renderTemplate(HttpServletRequest request, HttpServletResponse response,String template,Map<String,Object> values){
		return null;
	}
	
	public void runScript(HttpServletRequest request, HttpServletResponse response,String scriptPath){
		;
	}
}
