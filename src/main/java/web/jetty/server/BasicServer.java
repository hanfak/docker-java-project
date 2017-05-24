package web.jetty.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import properties.ServerSettings;

public class BasicServer {


    private final Server server;

    public BasicServer(ServerSettings settings) {
        this.server = new Server(settings.serverPort());
    }


    public void withContext(ServletContextHandler servletHandler) {
        server.setHandler(servletHandler);
    }


    public void start() throws Exception {
        server.start();

    }

    public void stop() throws Exception {
        server.stop();
    }

//    public static void main(String[] args) throws Exception {
//        Server server = new Server(this.server.serverPort());
//        server.setHandler(HelloJettyHandler.helloServletHandler());
//        server.start();
//        server.join();
//    }
}
