package infrastructure.jetty.web.jetty.servlets.thirdpartyapi.fake;

import infrastructure.jetty.web.JsonApiUnmarshaller;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class FakeDataReturnedUnmarshaller implements JsonApiUnmarshaller<FakeDataReturnedRequest> {

    @Override
    public FakeDataReturnedRequest unmarshall(CloseableHttpResponse thirdPartyResponse) throws IOException {
        String thirdPartyResponseBody = stringifyBody(thirdPartyResponse);
        System.out.println("body from response of 3rd party = " + thirdPartyResponseBody);
        JSONObject thirdPartyResponseJsonObject = new JSONObject(thirdPartyResponseBody);

        String title = thirdPartyResponseJsonObject.getString("title");
        String jsonBody = thirdPartyResponseJsonObject.getString("body");
        Integer userId = thirdPartyResponseJsonObject.getInt("userId");
        Integer id = thirdPartyResponseJsonObject.getInt("id");

        return new FakeDataReturnedRequest(title, jsonBody, userId, id);
    }

    private static String stringifyBody(CloseableHttpResponse thirdPartyResponse) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader((thirdPartyResponse.getEntity().getContent())));
        return br.lines()
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
