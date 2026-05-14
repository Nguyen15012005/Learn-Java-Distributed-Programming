package infrastructure.network;

import core.entity.Job;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

public class Client {
    public static void main(String[] args) {
        try(
            Socket socket = new Socket("TRUNGNGUYEN", 731);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ) {
            System.out.println("Câu 3.2");
            String skill = "Java";
            Request request1 = new Request(ConmmandType.FINDBYSKILLINOPENJOBS, skill);
            oos.writeObject(request1);
            oos.flush();
            Response response1 = (Response) ois.readObject();
            System.out.println(response1.getMessage());
            List<Object[]> data1 = (List<Object[]>) response1.getData();
            data1.forEach(obj -> System.out.println(obj[0] + "|" + obj[1] + "|" + obj[2]));

            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");

            System.out.println("Câu 3.3");
            String companyName = "TechSoft";
            Request request2 = new Request(ConmmandType.COUNTPERJOBBYCOMPANY, companyName);
            oos.writeObject(request2);
            oos.flush();
            Response response2 = (Response) ois.readObject();
            System.out.println(response2.getMessage());
            Map<Job,Long> data2 = (Map<Job, Long>) response2.getData();
            data2.forEach((k,v)-> System.out.println(k.getTitle() + "|" + v));

        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
