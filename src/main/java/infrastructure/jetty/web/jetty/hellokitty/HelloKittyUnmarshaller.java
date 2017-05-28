package infrastructure.jetty.web.jetty.hellokitty;

import infrastructure.jetty.web.JsonUnmarshaller;
import org.json.JSONObject;

public class HelloKittyUnmarshaller implements JsonKittyUnmarshaller<HelloKittyRequest> {

    @Override
    public HelloKittyRequest unmarshall(String requestBody) {
        JSONObject jsonObject = new JSONObject(requestBody);

        return new HelloKittyRequest(
                Integer.parseInt(jsonObject.get("number").toString()));
    }
}
