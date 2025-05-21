package rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class JsonPlaceholderService {
    private final static String baseUrl = "https://jsonplaceholder.typicode.com";

    private final ObjectMapper mapper = new ObjectMapper();
    public record Post(int userId, int id, String title, String body) {}

    public List<Post> printPosts() {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/posts"))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response code: " + response.statusCode());
            return mapper.readValue(response.body(), new TypeReference<>() {});
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
