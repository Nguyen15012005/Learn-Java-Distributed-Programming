package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(731)){

            while (true){
                System.out.println("Server started");

                Socket socket = serverSocket.accept();
                new Thread(()-> {
                    try (
                            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

                        Request request = (Request) ois.readObject();
                        while (true){
//                            switch ()
                        }

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).start();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
