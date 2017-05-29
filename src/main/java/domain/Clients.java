package domain;


import infrastructure.jetty.web.Request;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class Clients implements Request{
    private List<ClientDBData> clients;

    public Clients(List<ClientDBData> clients) {
        this.clients = clients;
    }

    public void addAClient(ClientDBData client) {
        clients.add(client);
    }

    public void clearClients() {
        clients.clear();
    }

    @Override
    public String toString() {
        return clients.stream().map(Clients::outputString).collect(Collectors.joining("\n"));
    }


    @Override
    public String toJson() {
        return "[" + clients.stream().map(Clients::outputJson).collect(Collectors.joining(",")) + "]";
    }

    private static String outputString(ClientDBData aClient) {
        return aClient.title + "  "
                + aClient.body + "  "
                + aClient.userId;
    }

    private static String outputJson(ClientDBData aClient) {
        return format("{\"title\": \"%s\"," +
                        " \"body\": \"%s\", " +
                        "\"userId\": %s," +
                        "\"id\": %s}",
                aClient.title, aClient.body, aClient.userId.toString(), aClient.clientId.toString());
    }

}
