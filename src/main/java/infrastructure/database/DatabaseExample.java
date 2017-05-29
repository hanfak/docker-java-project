package infrastructure.database;

import infrastructure.properties.PropertiesReader;
import infrastructure.properties.Settings;

import java.sql.*;

public class DatabaseExample {
    private final Settings settings;

    public DatabaseExample(Settings settings) {
        this.settings = settings;
    }

    public static void main(String args[]){
        Settings settings =  new Settings(new PropertiesReader("localhost"));
        try {
            Connection con = DriverManager.getConnection(
                    settings.databaseURL() + "tutorial_database", settings.databaseUsername(), settings.databasePassword());

            Statement stmt = con.createStatement();
            String sqlQuery = "select * from clients";
            ResultSet rs = stmt.executeQuery(sqlQuery);
            // Do this functionally
            // http://www.jooq.org/java-8-and-sql
            while(rs.next())
                System.out.println(rs.getString(2) + "  "
                        + rs.getString(3) + "  "
                        + rs.getInt(4));
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
