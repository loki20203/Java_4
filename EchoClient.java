import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Адреса сервера (локальний комп'ютер)
        int port = 12345; // Порт, який прослуховує сервер

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Підключено до сервера. Введіть повідомлення (для виходу введіть 'exit'):");

            String userInput;
            while ((userInput = consoleInput.readLine()) != null) {
                if ("exit".equalsIgnoreCase(userInput)) {
                    System.out.println("Завершення роботи...");
                    break;
                }

                out.println(userInput); // Відправляємо повідомлення серверу
                String response = in.readLine(); // Отримуємо відповідь від сервера
                System.out.println("Відповідь від сервера: " + response);
            }
        } catch (IOException e) {
            System.err.println("Помилка клієнта: " + e.getMessage());
        }
    }
}
