package infrastructure.database;

import infrastructure.properties.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private static Settings settings;

    public DatabaseConnectionManager(Settings settings) {
        this.settings = settings;
    }

    public Connection getDBConnection() {
        try {
            //autoReconnect -> must be better way of avoid connection closed error???
            Connection con = DriverManager.getConnection(
                    settings.databaseURL() + "tutorial_database?autoReconnect=true", settings.databaseUsername(), settings.databasePassword());
            return con;
        } catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
