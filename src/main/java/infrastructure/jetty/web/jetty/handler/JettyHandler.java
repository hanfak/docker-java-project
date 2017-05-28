package infrastructure.jetty.web.jetty.handler;


import infrastructure.jetty.web.jetty.starwars.ThirdPartyAPIResponseServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import infrastructure.jetty.web.jetty.hellokitty.HelloKittyServlet;
import infrastructure.jetty.web.jetty.hello.HelloServlet;
import infrastructure.jetty.web.jetty.json.JsonResponseServlet;

import static wiring.RyifURLs.*;

public class JettyHandler {

    public static ServletContextHandler helloServletHandler() {
        ServletContextHandler servletHandler = new ServletContextHandler();
        servletHandler.addServlet(new ServletHolder(new HelloServlet()), HELLO_PATH);
        servletHandler.addServlet(new ServletHolder(new HelloKittyServlet()), HELLO_KITTY_PATH);
        servletHandler.addServlet(new ServletHolder(new JsonResponseServlet()), JSON_PATH);
        servletHandler.addServlet(new ServletHolder(new ThirdPartyAPIResponseServlet()), STAR_WARS_PATH);
        return servletHandler;
    }
}
