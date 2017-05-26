package infrastructure.jetty.web.jetty.json;

public interface JsonUnmarshaller<Request> {

    Request unmarshall(String requestBody);
}
