package web.jetty.handler;


import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import web.jetty.servlet.HelloKittyServlet;
import web.jetty.servlet.HelloServlet;
import web.jetty.servlet.JsonResponseServlet;

public class HelloJettyHandler {

    public static ServletContextHandler helloServletHandler() {
        ServletContextHandler servletHandler = new ServletContextHandler();
        servletHandler.addServlet(new ServletHolder(new HelloServlet()), "/hello");
        servletHandler.addServlet(new ServletHolder(new HelloKittyServlet()), "/");
        servletHandler.addServlet(new ServletHolder(new JsonResponseServlet()), "/jj");
        return servletHandler;
    }
}
