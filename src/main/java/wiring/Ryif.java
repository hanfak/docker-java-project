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
        Settings settings = new Settings(new PropertiesReader("localhost"));
        DatabaseConnectionManager databaseConnectionManager = new DatabaseConnectionManager(settings);
        Connection dbConnection = databaseConnectionManager.getDBConnection();

        BasicServer server = new BasicServer(settings);
        server.withContext(JettyHandler.helloServletHandler(dbConnection));

        server.start();
    }
}
