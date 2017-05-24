package web.jetty;

import org.json.JSONObject;

public class HelloKittyUnmarshaller implements JsonUnmarshaller<HelloKittyRequest> {

    @Override
    public HelloKittyRequest unmarshall(String requestBody) {
        JSONObject jsonObject = new JSONObject(requestBody);

        return new HelloKittyRequest(
                Integer.parseInt(jsonObject.get("number").toString()));
    }
}
