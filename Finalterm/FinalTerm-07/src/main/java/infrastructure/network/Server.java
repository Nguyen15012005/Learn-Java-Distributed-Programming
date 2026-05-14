package infrastructure.network;

import infrastructure.service.CandidateService;
import infrastructure.service.JobService;
import infrastructure.service.impl.CandidateServiceImpl;
import infrastructure.service.impl.JobServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(731)){
            System.out.println("Server started");

            while (true){
                Socket socket = serverSocket.accept();
                new Thread(()-> {
                    try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                         ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
                    ){
                        CandidateService candidateService = new CandidateServiceImpl();
                        JobService jobService = new JobServiceImpl();

                        while (true){
                            Request request = (Request) ois.readObject();
                            Response response;

                            switch (request.getConmmandType()){
                                case COUNTPERJOBBYCOMPANY -> {
                                    String companyName = (String) request.getData();
                                    response = new Response(true, "Danh sách ứng viên theo yêu cầu: ", jobService.countPerJobByCompany(companyName));
                                }
                                case FINDBYSKILLINOPENJOBS -> {
                                    String nameSkill = (String) request.getData();
                                    response = new Response(true, "Danh sách ứng viên theo yêu cầu: ", candidateService.findBySkillInOpenJobs(nameSkill));
                                }
                                default -> {
                                    response = new Response(false, "Command không hợp lệ!", null);
                                }
                            }
                            oos.writeObject(response);
                            oos.flush();
                        }

                    } catch (Exception e) {
                        throw new IllegalArgumentException(e.getMessage());
                    }
                }).start();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }
}
