package infrastructure.jetty.web.jetty.thirdpartyapi.fake;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Created by hanfak on 28/05/2017.
 */
public class FakeDataReturnedUnmarshaller {
    public static FakeDataReturnedRequest unmarshall(CloseableHttpResponse thirdPartyResponse) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader((thirdPartyResponse.getEntity().getContent())));
        String thirdPartyResponseBody = br.lines()
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println("response from 3rd party = " + thirdPartyResponseBody);
        JSONObject thirdPartyResponseJsonObject = new JSONObject(thirdPartyResponseBody);

        String title = thirdPartyResponseJsonObject.getString("title");
        String jsonBody = thirdPartyResponseJsonObject.getString("body");
        Integer userId = thirdPartyResponseJsonObject.getInt("userId");
        Integer id = thirdPartyResponseJsonObject.getInt("id");

        System.out.println("id from 3rd party request = " + id);

        return new FakeDataReturnedRequest(title, jsonBody, userId, id);
    }
    // return JsonObject given fakeDataReturned object
}
