package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try(Socket socket = new Socket("pinkPanther", 4121);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // mo out truoc in
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner sc = new Scanner(System.in);
        ) {
            while(true){
                System.out.println("enter client massage");
                String message = sc.nextLine();
                out.println(message);

                message = in.readLine();
                System.out.println(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
