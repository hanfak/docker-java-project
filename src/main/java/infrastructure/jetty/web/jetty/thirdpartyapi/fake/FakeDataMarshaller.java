package infrastructure.jetty.web.jetty.thirdpartyapi.fake;

/**
 * Created by hanfak on 28/05/2017.
 */
public class FakeDataMarshaller {
    public static String marshall(FakeDataRequest changedFakeDataRequest) {
        return changedFakeDataRequest.toJson();
    }
}
