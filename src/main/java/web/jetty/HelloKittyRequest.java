package web.jetty;

import static java.lang.String.format;

public class HelloKittyRequest implements  Request{
    public final int number;

    public HelloKittyRequest(int number) {

        this.number = number;
    }

    @Override
    public String toJson() {
        return format("{\"number\": \"%s\"}", number);
    }

}
