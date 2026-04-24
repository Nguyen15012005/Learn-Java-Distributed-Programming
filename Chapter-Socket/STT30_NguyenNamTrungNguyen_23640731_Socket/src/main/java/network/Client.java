package network;

import dto.RecordDTO;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("DESKTOP-C8JQH8B", 1731);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Scanner sc = new Scanner(System.in);

        ) {
            while(true){
                System.out.println("===Menu===");
                System.out.println("1. Create record");
                System.out.println("2. Update record");
                System.out.println("3. List obese people");
                System.out.println("4. Count vaccines");

                int choice = sc.nextInt();
                Request request = null;
                switch (choice) {
                    case 1 -> {
                        RecordDTO recordDTO = RecordDTO.builder()
                                .personId("P005")
                                .vaccineId("V008")
                                .doseNumber(2)
                                .injectionDate(LocalDate.now().plusDays(10))
                                .build();
                        request = Request.builder()
                                .commandType(CommandType.CREATE_RECORD)
                                .data(recordDTO)
                                .build();
                    }
                    case 2 -> {
                        RecordDTO recordDTO = RecordDTO
                                .builder()
                                .recordId(8L)
                                .status(entity.DoseStatus.COMPLETED)
                                .build();

                        request = Request.builder()
                                .commandType(CommandType.UPDATE_RECORD)
                                .data(recordDTO)
                                .build();
                    }
                    case 3 -> {
                        request = Request.builder()
                                .commandType(CommandType.LIST_OBESE_PEOPLE)
                                .build();

                    }
                    case 4 -> {

                    }

                }


                out.writeObject(request);
                out.flush();

                Respone respone = (Respone) in.readObject();
                System.out.println(respone);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
