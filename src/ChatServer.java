import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket sS;

    ChatServer() throws IOException {
        sS = new ServerSocket(1234);
    }

    void sendToChat(String message) {
        for (Client client : clients) {
            client.receiveFromChat(message);
        }
    }

    public void run() {
        while(true) {
            System.out.println("Waiting...");
            try {
                Socket socket = sS.accept();
                System.out.println("Client connected!");
                clients.add(new Client(socket, this));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatServer().run();
    }
}