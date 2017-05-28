package infrastructure.jetty.web.jetty.thirdpartyapi.fake;

import org.json.JSONObject;

/**
 * Created by hanfak on 28/05/2017.
 */
public class FakeDataReturnedMarshaller {
    public static JSONObject marshall(FakeDataReturnedRequest changedFakeDataReturnedRequest) {
        return new JSONObject(changedFakeDataReturnedRequest.toJson());
    }
    // return JsonObject given fakeDataReturned object
}
