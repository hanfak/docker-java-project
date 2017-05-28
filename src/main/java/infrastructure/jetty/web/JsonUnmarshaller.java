package infrastructure.jetty.web;

public interface JsonUnmarshaller<Request> {

    Request unmarshall(String requestBody);
}
