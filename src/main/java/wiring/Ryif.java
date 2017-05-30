package wiring;

import infrastructure.database.DatabaseConnectionManager;
import infrastructure.jetty.web.jetty.handler.JettyHandler;
import infrastructure.properties.PropertiesReader;
import infrastructure.properties.ServerSettings;
import infrastructure.properties.Settings;
import infrastructure.jetty.server.BasicServer;

import java.sql.Connection;

public class Ryif {
    public static void main(String[] args) throws Exception {
        ServerSettings settings = new Settings(new PropertiesReader("localhost"));

        BasicServer server = new BasicServer(settings);
        server.withContext(JettyHandler.helloServletHandler());

        server.start();
    }
}
