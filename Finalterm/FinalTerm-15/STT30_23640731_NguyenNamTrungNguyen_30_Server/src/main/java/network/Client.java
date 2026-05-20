package network;

import dto.AppointmentDTO;
import dto.DoctorWorkloadDTO;
import entity.Status;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("TRUNGNGUYEN", 731);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
//            =============================================
            System.out.println("Cau 3.1");
            AppointmentDTO appointmentDTO = new AppointmentDTO(LocalDateTime.now(), "D01", "P01", Status.PENDING);
            Request request = new Request(CommandType.addAppointment, appointmentDTO);

            out.writeObject(request);
            out.flush();

            Response response = (Response) in.readObject();
            System.out.println(response.getMessage());
//            =============================================
            System.out.println("Cau 3.2");
            Request request1 = new Request(CommandType.getAppointmentDetails, null);

            out.writeObject(request1);
            out.flush();

            Response response1 = (Response) in.readObject();
            List<AppointmentDTO> list1 = (List<AppointmentDTO>) response1.getData();
            list1.forEach(System.out::println);


//            =============================================
            System.out.println("Cau 3.3");
            Request request2 = new Request(CommandType.getDoctorWorkload, null);

            out.writeObject(request2);
            out.flush();

            Response response2 = (Response) in.readObject();
            List<DoctorWorkloadDTO> list2 = (List<DoctorWorkloadDTO>) response2.getData();
            list2.forEach(
                    System.out::println
            );

        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
