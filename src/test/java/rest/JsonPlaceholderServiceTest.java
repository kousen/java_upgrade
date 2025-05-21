package rest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonPlaceholderServiceTest {

    private final JsonPlaceholderService jsonPlaceholderService = new JsonPlaceholderService();

    @Test
    void printPosts() {
        jsonPlaceholderService.printPosts().stream()
                .forEach(System.out::println);
    }
}