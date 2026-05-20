package infrastructure.network;

import core.dto.AppointmentDTO;
import infrastructure.service.AppointmentService;
import infrastructure.service.DoctorService;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(731)){
            System.out.println("Server started");
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(()-> {
                    try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                         ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
                        while (true){
                            AppointmentService appointmentService = new AppointmentService();
                            DoctorService doctorService = new DoctorService();
                            Request request = (Request) in.readObject();
                            Response response;

                            switch (request.getCommandType()){
                                case addAppointment -> {
                                    AppointmentDTO appointmentDTO = (AppointmentDTO) request.getData();
                                    boolean result = appointmentService.addAppointment(appointmentDTO);
                                    response = new Response(result, result?"Them thanh cong": "Them that bai", result);
                                }

                                case getAppointmentDetails -> {
                                    response = new Response(true, "Danh sach cuoc hen", appointmentService.getAppointmentDetails());
                                }
//
                                case getDoctorWorkload -> {
                                    response = new Response(true, "Danh sach cuoc hen", doctorService.getDoctorWorkload());
                                }

                                default -> {
                                    response = new Response(false, "Command khong hop le", null);
                                }
                            }

                            out.writeObject(response);
                            out.flush();
                            out.reset();
                        }

                    }catch (IOException | ClassNotFoundException e) {
                        System.out.println("Client disconnected");
//                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }
}
