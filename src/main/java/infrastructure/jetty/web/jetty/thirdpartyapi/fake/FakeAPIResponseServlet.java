package infrastructure.jetty.web.jetty.thirdpartyapi.fake;

import infrastructure.jetty.web.JsonUnmarshaller;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.*;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static infrastructure.jetty.web.jetty.thirdpartyapi.fake.FakeDataManipulator.changeTitle;
import static infrastructure.jetty.web.jetty.thirdpartyapi.fake.FakeDataReturnedManipulator.changeId;
import static infrastructure.jetty.web.jetty.thirdpartyapi.fake.FakeDataReturnedMarshaller.*;
import static infrastructure.jetty.web.jetty.thirdpartyapi.fake.FakeDataReturnedUnmarshaller.*;
import static java.lang.String.format;

public class FakeAPIResponseServlet extends HttpServlet {
    private static final String THIRD_PARTY_API_URL = "http://jsonplaceholder.typicode.com/posts";

    //
    private FakeDataUnmarshaller unmarshaller;

    public FakeAPIResponseServlet(FakeDataUnmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(">Unmarshalling response\n");
        FakeDataRequest unmarshallFakeDataRequest = unmarshaller.unmarshall(request);
        System.out.println(format("title from fake data object = %s\n", unmarshallFakeDataRequest.title));

        System.out.println(">manipulating request\n");
        FakeDataRequest changedFakeData = changeTitle("No more Foo here", unmarshallFakeDataRequest);
        System.out.println(format("changed title to send to third party = %s\n\n", changedFakeData.title));

        System.out.println(">Posting data to third party\n");
        CloseableHttpResponse thirdPartyResponse = postToThirdPartyApi(changedFakeData);

        System.out.println(">Unmarshalling response from third party\n");
        FakeDataReturnedRequest unmarshallFakeDataReturnedRequest = new FakeDataReturnedUnmarshaller().unmarshall(thirdPartyResponse);

        System.out.println("\n>manipulating response from third part\n");
        FakeDataReturnedRequest changedFakeDataReturnedRequest = changeId(unmarshallFakeDataReturnedRequest);
        System.out.println("changed fake data returned id = " + changedFakeDataReturnedRequest.id);

        System.out.println("\n>Marshalling data for response");
        JSONObject marshalledChangedFakeDataReturned = marshall(changedFakeDataReturnedRequest);

        System.out.println(">Sending repsonse back\n");
        sendResponse(response, marshalledChangedFakeDataReturned);
    }

    private CloseableHttpResponse postToThirdPartyApi(FakeDataRequest changedFakeDataRequest) throws IOException {
        return HttpClientBuilder.create()
                .build()
                .execute(thirdPartyRequest(changedFakeDataRequest));
    }

    private void sendResponse(HttpServletResponse response, JSONObject jsonObject) throws IOException {
        response.setContentType("application/json");
        jsonObject.write(response.getWriter());
        response.setStatus(201);
    }

    private HttpPost thirdPartyRequest(FakeDataRequest changedFakeDataRequest) throws IOException {
        HttpPost thirdPartyRequest = new HttpPost(THIRD_PARTY_API_URL);
        System.out.println(">Marshall fake data request for third party api request\n");
        StringEntity input = new StringEntity(FakeDataMarshaller.marshall(changedFakeDataRequest).toString());
        input.setContentType("application/json");
        thirdPartyRequest.setEntity(input);
        return thirdPartyRequest;
    }
}
