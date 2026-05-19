package infrastructure.repository.impl;

import core.entity.Appointment;
import infrastructure.repository.AppointmentRepository;

import java.util.List;

public class AppointmentRepositoryImpl extends GenericRepositoryImpl<Appointment, String> implements AppointmentRepository {

    @Override
    public boolean addAppointment(Appointment appointment){
        return doInTransaction(em -> {
           em.persist(appointment);
           return true;
        });
    }

    @Override
    public List<Object[]> getAppointmentDetails(){
        return doInTransaction(em -> {
            String jpql = """
                    select a.doctor.id, a.doctor.fullName, a.patient.id, a.patient.fullName, a.id.appointmentTime, a.status
                    from Appointment a
                    """;

            return em.createQuery(jpql, Object[].class).getResultList();
        });
    }

}
