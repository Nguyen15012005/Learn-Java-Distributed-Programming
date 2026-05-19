import core.dto.AppointmentDTO;
import core.dto.DoctorWorkloadDTO;
import core.enums.Status;
import infrastructure.network.CommandType;
import infrastructure.network.Request;
import infrastructure.network.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("TRUNGNGUYEN", 731);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())){
            // Cau 3.1

            AppointmentDTO appointmentDTO = new AppointmentDTO("D01", "P02", LocalDateTime.now(), Status.PENDING);
            Request request = new Request(CommandType.addAppointment, appointmentDTO);
            oos.writeObject(request);
            oos.flush();
            Response response = (Response) ois.readObject();
            System.out.println(response.getMessage());

            // Cau 3.2
            Request request1 = new Request(CommandType.getAppointmentDetails, null);
            oos.writeObject(request1);
            oos.flush();
            Response response1 = (Response) ois.readObject();
            System.out.println(response1.getMessage());
            List<AppointmentDTO> list = (List<AppointmentDTO>) response1.getData();
            list.forEach(System.out::println);

//            Cau 3.3
            Request request2 = new Request(CommandType.getDoctorWorkload, null);
            oos.writeObject(request2);
            oos.flush();
            Response response2 = (Response) ois.readObject();
            System.out.println(response2.getMessage());
            List<DoctorWorkloadDTO> list1 = (List<DoctorWorkloadDTO>) response2.getData();
            list1.forEach(System.out::println);

            // Cau 3.3
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}