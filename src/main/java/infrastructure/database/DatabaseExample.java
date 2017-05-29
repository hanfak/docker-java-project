package infrastructure.database;

import domain.ClientDBData;
import domain.Clients;
import infrastructure.properties.PropertiesReader;
import infrastructure.properties.Settings;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseExample {
    private static Settings settings;
    private static Clients clients = new Clients(new ArrayList<>());

    public static void main(String args[]){
        settings = new Settings(new PropertiesReader("localhost"));
        try {
            Connection con = DriverManager.getConnection(
                    settings.databaseURL() + "tutorial_database", settings.databaseUsername(), settings.databasePassword());

            Statement stmt = con.createStatement();
            String sqlQuery = "select * from clients";
            ResultSet rs = stmt.executeQuery(sqlQuery);
            // Do this functionally
            // http://www.jooq.org/java-8-and-sql
            while(rs.next()) {
                ClientDBData aClient = new ClientDBData(rs.getString(2),
                                                        rs.getString(3),
                                                        rs.getInt(4),
                                                        rs.getInt(1));
                clients.addAClient(aClient);
            }
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }

        System.out.println(clients);
    }
}
