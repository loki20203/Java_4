import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Адреса сервера
        int port = 12345; // Порт сервера

        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Підключено до сервера. Введіть повідомлення (для виходу введіть 'exit'):");

            while (true) {
                // Читаємо повідомлення користувача
                String message = scanner.nextLine();

                // Відправляємо пакет на сервер
                byte[] buffer = message.getBytes();
                InetAddress address = InetAddress.getByName(serverAddress);
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
                socket.send(packet);

                // Якщо введено "exit", завершуємо роботу клієнта
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Клієнт завершив роботу.");
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Помилка клієнта: " + e.getMessage());
        }
    }
}
