package infrastructure.jetty.web.jetty.database;

import domain.Clients;
import infrastructure.jetty.web.jetty.thirdpartyapi.fake.FakeDataRequest;
import org.json.JSONObject;

public class ClientMarshaller {
    public static String marshall(Clients clients) {
        System.out.println(clients.toJson());
        return clients.toJson();
    }
}
