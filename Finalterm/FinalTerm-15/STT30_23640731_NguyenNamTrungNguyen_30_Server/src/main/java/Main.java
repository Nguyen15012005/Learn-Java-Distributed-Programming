import entity.*;
import repository.AppointmentRepository;
import repository.DoctorRepository;
import util.JPAUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
//        JPAUtil.getEntityManager();


//        Test 3.1
        AppointmentRepository appointmentRepository = new AppointmentRepository();
//        AppointmentID appointmentID = new AppointmentID("D01", "P03", LocalDateTime.now());
//        Doctor doctor = new Doctor();
//        doctor.setId("D01");
//
//        Patient patient = new Patient();
//        patient.setId("P03");
//        Appointment appointment = new Appointment(appointmentID, doctor, patient, Status.PENDING);
//        boolean result = appointmentRepository.addAppointment(appointment);
//        System.out.println(result ? "Them thanh cong" : "Them that bai");

//        Test 3.2

//        appointmentRepository.getAppointmentDetails().forEach(objects -> System.out.println(
//                (String) objects[0] +
//                (String) objects[1] +
//                (String) objects[2] +
//                (String) objects[3] +
//                (LocalDateTime) objects[4] +
//                (Status) objects[5]
//        ));

        DoctorRepository doctorRepository = new DoctorRepository();
        doctorRepository.getDoctorWorkload().forEach(objects -> System.out.println(
                (String) objects[0] +
                (String) objects[1] +
                (Date) objects[2] +
                (Long) objects[3]
        ));
    }
}
