package infrastructure.service;

import core.entity.Appointment;
import core.dto.AppointmentDTO;
import core.entity.AppointmentID;
import core.entity.Doctor;
import core.entity.Patient;
import core.enums.Status;
import infrastructure.repository.AppointmentRepository;
import infrastructure.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentService {

    public final AppointmentRepository appointmentRepository;

    public AppointmentService() {
        appointmentRepository = new AppointmentRepository();
    }


    public boolean addAppointment(AppointmentDTO appointmentDTO){
        Doctor doctor = new Doctor();
        doctor.setId(appointmentDTO.getDoctorId());

        Patient patient = new Patient();
        patient.setId(appointmentDTO.getPatientId());

        AppointmentID appointmentID = new AppointmentID(appointmentDTO.getPatientId(), appointmentDTO.getDoctorId(), appointmentDTO.getAppointmentTime());
        Appointment appointment = new Appointment(appointmentID, appointmentDTO.getStatus(), doctor, patient);
        return appointmentRepository.addAppointment(appointment);
    }

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
