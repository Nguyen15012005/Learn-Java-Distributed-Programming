package network;

import dto.AlbumDTO;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("TRUNGNGUYEN", 731);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

//            Cau 3.1
            String id = "ALB001";
            double price = 40.3;
            Map<String, Object> data1 = new HashMap<>();
            data1.put("id", id);
            data1.put("price", price);

            Request request = new Request(CommandType.updatePriceOfAlbum, data1);
            oos.writeObject(request);
            oos.flush();

            Response response = (Response) ois.readObject();
            System.out.println(response.getMessage());
//
            String genreName = "Blues";
            int year = 1967;
            Map<String, Object> data2 = new HashMap<>();
            data2.put("genreName", genreName);
            data2.put("year", year);

            Request request1 = new Request(CommandType.listAlbumByGenre, data2);
            oos.writeObject(request1);
            oos.flush();

            Response response1 = (Response) ois.readObject();
            System.out.println(response1.getMessage());
            List<AlbumDTO> list = (List<AlbumDTO>) response1.getData();
            list.forEach(System.out::println);
//

            Request request2 = new Request(CommandType.getNumberOfAlbumsByGenre, null);
            oos.writeObject(request2);
            oos.flush();

            Response response2 = (Response) ois.readObject();
            System.out.println(response2.getMessage());
            Map<String, Long> list1 = (Map<String, Long>) response2.getData();
            list1.forEach((k,v) -> System.out.println(k  + "|" + v));

        }
       catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
