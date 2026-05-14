package network;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 731;

        try (
                Socket socket = new Socket(host, port);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                PrintWriter writer = new PrintWriter(
                        socket.getOutputStream(),
                        true
                );
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Client connect to server success");

            while (true) {
                System.out.println("\n===== MENU =====");
                System.out.println("1. Find courses by credits between");
                System.out.println("2. Find course by department name");
                System.out.println("3. Count students by course");
                System.out.println("0. Exit");
                System.out.print("Choose: ");

                int choice = Integer.parseInt(scanner.nextLine());
                String request;

                switch (choice) {
                    case 1:
                        System.out.print("Min credit: ");
                        int min = Integer.parseInt(scanner.nextLine());

                        System.out.print("Max credit: ");
                        int max = Integer.parseInt(scanner.nextLine());

                        request = "findCoursesByCreditsBetween " + min + " " + max;
                        break;

                    case 2:
                        System.out.print("Department keyword: ");
                        String keyword = scanner.nextLine();

                        request = "findCourseByDepartmentNameContaining " + keyword;
                        break;

                    case 3:
                        request = "countStudentsByCourse";
                        break;

                    case 0:
                        request = "exit";
                        writer.println(request);
                        System.out.println(reader.readLine());
                        return;

                    default:
                        System.out.println("Invalid choice");
                        continue;
                }

                writer.println(request);

                String response = reader.readLine();
                System.out.println("Response: " + response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}