package infrastructure.network;

import core.dto.AppointmentDTO;
import core.dto.DoctorWorkloadDTO;
import core.enums.Status;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("TRUNGNGUYEN", 731);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ){
//            Cau 3.1

            AppointmentDTO appointmentDTO = new AppointmentDTO(LocalDateTime.now(), Status.PENDING, "D01", "P03");
            Request request = new Request(CommandType.addAppointment, appointmentDTO);

            out.writeObject(request);
            out.flush();

            Response response = (Response) in.readObject();
            System.out.println(response.getMessage());

//            Cau 3.2
            Request request1 = new Request(CommandType.getAppointmentDetails, null);

            out.writeObject(request1);
            out.flush();

            Response response1 = (Response) in.readObject();
            List<AppointmentDTO> list = (List<AppointmentDTO>) response1.getData();
            list.forEach(System.out::println);


//            Cau 3.3

            Request request2 = new Request(CommandType.getDoctorWorkload, null);

            out.writeObject(request2);
            out.flush();

            Response response2 = (Response) in.readObject();
            List<DoctorWorkloadDTO> list1 = (List<DoctorWorkloadDTO>) response2.getData();
            list1.forEach(System.out::println);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
