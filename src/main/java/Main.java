import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        final int serverPort = 9999;
        final List<String> validPaths = List.of("/index.html", "/spring.svg", "/spring.png", "/resources.html", "/styles.css", "/app.js", "/links.html", "/forms.html", "/classic.html", "/events.html", "/events.js");

        Server server = new Server(serverPort, validPaths);
        server.start();
    }
}