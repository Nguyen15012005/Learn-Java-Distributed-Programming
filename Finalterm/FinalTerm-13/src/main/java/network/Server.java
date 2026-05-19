package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(731)){
            while (true){
                System.out.println("Server started");

                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                         ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())){

                        while (true){
                            Request request = (Request) ois.readObject();
                            Response response = null;

                            switch (request.getCommandType()){
                                case addEnrollment -> {}
                                case updateScore ->{}
                                case getOpenCoursesByGenre -> {}
                                case getAllEnrollmentDetails -> {}
                                case getInstructorsWithMultipleOpenCourses -> {}
                                case findInstructorByPhone -> {}
                                case getCoursesWithNoEnrollment -> {}
                                case findAdvancedStudentsByGenreKeyword -> {}
                                case countCoursesByGenre -> {}
                                case deleteEnrollment -> {}
                                case getTop3CoursesByRevenue -> {}
                                case closeAllCoursesByInstructor -> {}
                                case StudentCourseDTO -> {}
                            }
                        }

                    } catch (Exception e) {
                        throw new IllegalArgumentException(e.getMessage());
                    }
                }).start();
            }
        }
    }
}
