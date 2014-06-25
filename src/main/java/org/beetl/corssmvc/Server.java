package org.beetl.corssmvc;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class Server 
{
	
	static ClassLoader loader = null;
    public static void main( String[] args ) throws Exception
    {
    	String appRoot = System.getProperty("user.home");
    	String configPath = appRoot+File.separator+"config.properties";
    	String beetlConfig = appRoot+File.separator+"beetl.properties";
    	
    	Config conf = new Config(configPath);
    	String codePath = conf.get("code");
    	String tempatePath = conf.get("page");
    	codePath = codePath.replaceAll("${user.home}", appRoot);
    	tempatePath = tempatePath.replaceAll("${user.home}", appRoot);
    	loader = newLoader(codePath);
    	loader.loadClass("or")
    	
    	
    	int port = conf.getInt("port", 7001);
    	startServer(port);
    	
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
    	
    	URLClassLoader loader = new URLClassLoader(cp.toArray(new URL[0]),Server.class.getClassLoader());
    	return loader;
    	
    }
    
    public static void startServer(int port) throws Exception{
    	DeploymentInfo servletBuilder = Servlets.deployment()
    	        .setClassLoader(Server.class.getClassLoader())
    	        .setContextPath("/")
    	        .setDeploymentName("crossmvc.war")
    	        .addServlets(
    	                Servlets.servlet("IndexServlet", IndexServlet.class)    	                        
    	                        .addMapping("/*"));

    	DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
    	manager.deploy();
    	PathHandler path = Handlers.path(Handlers.redirect("/"))
    	        .addPrefixPath("/", manager.start());

    	Undertow server = Undertow.builder()
    	        .addHttpListener(port, "localhost")
    	        .setHandler(path)
    	        .build();
    	server.start();
    }
}
