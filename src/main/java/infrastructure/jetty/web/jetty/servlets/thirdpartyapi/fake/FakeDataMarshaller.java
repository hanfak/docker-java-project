package infrastructure.jetty.web.jetty.servlets.thirdpartyapi.fake;

import org.json.JSONObject;

public class FakeDataMarshaller {
    public static JSONObject marshall(FakeDataRequest changedFakeDataRequest) {
        return new JSONObject(changedFakeDataRequest.toJson());
    }
}
