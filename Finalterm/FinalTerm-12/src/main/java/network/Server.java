package network;

import service.AlbumService;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(731)){
            while (true){
                System.out.println("Server Started");

                Socket socket = serverSocket.accept();

                new Thread(()-> {
                    try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                         ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())){


                        while (true){
                            Request request = (Request) ois.readObject();
                            Response response = null;
                            AlbumService albumService = new AlbumService();
                            switch (request.getCommandType()){
                                case updatePriceOfAlbum -> {

                                    Map<String, Object> data = (Map<String, Object>) request.getData();

                                    String id =  (String) data.get("id");
                                    Double price =  (Double) data.get("price");
                                    boolean result = albumService.updatePriceOfAlbum(id, price);
                                    response = new Response(result, result? "update thanh cong" : "update that bai",result);
                                }
                                case listAlbumByGenre -> {
                                    Map<String, Object> data = (Map<String, Object>) request.getData();

                                    String genreName =  (String) data.get("genreName");
                                    int year =  (int) data.get("year");
                                    response = new Response(true, "Danh sach Album theo genre", albumService.listAlbumByGenre(genreName, year));
                                }
                                case getNumberOfAlbumsByGenre -> {
                                    response = new Response(true, "Danh sach Album theo the loai", albumService.getNumberOfAlbumsByGenre());
                                }
                            }

                            oos.writeObject(response);
                            oos.flush();

                        }
                    } catch (EOFException e) {
                        System.out.println("Client đã ngắt kết nối");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        }
    }
}
