package infrastructure.mapper;

import core.dto.AppointmentDTO;
import core.entity.Appointment;
import core.entity.Doctor;
import core.entity.Patient;
import lombok.Data;

public class Mapper {

    public static AppointmentDTO toAppointmentDTO(Appointment appointment){
        return new AppointmentDTO(
                appointment.getDoctor().getId(),
                appointment.getDoctor().getFullName(),
                appointment.getPatient().getId(),
                appointment.getPatient().getFullName(),
                appointment.getAppointmentTime(),
                appointment.getStatus()
        );
    }

    public static Appointment toAppointment(AppointmentDTO dto){

        Doctor doctor = new Doctor();
        doctor.setId(dto.getDoctorId());

        Patient patient = new Patient();
        patient.setId(dto.getPatientId());

        Appointment appointment = new Appointment();

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setStatus(dto.getStatus());

        return appointment;
    }

}
