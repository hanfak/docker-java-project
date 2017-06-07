package infrastructure.jetty.web.jetty.servlets.database;

import domain.ClientDBData;
import domain.Clients;
import infrastructure.database.DatabaseConnectionManager;
import infrastructure.properties.PropertiesReader;
import infrastructure.properties.Settings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

// use parameters to choose either cat or client table
// Get all data from choosen table
// response in json or xml
public class DatabaseResponseServlet extends HttpServlet {
    private final Settings settings;

    public DatabaseResponseServlet(Settings settings) {

        this.settings = settings;
    }
//    private JsonMarshaller marshaller;
//    private RetrieveAllUseCase retrieveAllUseCase;

    // Use GET or POST as result may change, thinking POST as request is not idempotent
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("asdfa");

        String pathInfo = request.getPathInfo();
//        System.out.println(request.getPathInfo());
        System.out.println(request.getQueryString());

        // Have to trim last / if written
        String table = pathInfo.substring(pathInfo.lastIndexOf("/") + 1 );

        Clients clients = new Clients(new ArrayList<>());
        // check if parameter has same table if not send 404 and json fail, no table by that name
        try {
            DatabaseConnectionManager databaseConnectionManager = new DatabaseConnectionManager(settings);
            Connection dbConnection = databaseConnectionManager.getDBConnection();
            Statement stmt = dbConnection.createStatement();
            String sqlQuery = "select * from " + table;
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while(rs.next()) {
                ClientDBData aClient = new ClientDBData(rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(1));
                clients.addAClient(aClient);
            }

            ;
        } catch(Exception e) {
            System.out.println(e);
        }

        System.out.println(clients);

        sendResponse(response, ClientMarshaller.marshall(clients));
    }


    // Should I pass String or JsonObject??
    // send response back
    private void sendResponse(HttpServletResponse response, String jsonString) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write(jsonString);
        response.setStatus(201);
    }



        // if empty table, send {} or message

    // Connection closes, cannot update table and run search again
}
