package infrastructure.jetty.web.jetty.hellokitty;

import javax.servlet.http.HttpServletRequest;

public interface JsonKittyUnmarshaller<Request> {
    Request unmarshall(String requestBody);
}
