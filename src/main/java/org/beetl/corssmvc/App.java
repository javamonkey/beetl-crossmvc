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
    	        .setContextPath("/myapp")
    	        .setDeploymentName("test.war")
    	        .addServlets(
    	                Servlets.servlet("IndexServlet", IndexServlet.class)    	                        
    	                        .addMapping("/*"));

    	DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
    	manager.deploy();
    	PathHandler path = Handlers.path(Handlers.redirect("/myapp"))
    	        .addPrefixPath("/myapp", manager.start());

    	Undertow server = Undertow.builder()
    	        .addHttpListener(8080, "localhost")
    	        .setHandler(path)
    	        .build();
    	server.start();
    }
}
