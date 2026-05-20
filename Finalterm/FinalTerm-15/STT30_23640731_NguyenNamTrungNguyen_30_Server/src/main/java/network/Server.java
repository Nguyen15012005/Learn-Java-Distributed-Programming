package network;

import dto.AppointmentDTO;
import service.AppointmentService;
import service.DoctorService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(731)){
            System.out.println("Server start");

            while (true){
                Socket socket = serverSocket.accept();

                new Thread(() -> {
                    try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                         ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){

                        AppointmentService appointmentService = new AppointmentService();
                        DoctorService doctorService = new DoctorService();

                        while (true){
                            Request request = (Request) in.readObject();
                            Response response;

                            switch (request.getCommandType()){
                                case addAppointment -> {
                                    AppointmentDTO appointmentDTO = (AppointmentDTO) request.getData();
                                    boolean result = appointmentService.addAppointment(appointmentDTO);
                                    response = new Response(result, result ? "Them thanh cong" : "Them that bai", result);
                                }
                                case getAppointmentDetails -> {
                                    response = new Response(true, "Danh sach 3.2", appointmentService.getAppointmentDetails());
                                }
                                case getDoctorWorkload -> {
                                    response = new Response(true, "Danh sach 3.3", doctorService.getDoctorWorkload());
                                }
                                default -> {
                                    response = new Response(false, "CommandType khong hop le", null);

                                }
                            }

                            out.writeObject(response);
                            out.flush();
                            out.reset();
                        }


                    } catch (Exception e) {
                        throw new IllegalArgumentException(e.getMessage());
                    }
                }).start();
            }
        }
    }
}
