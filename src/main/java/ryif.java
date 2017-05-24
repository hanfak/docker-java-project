import web.jetty.handler.HelloJettyHandler;
import properties.PropertiesReader;
import properties.ServerSettings;
import properties.Settings;
import web.jetty.server.BasicServer;

public class ryif {
    public static void main(String[] args) throws Exception {
        ServerSettings settings = new Settings(new PropertiesReader("localhost"));

        BasicServer jettyServer = new BasicServer(settings);
        jettyServer.withContext(HelloJettyHandler.helloServletHandler());

        jettyServer.start();
    }
}
