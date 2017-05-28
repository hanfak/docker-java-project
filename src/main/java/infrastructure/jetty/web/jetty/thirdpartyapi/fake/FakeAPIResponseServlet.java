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

import static java.lang.String.format;

public class FakeAPIResponseServlet extends HttpServlet {
    // Aim, get json from body, change it, send it to third party
    private static final String THIRD_PARTY_API_URL = "http://jsonplaceholder.typicode.com/posts";

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get body from request
        String body = request.getReader()
                             .lines()
                             .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(format("body from request = %s\n", body));

        // Store body string as json object to do what we want
        JSONObject jsonObject = new JSONObject(body);
        // Store json object into object and manipulates it (use static factory method)
        String title = jsonObject.getString("title");
        String jsonBody = jsonObject.getString("body");
        Integer userId = jsonObject.getInt("userId");
        FakeDataRequest fakeDataRequest = new FakeDataRequest(title, jsonBody, userId);
        FakeDataRequest changedFakeDataRequest = FakeDataManipulator.changeTitle("No more Foo here", fakeDataRequest);

        System.out.println(format("title from request = %s\n", title));
        System.out.println(format("title from fake data object = %s\n", fakeDataRequest.title));
        System.out.println(format("changed title to send to third party = %s\n\n", changedFakeDataRequest.title));

        //create third party request
        HttpPost thirdPartyRequest = new HttpPost(THIRD_PARTY_API_URL);
        //turn request body into json and set the body for third party api request
        StringEntity input = new StringEntity(changedFakeDataRequest.toJson());
        input.setContentType("application/json");
        thirdPartyRequest.setEntity(input);

        //For logging purpose to see request
        InputStream content = thirdPartyRequest.getEntity().getContent();
        Scanner s = new Scanner(content).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        System.out.println("body from request to 3rd party = " + result);
        //*********

        // excute request to third party and get response
        CloseableHttpResponse thirdPartyResponse = HttpClientBuilder.create()
                                                    .build()
                                                    .execute(thirdPartyRequest);
        // unmarshaller thirdPartyResponse into object
        FakeDataReturnedRequest unmarshallFakeDataReturnedRequest = FakeDataReturnedUnmarshaller.unmarshall(thirdPartyResponse);
        // manipulate using another object method
        FakeDataReturnedRequest changedFakeDataReturnedRequest = FakeDataReturnedManipulator.changeId(unmarshallFakeDataReturnedRequest);
        System.out.println("changed fake data returned boby = " + changedFakeDataReturnedRequest.id);
        // marshaller it using 'toJson' from object
        JSONObject marshalledChangedFakeDataReturned = FakeDataReturnedMarshaller.marshall(changedFakeDataReturnedRequest);
        // Write response with marshalled data
        //Return response from 3rd party as a json as response to this servlet
        response.setContentType("application/json");
        marshalledChangedFakeDataReturned.write(response.getWriter());
        response.setStatus(201);
    }
}
