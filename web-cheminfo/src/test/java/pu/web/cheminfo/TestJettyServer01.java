package pu.web.cheminfo;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

import pu.web.cheminfo.servlets.MoleculeServlet;

public class TestJettyServer01 {

	public class JettyServer {
		private Server server;

		public void start() throws Exception {
			server = new Server();
			ServerConnector connector = new ServerConnector(server);
			connector.setPort(8090);
			server.setConnectors(new Connector[] {connector});
		}
	}


	public static void main(String[] args)  throws Exception
	{
		//Setup Jetty server
		Server server = new Server();
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(8080);
		server.setConnectors(new Connector[] {connector});        
		ServletHandler servletHandler = new ServletHandler();
		server.setHandler(servletHandler);
		
		//Servlet mappings
		servletHandler.addServletWithMapping(MoleculeServlet.class, "/molecule");


		server.start();

	}

}



