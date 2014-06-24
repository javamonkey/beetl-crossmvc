package org.beetl.corssmvc;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	DeploymentInfo servletBuilder = Servlets.deployment()
    	        .setClassLoader(App.class.getClassLoader())
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
    	        .addHttpListener(7001, "localhost")
    	        .setHandler(path)
    	        .build();
    	server.start();
    }
}
