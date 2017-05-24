import org.eclipse.jetty.server.Server;

public class BasicServer {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        Server server = new Server(port);
        server.start();
    }
}
