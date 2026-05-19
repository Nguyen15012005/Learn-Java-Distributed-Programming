package infrastructure.network;

import core.dto.EnrollmentDTO;
import infrastructure.repository.CourseRepository;
import infrastructure.repository.EnrollmentRepository;
import infrastructure.repository.impl.CourseRepositoryImpl;
import infrastructure.service.EnrollmentService;
import infrastructure.service.impl.EnrollmentServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

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
                            EnrollmentService enrollmentService = new EnrollmentServiceImpl();
                            CourseRepository courseRepository = new CourseRepositoryImpl();
                            Request request = (Request) ois.readObject();
                            Response response = null;

                            switch (request.getCommandType()){
                                case addEnrollment -> {
                                    EnrollmentDTO enrollmentDTO = (EnrollmentDTO) request.getData();
                                    boolean result = enrollmentService.addEnrollment(enrollmentDTO);
                                    response = new Response(result, result ? "Them thanh cong" : "Them that bai", result);
                                }
                                case updateScore ->{
                                    Map<String, Object> dataupdate = (Map<String, Object>) request.getData();
                                    String studentId = (String) dataupdate.get("studentId");
                                    String courseId = (String) dataupdate.get("courseId");
                                    Date enrollDate = (Date) dataupdate.get("enrollDate");
                                    Double newScore = (Double) dataupdate.get("newScore");
                                    boolean result = enrollmentService.updateScore(studentId,courseId,enrollDate,newScore);
                                    response = new Response(result, result ? "Update thanh cong" : "Update that bai", result);
                                }
                                case getOpenCoursesByGenre -> {
                                    String genre = (String) request.getData();
                                    response = new Response(true, "Danh sach", courseRepository.getOpenCoursesByGenre(genre));
                                }

                            }

                            oos.writeObject(response);
                            oos.flush();
                            oos.reset();
                        }

                    } catch (Exception e) {
                        throw new IllegalArgumentException(e.getMessage());
                    }
                }).start();
            }
        }
    }
}
