import core.dto.EnrollmentDTO;
import core.enums.EnrollStatus;
import infrastructure.network.CommandType;
import infrastructure.network.Request;
import infrastructure.network.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("TRUNGNGUYEN", 731);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ){
            EnrollmentDTO enrollmentDTO = new EnrollmentDTO(Date.valueOf("6784-12-18"), 9.5, EnrollStatus.COMPLETED, "C003", "S001");
            Request request = new Request(CommandType.addEnrollment, enrollmentDTO);
            oos.writeObject(request);
            oos.flush();

            Response response = (Response) ois.readObject();
            System.out.println(response.getMessage());
//--------------------------------------------------
            Map<String, Object> dataupdate = new HashMap<>();
            dataupdate.put("studentId", "S004");
            dataupdate.put("courseId", "C002");
            dataupdate.put("enrollDate", Date.valueOf("2026-07-18"));
            dataupdate.put("newScore", 10.0);
            Request request1 = new Request(CommandType.updateScore, dataupdate);
            oos.writeObject(request1);
            oos.flush();

            Response response1 = (Response) ois.readObject();
            System.out.println(response1.getMessage());

//            -----------------------------------
            String genre = "Classic";
            Request request2 = new Request(CommandType.getOpenCoursesByGenre, genre);
            oos.writeObject(request2);
            oos.flush();

            Response response2 = (Response) ois.readObject();
            System.out.println(response2.getMessage());
            List<Object[]> list = (List<Object[]>) response2.getData();
            list.forEach(obj -> System.out.println(
                        "Ten Khoa hoc: " + (String) obj[0] +
                                " | Ten giang vien: " + (String) obj[1] +
                                " | Ten loai nhac: " + (String) obj[2] +
                                " | Hoc phi: " + (Double) obj[3] +
                                " | So luong hoc vien: " + (Long) obj[4]
                ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
