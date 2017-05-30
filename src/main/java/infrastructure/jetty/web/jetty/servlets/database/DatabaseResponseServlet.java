package infrastructure.jetty.web.jetty.servlets.database;

import domain.ClientDBData;
import domain.Clients;

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
//    private JsonMarshaller marshaller;
//    private RetrieveAllUseCase retrieveAllUseCase;
    private  Connection dbConnection;
//    private static Clients clients = new Clients(new ArrayList<>());

    public DatabaseResponseServlet(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Use GET or POST as result may change, thinking POST as request is not idempotent
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get parameter
        String pathInfo = request.getPathInfo();
        // Have to trim last / if written
        String table = pathInfo.substring(pathInfo.lastIndexOf("/") + 1 );
        System.out.println("pathInfo = " + pathInfo);
        System.out.println("table = " + table);
        Clients clients = new Clients(new ArrayList<>());
        // check if parameter has same table if not send 404 and json fail, no table by that name
        try {
            // access query database using parameter
            Statement stmt = dbConnection.createStatement();
            String sqlQuery = "select * from " + table;
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while(rs.next()) {
                // store results in data object
                ClientDBData aClient = new ClientDBData(rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(1));
                clients.addAClient(aClient);
            }
//            dbConnection.close(); // allows to access db an no errors
        } catch(Exception e) {
            System.out.println(e);
        }

        System.out.println(clients);

        // marshall object into json
        sendResponse(response, ClientMarshaller.marshall(clients));

        // database is cleared, but upon refreshing it returns all data
        // data is still persisted in memory, cleared array but only works once
        // is there a better way??
//        clients.clearClients();
        // Without this the object is not cleared, and keeps on adding the results to
        //it if the Clients object is instantiated in the field
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
