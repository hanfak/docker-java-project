package infrastructure.jetty.web.jetty.thirdpartyapi.fake;

import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * Created by hanfak on 28/05/2017.
 */
public class FakeDataUnmarshaller {
    public static FakeDataRequest unmarshall(HttpServletRequest request) throws IOException {
        //get body from request
        String body = request.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(format("body from request = %s\n", body));

        // Store body string as json object to do what we want
        JSONObject jsonObject = new JSONObject(body);
        // Store json object into object and manipulates it (use static factory method)
        String title = jsonObject.getString("title");
        String jsonBody = jsonObject.getString("body");
        Integer userId = jsonObject.getInt("userId");
        FakeDataRequest fakeDataRequest = new FakeDataRequest(title, jsonBody, userId);

        System.out.println(format("title from request = %s\n", title));
        System.out.println(format("title from fake data object = %s\n", fakeDataRequest.title));

        return fakeDataRequest;
    }
}
