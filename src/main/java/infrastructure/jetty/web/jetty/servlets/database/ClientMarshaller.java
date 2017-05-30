package infrastructure.jetty.web.jetty.servlets.database;

import domain.Clients;

public class ClientMarshaller {
    public static String marshall(Clients clients) {
        System.out.println(clients.toJson());
        return clients.toJson();
    }
}
