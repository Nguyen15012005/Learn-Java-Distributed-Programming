package infrastructure.repository;

import core.entity.Appointment;
import infrastructure.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AppointmentRepository {

    public boolean addAppointment(Appointment appointment){
        try (EntityManager em = JPAUtil.getEntityManager()){
            em.getTransaction().begin();
            em.persist(appointment);
            em.getTransaction().commit();
            return true;
        }
    }

    public List<Object[]> getAppointmentDetails(){
        try (EntityManager em = JPAUtil.getEntityManager()) {
            String jpql = """
                    select a.doctor.id, a.doctor.fullName, a.patient.id, a.patient.fullName, a.id.appointmentTime, a.status
                    from Appointment a
                    """;

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            return query.getResultList();
        }
    }
}
