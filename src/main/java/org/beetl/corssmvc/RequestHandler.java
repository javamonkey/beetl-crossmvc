package org.beetl.corssmvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHandler {
	public void handle(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		response.getWriter().println("hello");
	}
}
