package infrastructure.jetty.web.jetty.thirdpartyapi;

import com.jayway.jsonpath.JsonPath;
import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.*;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.*;
import org.apache.http.util.EntityUtils;

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

public class FakeAPIResponseServlet extends HttpServlet {
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Use this instead
        String jsonString = "{" +
                "    \"title\": \"foo\"," +
                "    \"body\": \"bar\"," +
                "    \"userId\": \"1\"" +
                "}";
        HttpPost postRequest = new HttpPost("http://jsonplaceholder.typicode.com/posts");

        StringEntity input = new StringEntity(jsonString);
        input.setContentType("application/json");
        postRequest.setEntity(input);

        //For logging purpose to see request
        InputStream content = postRequest.getEntity().getContent();
        Scanner s = new Scanner(content).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        System.out.println(result);

        CloseableHttpResponse theResponse = HttpClientBuilder.create().build().execute(postRequest);
        //For logging purpose to get response back
        BufferedReader br = new BufferedReader(
                new InputStreamReader((theResponse.getEntity().getContent())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        response.setStatus(201);

    }
}
