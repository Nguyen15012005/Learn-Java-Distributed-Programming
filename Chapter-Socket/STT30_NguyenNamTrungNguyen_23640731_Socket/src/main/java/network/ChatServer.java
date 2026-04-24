package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(4121);){
            System.out.println("Server Started");
            while(true){
                Socket socket = serverSocket.accept(); //1 client
                System.out.println(socket.getInetAddress().getHostName());
                System.out.println(socket.getRemoteSocketAddress());
                ClientHandler handler = new ClientHandler(socket);
                Thread thread = new Thread(handler);
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
class ClientHandler implements Runnable{
    private Socket socket;
    public ClientHandler(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // mo out truoc in
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner sc = new Scanner(System.in);

            while(true){
                String message = in.readLine();
                System.out.println("receive massage: " + message);

                //send back massage
                System.out.println("enter client massage");
                message = sc.nextLine();
                out.println(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
