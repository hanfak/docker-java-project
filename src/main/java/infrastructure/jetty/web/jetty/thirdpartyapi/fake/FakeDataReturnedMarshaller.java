package infrastructure.jetty.web.jetty.thirdpartyapi.fake;

import org.json.JSONObject;

public class FakeDataReturnedMarshaller {
    public static JSONObject marshall(FakeDataReturnedRequest changedFakeDataReturnedRequest) {
        return new JSONObject(changedFakeDataReturnedRequest.toJson());
    }
}
