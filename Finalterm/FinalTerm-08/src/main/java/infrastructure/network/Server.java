package infrastructure.network;

import infrastructure.service.impl.AlbumService;
import infrastructure.service.impl.AlbumServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Server {
    public static void main(String[] args) throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(731)){

            System.out.println("Server started");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client đã kết nối");
                new Thread(() -> {
                   try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
                   ){
                       AlbumService albumService = new AlbumServiceImpl();

                       while (true){
                           Request request = (Request) ois.readObject();
                           Response response;

                           switch (request.getCommandType()){

                               case updatePriceOfAlbum -> {
                                   Map<String, Object> data = (Map<String, Object>) request.getData();

                                   String id = (String) data.get("id");
                                   double newPrice = (double) data.get("newPrice");

                                   boolean result = albumService.updatePriceOfAlbum(id, newPrice);

                                   response = new Response(result,result ? "Thêm lịch thành công" : "Thêm lịch thất bại", result);
                               }

                               case listAlbumByGenre -> {
                                   Map<String, Object> data = (Map<String, Object>) request.getData();

                                   String genreName = (String) data.get("genreName");
                                   int year = (int) data.get("year");

                                   response = new Response(
                                           true,
                                           "listAlbumByGenre",
                                           albumService.listAlbumByGenre(genreName, year)
                                   );
                               }

                               case getNumberOfAlbumsByGenre -> {
                                   response = new Response(true, "getNumberOfAlbumsByGenre", albumService.getNumberOfAlbumsByGenre());
                               }

                               default -> {
                                   response = new Response(false, "CommandType khong dung", null);
                               }
                           }

                           oos.writeObject(response);
                           oos.flush();
                       }
                   } catch (Exception e) {
                       throw new RuntimeException(e);
                   }
                }).start();
            }
        }
    }
}
