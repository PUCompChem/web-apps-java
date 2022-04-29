package pu.web.cheminfo.servlets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import org.openscience.cdk.interfaces.IAtomContainer;

import ambit2.smarts.IsomorphismTester;
import ambit2.smarts.SmartsHelper;
import ambit2.smarts.SmartsParser;
import ambit2.smarts.groups.GroupMatch;
import pu.web.cheminfo.ChemInfoServer;

public class MoleculeServlet extends HttpServlet {

	List<String> errors = new ArrayList<String>();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		errors.clear();
		
		// Set response content type
		response.setContentType("text/html");


		PrintWriter out = response.getWriter();
		String title = "Molecule";
		String docType =
				"<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		
		
		IAtomContainer mol = createMolecule(request);
		
		out.println(docType +
				"<html>\n" +
				"<head><title>" + title + "</title></head>\n" +
				"<body bgcolor = \"#f0f0f0\">\n" +
				"<h1 align = \"center\">" + title + "</h1>\n");

		if (!errors.isEmpty())
			out.println(getErrorOut());
		else
			out.println(getMoleculeOut(mol));

		out.println(
				"</body>" +
				"</html>");
	}

	public void destroy() {
		// do nothing.
	}


	public IAtomContainer createMolecule(HttpServletRequest request) 
	{
		//Try to create molecule from smiles
		String smiles = request.getParameter("smiles");
		if (smiles != null)
		try {
			IAtomContainer mol = SmartsHelper.getMoleculeFromSmiles(smiles, true);
			mol.setProperty("M_SMILES", smiles);
			
			//Register molecule
			ChemInfoServer.defaultServer.addMolecule(mol);			
			return mol;
		} 
		catch (Exception e) {
			errors.add( "Cdk error smiles processing: " +smiles);			
		}
		
		//Try to get existing molecule from server
		String id = request.getParameter("id");
		if (id != null) {
			IAtomContainer mol = ChemInfoServer.defaultServer.getMoleculeById(id);
			if (mol == null)
				errors.add( "Incorrect molecule id. Molecule with id = " + id + " does not exists!");	
			return mol;
				
		}
		
		
		errors.add("Missing parameters for molecule creation !");
		return null;
	}
	
	public String getErrorOut() {
		String errOut = "<ul>\n";
		for (String err : errors)
			errOut += "  <li><b> " + err + "</b>";
		
		errOut += "</ul>\n";
		return errOut;
	}
	
	public String getMoleculeOut(IAtomContainer mol) {		
		String errOut = "<ul>\n";
		
		String smiles = mol.getProperty("M_SMILES");
		if (smiles != null)
			errOut += "  <li><b> SMILES : "  + smiles + "</b>";
		errOut += "  <li><b> Number of atoms : "  + mol.getAtomCount() + "</b>";
		errOut += "  <li><b> Number of bonds : "  + mol.getBondCount() + "</b>";
		
		errOut += "</ul>\n";
		return errOut;
	}

}