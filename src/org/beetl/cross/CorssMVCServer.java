package org.beetl.cross;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import pygmy.core.Server;

public class CorssMVCServer 
{
	
	static ClassLoader loader = null;
	static Config conf;
    public static void main( String[] args ) throws Exception
    {
    	String appRoot = System.getProperty("user.dir");
    	String configPath = appRoot+File.separator+"config.properties";
    	String beetlConfig = appRoot+File.separator+"beetl.properties";
    	
    	conf = new Config(configPath);
    	String codePath = conf.get("code",appRoot);
    	String tempatePath = conf.get("template",appRoot);
    	
    	startServer();
    	
    }
    
    public static ClassLoader newLoader(String codeRoot) throws Exception{
    	String classPath = codeRoot+File.separator+"classes/";
    	String libPath = codeRoot+File.separator+"lib";
    	List<URL> cp = new ArrayList<URL>();
    	URL classURL = new File(classPath).toURL();
    	cp.add(classURL);
    	File[] list = new File(libPath).listFiles(new FilenameFilter(){

			public boolean accept(File dir, String name) {
				if(name.endsWith(".jar")){
					return true;
				}else{
					return false ;
				}
			}
    		
    	});
    	
    	for(File f:list){
    		cp.add(f.toURL());
    	}
    	
    	URLClassLoader loader = new URLClassLoader(cp.toArray(new URL[0]),CorssMVCServer.class.getClassLoader());
    	return loader;
    	
    }
    
    public static void startServer() throws Exception{
    	  Server server = new Server( conf.ps );
          try {
              server.start();
              synchronized( server ) {
                  server.wait();
              }
          } catch (InterruptedException e) {
             throw e ;
          }
    }
}
