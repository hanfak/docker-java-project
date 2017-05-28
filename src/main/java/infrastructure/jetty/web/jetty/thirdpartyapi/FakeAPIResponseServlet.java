package infrastructure.jetty.web.jetty.thirdpartyapi;

import com.jayway.jsonpath.JsonPath;
import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.*;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.*;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class FakeAPIResponseServlet extends HttpServlet {

    private static final String THIRD_PARTY_API_URL = "http://jsonplaceholder.typicode.com/posts";

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get body from request
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println("body from request = " + body);

        // Store body string as json object to do what we want
        JSONObject jsonObject = new JSONObject(body);
        Object title = jsonObject.get("title");
        System.out.println(format("title from request = %s", title));

        //create third party request
        HttpPost thirdPartyRequest = new HttpPost(THIRD_PARTY_API_URL);
        //turn request body into json and set the body for third party api request
        StringEntity input = new StringEntity(body);
        input.setContentType("application/json");
        thirdPartyRequest.setEntity(input);

        //For logging purpose to see request
        InputStream content = thirdPartyRequest.getEntity().getContent();
        Scanner s = new Scanner(content).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        System.out.println("body from request to 3rd party = " + result);
        //*********

        // excute request to third party and get response
        CloseableHttpResponse thirdPartyResponse = HttpClientBuilder.create().build().execute(thirdPartyRequest);
        // turn body from 3rd party response into string
        BufferedReader br = new BufferedReader(
                new InputStreamReader((thirdPartyResponse.getEntity().getContent())));
        String thirdPartyResponseBody = br.lines()
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println("response from 3rd party = " + thirdPartyResponseBody);

        //Turn body of 3rd party response into json object to do what we want,
        // access specific fields
        JSONObject jsonObject1 = new JSONObject(thirdPartyResponseBody);
        Object id = jsonObject1.get("id");
        System.out.println("id from 3rd party request = " + id);

        //Return response from 3rd party as a json as response to this servlet
        response.setContentType("application/json");
        jsonObject1.write(response.getWriter());
        response.setStatus(201);
    }
}
