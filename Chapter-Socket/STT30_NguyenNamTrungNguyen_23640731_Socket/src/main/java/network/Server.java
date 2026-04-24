package network;

import dto.RecordDTO;
import org.hibernate.sql.Update;
import repository.IPersonRepository;
import repository.impl.PersonRepository;
import service.IPersonService;
import service.IRecordService;
import service.IVaccineService;
import service.impl.PersonService;
import service.impl.RecordService;
import service.impl.VaccineService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(1731);
            ExecutorService pool = Executors.newFixedThreadPool(10);
        ){
            System.out.println("Server Started");
            while(true){
                Socket socket = serverSocket.accept(); //1 client
                System.out.println(socket.getInetAddress().getHostName());
                System.out.println(socket.getRemoteSocketAddress());
                RequestHandler handler = new RequestHandler(socket);
                pool.submit(handler);
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
class RequestHandler implements Runnable{
    private Socket socket;
    private final IPersonService personService = new PersonService();
    private final IVaccineService vaccineService = new VaccineService();
    private final IRecordService recordService = new RecordService();
    public RequestHandler(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try(ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());){
            while(true){
                Request request = (Request) in.readObject();
                Respone respone = processingRequest(request);
                out.writeObject(respone); // return respone to client
                out.flush();

            }
        }catch (Exception e){
            throw  new RuntimeException(e);}
    }

    private Respone processingRequest(Request request) {
        Respone respone = null;
        switch (request.getCommandType()){
            case CREATE_RECORD -> {
                RecordDTO recordDTO = (RecordDTO) request.getData();
                boolean created = recordService.createNewRecord(recordDTO);

                respone = Respone.builder()
                        .success(created)
                        .data(null)
                        .message(created?"Success":"Fail")
                        .build();

            }
            case UPDATE_RECORD -> {
                RecordDTO recordDTO = (RecordDTO) request.getData();
                boolean updated = recordService.updateScheduledRecord(recordDTO.getRecordId(), recordDTO.getStatus());
                respone = Respone
                        .builder()
                        .success(true)
                        .data(recordDTO)
                        .message(updated?"Record updated successfully":"Failed to update record")

                        .build();
            }
            case LIST_OBESE_PEOPLE -> {
                respone = Respone.builder()
                        .success(false)
                        .data(null)
                        .message("LIST_OBESE_PEOPLE not implemented yet")
                        .build();
            }
            case COUNT_VACCINES -> {
                respone = Respone.builder()
                        .success(false)
                        .data(null)
                        .message("COUNT_VACCINES not implemented yet")
                        .build();
            }
            default -> {
                respone = Respone.builder()
                        .success(false)
                        .data(null)
                        .message("Unknown command type")
                        .build();
            }
        }
        return respone;
    }
}
