import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
        int port = 12345; // Порт, який сервер прослуховує

        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Сервер запущено. Очікування повідомлень...");

            byte[] buffer = new byte[1024];

            while (true) {
                // Приймаємо пакет
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                // Отримуємо дані з пакету
                String receivedMessage = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Отримано: " + receivedMessage);

                // Якщо клієнт надіслав "exit", зупиняємо сервер
                if (receivedMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Сервер завершив роботу.");
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Помилка сервера: " + e.getMessage());
        }
    }
}
