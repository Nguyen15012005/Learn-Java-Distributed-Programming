package infrastructure.network;

import core.domain.Status;
import core.dto.AppointmentDTO;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("TRUNGNGUYEN", 731);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ){
            System.out.println("Câu 3.1");
            AppointmentDTO dto = new AppointmentDTO("D02", "P03", LocalDateTime.now(), Status.PENDING);
            Request request = new Request(CommandType.ADD_APPOINTMENT, dto);
            oos.writeObject(request);
            oos.flush();

            Reponse addReponse = (Reponse) ois.readObject();
            System.out.println(addReponse.getMessage());

            System.out.println("Câu 3.2");
            Request request1 = new Request(CommandType.GET_APPOINTMENT_DETAILS, null);
            oos.writeObject(request1);
            oos.flush();

            Reponse addReponse1 = (Reponse) ois.readObject();
            System.out.println(addReponse1.getMessage());
            List<?> appointmentList = (List<?>) addReponse1.getData();
            appointmentList.forEach(System.out::println);


            System.out.println("Câu 3.3");
            Request request2 = new Request(CommandType.GET_DOCTOR_WORKLOAD, null);
            oos.writeObject(request2);
            oos.flush();

            Reponse addReponse2 = (Reponse) ois.readObject();
            System.out.println(addReponse2.getMessage());
            List<?> appointmentList1 = (List<?>) addReponse2.getData();
            appointmentList1.forEach(System.out::println);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
