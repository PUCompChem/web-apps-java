package pu.web.cheminfo.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openscience.cdk.interfaces.IAtomContainer;

import pu.web.cheminfo.ChemInfoServer;

public class MoleculesServlet extends HttpServlet 
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		
		// Set response content type
		response.setContentType("text/html");


		PrintWriter out = response.getWriter();
		String title = "Molecule list";
		String docType =
				"<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		
		
		
		
		out.println(docType +
				"<html>\n" +
				"<head><title>" + title + "</title></head>\n" +
				"<body bgcolor = \"#f0f0f0\">\n" +
				"<h1 align = \"center\">" + title + "</h1>\n");

		out.println(getMoleculeListOut());
					
		out.println(
				"</body>" +
				"</html>");
	}
	
	public String getMoleculeListOut() {		
		StringBuffer sb = new StringBuffer();
		sb.append("<ul>\n");
		
		for (IAtomContainer mol : ChemInfoServer.defaultServer.molecules) {					
			sb.append("  <li><b>  "  + mol.getProperty("M_ID") 
				+ "  " + mol.getProperty("M_SMILES") + "</b>\n");
		}
		
		sb.append("</ul>\n");
		return sb.toString();
	}
	
}
