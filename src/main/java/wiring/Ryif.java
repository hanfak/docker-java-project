package wiring;

import infrastructure.jetty.web.jetty.handler.JettyHandler;
import infrastructure.properties.PropertiesReader;
import infrastructure.properties.ServerSettings;
import infrastructure.properties.Settings;
import infrastructure.jetty.server.BasicServer;

public class Ryif {
    public static void main(String[] args) throws Exception {
        ServerSettings settings = new Settings(new PropertiesReader("localhost"));

        BasicServer jettyServer = new BasicServer(settings);
        jettyServer.withContext(JettyHandler.helloServletHandler());

        jettyServer.start();
    }
}
