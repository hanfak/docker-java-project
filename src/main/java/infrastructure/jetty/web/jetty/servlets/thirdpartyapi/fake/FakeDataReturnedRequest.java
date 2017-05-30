package infrastructure.jetty.web.jetty.servlets.thirdpartyapi.fake;

import infrastructure.jetty.web.Request;

import static java.lang.String.format;

public class FakeDataReturnedRequest implements Request {
    public final String title;
    public final String jsonBody;
    public final Integer userId;
    public final Integer id;

    public FakeDataReturnedRequest(String title, String jsonBody, Integer userId, Integer id) {
        this.title = title;
        this.jsonBody = jsonBody;
        this.userId = userId;
        this.id = id;
    }

    @Override
    public String toJson() {
        return format("{\"title\": \"%s\"," +
                        " \"body\": \"%s\", " +
                        " \"id\": \"%s\", " +
                        "\"userId\": %s}",
                title, jsonBody, userId.toString(), id.toString());
    }
}
