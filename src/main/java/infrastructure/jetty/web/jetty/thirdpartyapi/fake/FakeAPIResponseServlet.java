package infrastructure.jetty.web.jetty.thirdpartyapi.fake;

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
import java.io.InputStream;
import java.util.Scanner;
import java.util.stream.Collectors;

import static infrastructure.jetty.web.jetty.thirdpartyapi.fake.FakeDataManipulator.changeTitle;
import static infrastructure.jetty.web.jetty.thirdpartyapi.fake.FakeDataReturnedManipulator.changeId;
import static infrastructure.jetty.web.jetty.thirdpartyapi.fake.FakeDataReturnedMarshaller.*;
import static infrastructure.jetty.web.jetty.thirdpartyapi.fake.FakeDataReturnedUnmarshaller.*;
import static java.lang.String.format;

public class FakeAPIResponseServlet extends HttpServlet {
    // Aim, get json from body, change it, send it to third party
    private static final String THIRD_PARTY_API_URL = "http://jsonplaceholder.typicode.com/posts";

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Unmarshalling response\n");
        FakeDataRequest unmarshallFakeDataRequest = FakeDataUnmarshaller.unmarshall(request);
        System.out.println("manipulating request\n");
        FakeDataRequest changedFakeDataRequest = changeTitle("No more Foo here", unmarshallFakeDataRequest);
        System.out.println(format("changed title to send to third party = %s\n\n", changedFakeDataRequest.title));

        System.out.println("Posting data to third party\n");
        CloseableHttpResponse thirdPartyResponse = postToThirdPartyApi(changedFakeDataRequest);

        System.out.println("Unmarshalling response from third party");
        FakeDataReturnedRequest unmarshallFakeDataReturnedRequest = unmarshall(thirdPartyResponse);
        System.out.println("manipulating response from third part\n");
        FakeDataReturnedRequest changedFakeDataReturnedRequest = changeId(unmarshallFakeDataReturnedRequest);
        System.out.println("changed fake data returned boby = " + changedFakeDataReturnedRequest.id);
        System.out.println("\nMarshalling data for response");
        JSONObject marshalledChangedFakeDataReturned = marshall(changedFakeDataReturnedRequest);
        System.out.println("Sending repsonse back\n");
        sendResponse(response, marshalledChangedFakeDataReturned);
    }

    private CloseableHttpResponse postToThirdPartyApi(FakeDataRequest changedFakeDataRequest) throws IOException {
        //create third party request
        HttpPost thirdPartyRequest = createThirdPartyRequest(changedFakeDataRequest);

        // excute request to third party and get response
        return HttpClientBuilder.create().build().execute(thirdPartyRequest);
    }

    private void sendResponse(HttpServletResponse response, JSONObject marshalledChangedFakeDataReturned) throws IOException {
        response.setContentType("application/json");
        marshalledChangedFakeDataReturned.write(response.getWriter());
        response.setStatus(201);
    }

    private HttpPost createThirdPartyRequest(FakeDataRequest changedFakeDataRequest) throws IOException {
        HttpPost thirdPartyRequest = new HttpPost(THIRD_PARTY_API_URL);
        System.out.println("Marshall fake data request for third party api request\n");
        StringEntity input = new StringEntity(FakeDataMarshaller.marshall(changedFakeDataRequest));
        input.setContentType("application/json");
        thirdPartyRequest.setEntity(input);

        //For logging purpose to see request
        InputStream content = thirdPartyRequest.getEntity().getContent();
        Scanner s = new Scanner(content).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        System.out.println("body from request to 3rd party = " + result);
        //*********
        return thirdPartyRequest;
    }
}
