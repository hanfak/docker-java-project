package domain;


import java.util.List;
import java.util.stream.Collectors;

public class Clients {
    private List<ClientDBData> clients;

    public Clients(List<ClientDBData> clients) {
        this.clients = clients;
    }

    public void addAClient(ClientDBData client) {
        clients.add(client);
    }

    @Override
    public String toString() {
        return clients.stream().map(Clients::outputString).collect(Collectors.joining("\n"));
    }

    private static String outputString(ClientDBData aClient) {
        return aClient.title + "  "
                + aClient.body + "  "
                + aClient.userId;
    }

}
