package infrastructure.jetty.web.jetty.thirdpartyapi.fake;

//Move to domain
public class FakeDataManipulator {

    public static FakeDataRequest changeTitle(String newTitle, FakeDataRequest fakeDataRequest) {
        return new FakeDataRequest(newTitle, fakeDataRequest.body, fakeDataRequest.userId);
    }

}
