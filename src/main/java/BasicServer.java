import HelloJettyHandler.HelloJettyHandler;
import org.eclipse.jetty.server.Server;

public class BasicServer {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        Server server = new Server(port);
        server.setHandler(new HelloJettyHandler());
        server.start();
        server.join();
    }


}
