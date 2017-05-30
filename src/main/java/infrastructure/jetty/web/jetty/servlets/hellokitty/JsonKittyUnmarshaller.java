package infrastructure.jetty.web.jetty.servlets.hellokitty;

public interface JsonKittyUnmarshaller<Request> {
    Request unmarshall(String requestBody);
}
