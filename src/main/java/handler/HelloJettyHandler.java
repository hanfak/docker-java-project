package handler;


import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.HelloKittyServlet;
import servlets.HelloServlet;

public class HelloJettyHandler {

    public static ServletContextHandler helloServletHandler() {
        ServletContextHandler servletHandler = new ServletContextHandler();
        servletHandler.addServlet(new ServletHolder(new HelloServlet()), "/hello");
        servletHandler.addServlet(new ServletHolder(new HelloKittyServlet()), "/");
        return servletHandler;
    }
}
