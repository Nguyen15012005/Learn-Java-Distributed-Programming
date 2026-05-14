package infrastructure.persistence_repository;

import core.entity.Appointment;

import java.util.List;

public interface AppointmentRepository {
    boolean addAppointment(Appointment appointment);

    List<Object[]> getAppointmentDetails();

    List<Object[]> getDoctorWorkload();
}
