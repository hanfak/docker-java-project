package infrastructure.jetty.web.jetty.handler;


import infrastructure.jetty.web.jetty.database.DatabaseResponseServlet;
import infrastructure.jetty.web.jetty.thirdpartyapi.fake.FakeAPIResponseServlet;
import infrastructure.jetty.web.jetty.thirdpartyapi.fake.FakeDataUnmarshaller;
import infrastructure.jetty.web.jetty.thirdpartyapi.starwars.ThirdPartyAPIResponseServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import infrastructure.jetty.web.jetty.hellokitty.HelloKittyServlet;
import infrastructure.jetty.web.jetty.hello.HelloServlet;
import infrastructure.jetty.web.jetty.json.JsonResponseServlet;

import java.sql.Connection;

import static wiring.RyifURLs.*;

public class JettyHandler {

    public static ServletContextHandler helloServletHandler(Connection dbConnection) {
        ServletContextHandler servletHandler = new ServletContextHandler();
        servletHandler.addServlet(new ServletHolder(new HelloServlet()), HELLO_PATH);
        servletHandler.addServlet(new ServletHolder(new HelloKittyServlet()), HELLO_KITTY_PATH);
        servletHandler.addServlet(new ServletHolder(new JsonResponseServlet()), JSON_PATH);
        servletHandler.addServlet(new ServletHolder(new ThirdPartyAPIResponseServlet()), STAR_WARS_PATH);
        servletHandler.addServlet(new ServletHolder(new FakeAPIResponseServlet(new FakeDataUnmarshaller())), FAKE_API_PATH);
        servletHandler.addServlet(new ServletHolder(new DatabaseResponseServlet(dbConnection)), DATABASE_PATH);
        return servletHandler;
    }
}
