package web.jetty.servlet;

import org.json.JSONObject;
import sun.misc.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class HelloKittyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("Hello Kitty!!!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        JSONObject jsonObject = new JSONObject(body);
        System.out.println("");
        System.out.println(jsonObject.get("events"));
        response.setContentType("application/json");
        jsonObject.write(response.getWriter());
    }
}
