package network;

import service.CourseService;
import service.impl.CourseServiceImpl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 731;

        try (
                ServerSocket serverSocket = new ServerSocket(port)
        ) {
            System.out.println("TCP Server started on port " + port);

            CourseService courseService = new CourseServiceImpl();

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                new Thread(() -> handleClient(socket, courseService)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket, CourseService courseService) {
        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                PrintWriter writer = new PrintWriter(
                        socket.getOutputStream(),
                        true
                )
        ) {
            String request;

            while ((request = reader.readLine()) != null) {
                System.out.println("Request: " + request);

                String response = handleRequest(request, courseService);

                writer.println(response);

                if (request.equalsIgnoreCase("exit")) {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String handleRequest(String request, CourseService courseService) {
        try {
            String[] parts = request.split(" ");

            switch (parts[0]) {
                case "findCoursesByCreditsBetween": {
                    int min = Integer.parseInt(parts[1]);
                    int max = Integer.parseInt(parts[2]);

                    StringBuilder sb = new StringBuilder();

                    courseService.findCoursesByCreditsBetween(min, max)
                            .forEach(course -> sb.append(course).append(" | "));

                    return sb.toString();
                }

                case "findCourseByDepartmentNameContaining": {
                    String keyword = parts[1];

                    StringBuilder sb = new StringBuilder();

                    courseService.findCourseByDepartmentNameContaining(keyword)
                            .forEach(course -> sb.append(course).append(" | "));

                    return sb.toString();
                }

                case "countStudentsByCourse": {
                    StringBuilder sb = new StringBuilder();

                    courseService.countStudentsByCourse()
                            .forEach((k, v) -> sb.append(k.getTitle())
                                    .append(": ")
                                    .append(v)
                                    .append(" | "));

                    return sb.toString();
                }

                case "exit":
                    return "Disconnect success";

                default:
                    return "Unknown request";
            }

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}