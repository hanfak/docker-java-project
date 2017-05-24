package web.jetty.servlet;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonResponseServlet extends HttpServlet  {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonString = "{" +
                "    \"aggregateName\": \"HydraPortInMsisdnListRetrievedAggregate\"," +
                "    \"entityId\": {" +
                "        \"portInMsisdnListRetrievedEventExternalId\" : 1" +
                "    }," +
                "    \"events\": " +
                "       [" +
                "        {" +
                "           \"eventType\": \"PortInMsisdnListRetrievedEvent\"," +
                "           \"portInMsisdnListRetrievedEventExternalId\" : 2," +
                "           \"retrievedAt\": \"%s\"" +
                "        }" +
                "       ]" +
                "    }" +
                "}";
        response.setContentType("application/json");
        JSONObject jsonObject = new JSONObject(jsonString);
        jsonObject.write(response.getWriter());
    }
}
