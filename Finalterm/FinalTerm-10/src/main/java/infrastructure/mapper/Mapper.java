package infrastructure.mapper;

import core.dto.AppointmentDTO;
import core.entity.Appointment;
import core.entity.AppointmentId;
import core.entity.Doctor;
import core.entity.Patient;

public class Mapper {
    public static AppointmentDTO toDTO(Appointment appointment){
        return new AppointmentDTO(appointment.getDoctor().getId(), appointment.getDoctor().getFullName(), appointment.getPatient().getId(), appointment.getPatient().getFullName(), appointment.getId().getAppointmentTime(), appointment.getStatus());
    }

    public static Appointment toEntity(AppointmentDTO appointmentDTO){
        Doctor doctor = new Doctor();
        doctor.setId(appointmentDTO.getDoctorId());
        Patient patient = new Patient();
        patient.setId(appointmentDTO.getPatientId());
        AppointmentId id = new AppointmentId();
        id.setAppointmentTime(appointmentDTO.getTimeAppointment());
        return new Appointment(id, appointmentDTO.getStatus(), doctor, patient);
    }
}
