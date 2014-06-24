package org.beetl.corssmvc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ConfigServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new RequestHandler().handle(request, response);
	}

	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new RequestHandler().handle(request, response);
	}

}
