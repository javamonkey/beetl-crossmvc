package org.beetl.cross;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import pygmy.core.AbstractHandler;
import pygmy.core.HttpRequest;
import pygmy.core.HttpResponse;
import pygmy.core.Server;

public class HttpHandler  extends AbstractHandler{

	private String root = null;
	private String userDir = null;
	public HttpHandler(){
		
	}
	
	 public boolean initialize(String handlerName, Server server){
		 	super.initialize(handlerName, server);
			userDir = System.getProperty("user.dir");
		    String templateRoot = this.server.getProperty("root.template");
		    
		    if(templateRoot==null){
		    	root = userDir ;
		    }else{
		    	root = userDir+File.separator+templateRoot;
		    }
		   return true ;
	 }
	protected boolean handleBody(HttpRequest request, HttpResponse response) throws IOException {
	    String url = request.getUrl();
	
	    if(url.endsWith(".jpg")||url.endsWith(".css")||url.endsWith(".gif")||url.endsWith(".png")){
	    	//静态资源
	    	FileInputStream ins = new FileInputStream(new File(userDir,url));
	    	String mimeType = getMimeType(url);
	    	response.setMimeType(mimeType);
	        response.sendResponse(ins, -1);
	        return true;
	    }
	   // 查看是否有控制代码
	    
	    return true;
	  }
}
