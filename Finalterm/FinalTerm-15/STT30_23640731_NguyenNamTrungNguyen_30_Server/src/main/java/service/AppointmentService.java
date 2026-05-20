package service;

import dto.AppointmentDTO;
import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.AppointmentRepository;
import util.JPAUtil;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentService {

    private final AppointmentRepository appointmentRepository = new AppointmentRepository();

    public boolean addAppointment(AppointmentDTO appointmentDTO){

        AppointmentID appointmentID = new AppointmentID(appointmentDTO.getDoctorId(), appointmentDTO.getPatientId(), appointmentDTO.getAppointmentTime());
        Doctor doctor = new Doctor();
        doctor.setId(appointmentDTO.getDoctorId());

        Patient patient = new Patient();
        patient.setId(appointmentDTO.getPatientId());
        Appointment appointment = new Appointment(appointmentID, doctor, patient, appointmentDTO.getStatus());
        return appointmentRepository.addAppointment(appointment);
    }
//    Liệt kê danh sách lịch khám gồm: Mã số bác sĩ, tên bác sĩ, mã số bệnh nhân, tên bệnh nhân, thời
//    gian khám và trạng thái
    public List<AppointmentDTO> getAppointmentDetails(){
        return appointmentRepository.getAppointmentDetails().stream().map(
                objects -> new AppointmentDTO(
                        (String) objects[0],
                        (String) objects[1],
                        (String) objects[2],
                        (String) objects[3],
                        (LocalDateTime) objects[4],
                        (Status) objects[5]
                )
        ).toList();
    }

}
