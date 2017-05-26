package infrastructure.jetty.web.jetty.hellokitty;

import infrastructure.jetty.web.jetty.json.JsonUnmarshaller;
import org.json.JSONObject;

public class HelloKittyUnmarshaller implements JsonUnmarshaller<HelloKittyRequest> {

    @Override
    public HelloKittyRequest unmarshall(String requestBody) {
        JSONObject jsonObject = new JSONObject(requestBody);

        return new HelloKittyRequest(
                Integer.parseInt(jsonObject.get("number").toString()));
    }
}
