package wiring;

import infrastructure.jetty.server.BasicServer;
import infrastructure.jetty.web.jetty.handler.JettyHandler;
import infrastructure.properties.PropertiesReader;
import infrastructure.properties.ServerSettings;
import infrastructure.properties.Settings;

public class Ryif {
    public static void main(String[] args) throws Exception {
        Settings settings = new Settings(new PropertiesReader("localhost"));

        BasicServer server = new BasicServer(settings);
        server.withContext(JettyHandler.helloServletHandler(settings));

        server.start();
    }
}
