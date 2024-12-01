import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        int port = 12345; // Порт для прослуховування

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущено. Очікування клієнтів...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Приймаємо підключення
                System.out.println("Підключився клієнт: " + clientSocket.getInetAddress());

                // Обробляємо клієнта в окремому потоці
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Помилка сервера: " + e.getMessage());
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Отримано від клієнта: " + inputLine);
                out.println("Ехо: " + inputLine); // Відправляємо відповідь клієнту
            }
        } catch (IOException e) {
            System.err.println("Помилка обробки клієнта: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Не вдалося закрити сокет клієнта: " + e.getMessage());
            }
        }
    }
}

