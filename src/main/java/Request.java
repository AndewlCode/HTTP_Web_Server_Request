import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Request {
    private final String request;
    private String httpMethod;
    private String path;
    private List<NameValuePair> nameValueParams;
    private String httpVersion;

    public Request(String request) {
        this.request = request;
        parseRequestString(request);
    }

    private void parseRequestString(String requestString) {
        String[] lines = requestString.split("\r\n");
        String[] requestLine = lines[0].split(" ");
        this.httpMethod = requestLine[0];
        this.path = setQueryPath(requestLine[1]);
        this.nameValueParams = setQueryParams(requestLine[1]);
        this.httpVersion = requestLine[2];
    }

    private String setQueryPath(String requestLine) {
        return this.path = requestLine.substring(0, requestLine.indexOf("?"));
    }

    private List<NameValuePair> setQueryParams(String requestLine) {
        try {
            return this.nameValueParams = URLEncodedUtils.parse(new URI(requestLine), StandardCharsets.UTF_8);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public String getQueryParam(String queryParameter) {
        Map<String, String> keyValueMap = this.nameValueParams.stream().collect(
                Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
        return keyValueMap.get(queryParameter);
    }


    public String getRequest() {
        return this.request;
    }

    public String getHttpMethod() {
        return this.httpMethod;
    }

    public String getHttpVersion() {
        return this.httpVersion;
    }

    public String getPath() {
        return this.path;
    }

    public List<NameValuePair> getNameValueParams() {
        return this.nameValueParams;
    }
}
