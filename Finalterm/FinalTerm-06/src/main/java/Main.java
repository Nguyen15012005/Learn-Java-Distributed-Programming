import core.domain.Status;
import core.dto.AppointmentDTO;
import infrastructure.persistence_repository.AppointmentRepository;
import infrastructure.persistence_repository.impl.AppointmentRepositoryImpl;
import infrastructure.service.impl.AppointmentServiceImpl;

import java.rmi.RemoteException;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws RemoteException {
        AppointmentRepository appointmentRepository = new AppointmentRepositoryImpl();
        AppointmentService appointmentService = new AppointmentServiceImpl();
        AppointmentDTO appointmentDTO = new AppointmentDTO("D01", "P01", LocalDateTime.now(), Status.PENDING);

        boolean result = appointmentService.addAppointment(appointmentDTO);
        if(result) System.out.println("Thêm bác sĩ thành công");
        else System.out.println("Thêm bác sĩ thất bại");
        appointmentRepository.getAppointmentDetails().forEach(objects -> System.out.println(objects[0] + " | " + objects[1] + " | " + objects[2] + " | " + objects[3] + " | " + objects[4] + " | " + objects[5]));
        appointmentRepository.getDoctorWorkload().forEach(objects -> System.out.println(objects[0] + " | " + objects[1] + " | " + objects[2] + " | " + objects[3]));

    }
}
