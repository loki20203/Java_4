import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;

public class HttpExample {

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        // Виконання GET-запиту
        System.out.println("=== Виконання GET-запиту ===");
        performGetRequest(client);

        // Виконання POST-запиту
        System.out.println("\n=== Виконання POST-запиту ===");
        performPostRequest(client);
    }

    private static void performGetRequest(HttpClient client) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1")) // Приклад URL
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Виведення результату
            printResponse(response);
        } catch (Exception e) {
            System.err.println("Помилка під час виконання GET-запиту: " + e.getMessage());
        }
    }

    private static void performPostRequest(HttpClient client) {
        try {
            // Тіло POST-запиту
            String requestBody = """
                    {
                        "title": "foo",
                        "body": "bar",
                        "userId": 1
                    }
                    """;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts")) // Приклад URL
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Виведення результату
            printResponse(response);
        } catch (Exception e) {
            System.err.println("Помилка під час виконання POST-запиту: " + e.getMessage());
        }
    }

    private static void printResponse(HttpResponse<String> response) {
        // Виведення статусу
        System.out.println("Статус-код: " + response.statusCode());

        // Виведення заголовків
        System.out.println("Заголовки:");
        HttpHeaders headers = response.headers();
        headers.map().forEach((key, value) -> System.out.println(key + ": " + value));

        // Виведення тіла відповіді
        System.out.println("Тіло відповіді:");
        System.out.println(response.body());
    }
}
