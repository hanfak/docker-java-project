package domain;

public class ClientDBData {
    public final String title;
    public final String body;
    public final Integer userId;
    public final Integer clientId;

    public ClientDBData(String title, String body, Integer userId, Integer clientId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.clientId = clientId;
    }
}
