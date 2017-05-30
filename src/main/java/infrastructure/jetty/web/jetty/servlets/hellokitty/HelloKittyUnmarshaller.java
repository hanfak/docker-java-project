package infrastructure.jetty.web.jetty.servlets.hellokitty;

import org.json.JSONObject;

public class HelloKittyUnmarshaller implements JsonKittyUnmarshaller<HelloKittyRequest> {

    @Override
    public HelloKittyRequest unmarshall(String requestBody) {
        JSONObject jsonObject = new JSONObject(requestBody);

        return new HelloKittyRequest(
                Integer.parseInt(jsonObject.get("number").toString()));
    }
}
