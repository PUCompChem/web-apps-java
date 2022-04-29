package pu.web.cheminfo.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeServlet  extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		      
		      // Set response content type
		      response.setContentType("text/json");

		      // Actual logic goes here.
		      PrintWriter out = response.getWriter();
		      out.println("{ \"CHEMINFO\" : \" ok \"  } ");
		   }
}
