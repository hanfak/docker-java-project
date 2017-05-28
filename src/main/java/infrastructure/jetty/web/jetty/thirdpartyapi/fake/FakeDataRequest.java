package infrastructure.jetty.web.jetty.thirdpartyapi.fake;

import infrastructure.jetty.web.Request;

import static java.lang.String.format;
//move to domain
public class FakeDataRequest implements Request {
    public final String title;
    public final String body;
    public final Integer userId;

    public FakeDataRequest(String title, String body, Integer userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    @Override
    public String toJson() {
        return format("{\"title\": \"%s\"," +
                      " \"body\": \"%s\", " +
                      "\"userId\": %s}",
                      title, body, userId.toString());
    }

}
