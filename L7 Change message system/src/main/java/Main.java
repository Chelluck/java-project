import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {

    public static void main(String[] args) throws Exception {
         try (ServerSocket serverSocket = new ServerSocket(5050)) {

             System.out.println("Server started");

            while (true) {
                Socket clientSocket = serverSocket.accept();

                System.out.println("Client connected: " + clientSocket);

                SocketThread ClientHandler = new SocketThread(clientSocket);

                ClientHandler.start();
            }
         }
    }


    private static class SocketThread extends Thread {
        private final Socket clientSocket;


        private SocketThread(Socket clientSocket) {this.clientSocket = clientSocket;}

        @Override
        public void run() {
            // Выводим информацию о запущенном потоке
            System.out.println("Run: " + this.getName());
            try (
                    PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
            ) {
                String inputLine;
                // Читаем данные от клиента и отправляем их обратно
                while ((inputLine = input.readLine()) != null) {
                    output.println(inputLine);
                    // Если клиент отправил "Bye.", завершаем соединение
                    if (inputLine.equals("Bye.")) {
                        break;
                    }
                    // Закрываем соединение с клиентом
                }
                clientSocket.close();

                System.out.println("Client disconnected: " + clientSocket);

            } catch (IOException e) {
                // В случае ошибки выводим информацию об ошибке и завершаем программу
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
