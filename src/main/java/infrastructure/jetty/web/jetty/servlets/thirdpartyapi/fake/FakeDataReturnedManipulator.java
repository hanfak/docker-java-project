package infrastructure.jetty.web.jetty.servlets.thirdpartyapi.fake;

//Move to domain
public class FakeDataReturnedManipulator {

    public static FakeDataReturnedRequest changeId(FakeDataReturnedRequest fakeDataReturnedRequest) {
        return new FakeDataReturnedRequest(fakeDataReturnedRequest.title, fakeDataReturnedRequest.jsonBody, fakeDataReturnedRequest.userId, fakeDataReturnedRequest.id + 24);
    }

}
