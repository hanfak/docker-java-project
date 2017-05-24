package web.jetty;

public interface JsonUnmarshaller<Request> {

    Request unmarshall(String requestBody);
}
