package infrastructure.jetty.web;

import org.apache.http.client.methods.CloseableHttpResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface JsonApiUnmarshaller<Request> {
    Request unmarshall(CloseableHttpResponse responseBody) throws IOException;
}
