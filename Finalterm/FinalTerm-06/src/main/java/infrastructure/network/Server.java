package infrastructure.network;

import core.dto.AppointmentDTO;
import infrastructure.service.AppointmentService;
import infrastructure.service.impl.AppointmentServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(731)){
            System.out.println("Server đang chay tại port 731");

            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("Client đã kết nối: " + socket.getInetAddress());
                new Thread(()-> {
                   try(ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                       ObjectInputStream ois = new ObjectInputStream((socket.getInputStream()))

                   ){
                       AppointmentService appointmentService = new AppointmentServiceImpl();
                       while (true){
                           Request request = (Request) ois.readObject();
                           Reponse reponse;

                           switch (request.getCommand()){
                               case ADD_APPOINTMENT -> {
                                   AppointmentDTO dto = (AppointmentDTO) request.getData();

                                   boolean result = appointmentService.addAppointment(dto);
                                   reponse = new Reponse(result, result ? "Thêm lịch khám thành công" : "Thêm lịch khám thất bại", result);
                               }
                               case GET_APPOINTMENT_DETAILS -> {
                                    reponse = new Reponse(true, "Lấy danh sách lịch khám thành công", appointmentService.getAppointmentDetails());
                               }

                               case GET_DOCTOR_WORKLOAD -> {
                                   reponse = new Reponse(true, "Lấy workload thành công", appointmentService.getDoctorWorkload());
                               }

                               default -> {
                                   reponse = new Reponse(false, "Command không hợp lệ",null);
                               }
                           }
                           oos.writeObject(reponse);
                           oos.flush();

                       }
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                }).start();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
