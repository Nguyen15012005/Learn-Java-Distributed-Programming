package infrastructure.network;

import core.dto.AppointmentDTO;
import infrastructure.service.impl.AppointmentServiceImpl;
import infrastructure.service.impl.DoctorServiceImpl;

import java.io.EOFException;
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

                new Thread(()-> {
                    try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                         ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())){
                        while (true){
                            try{
                                Request request = (Request) ois.readObject();
                                Response response = null;
                                AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
                                DoctorServiceImpl doctorService = new DoctorServiceImpl();
                                switch (request.getCommandType()){

                                    case addAppointment -> {
                                        AppointmentDTO appointmentDTO = (AppointmentDTO) request.getData();
                                        boolean result = appointmentService.addAppointment(appointmentDTO);

                                        response = new Response(result, result ? "Them bac si moi thanh cong" : "Them bac si moi that bai", result);
                                    }
                                    case getAppointmentDetails -> {
                                        response = new Response(true, "Danh sach cuoc hen", appointmentService.getAppointmentDetails());
                                    }
                                    case getDoctorWorkload -> {
                                        response = new Response(true, "Danh sach bac si", doctorService.getDoctorWorkload());

                                    }
                                    default -> {
                                        response = new Response(false, "Comand loi", null);
                                        break;
                                    }
                                }

                                oos.writeObject(response);
                                oos.flush();
                            }
                            catch (EOFException e){
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }).start();
            }
        }
    }
}
