package infrastructure.network;

import core.dto.AlbumDTO;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {
    public static void main(String[] args) {
        try (
            Socket socket = new Socket("TRUNGNGUYEN", 731);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ){
            System.out.println("Câu 3.1");
            Map<String, Object> data1 = new HashMap<>();
            data1.put("id", "ALB001");
            data1.put("newPrice", 20.4);

            Request request1 = new Request(CommandType.updatePriceOfAlbum, data1);

            oos.writeObject(request1);
            oos.flush();

            Response response1 = (Response) ois.readObject();
            System.out.println(response1.getMessage());

//            -------------------------------
            System.out.println("Câu 3.2");
            Map<String, Object> data2 = new HashMap<>();
            data2.put("genreName", "BLUES");
            data2.put("year", 1967);

            Request request2 = new Request(CommandType.listAlbumByGenre, data2);

            oos.writeObject(request2);
            oos.flush();

            Response response2 = (Response) ois.readObject();
            System.out.println(response2.getMessage());
            List<AlbumDTO> list = (List<AlbumDTO>) response2.getData();
            list.forEach(System.out::println);

//            ----------------------------------

            System.out.println("Câu 3.3");
            Request request3 = new Request(CommandType.getNumberOfAlbumsByGenre, null);

            oos.writeObject(request3);
            oos.flush();

            Response response3 = (Response) ois.readObject();
            System.out.println(response3.getMessage());
            Map<String, Long> data = (Map<String, Long>) response3.getData();

            data.forEach((k, v) -> System.out.println(k + "|" + v));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
