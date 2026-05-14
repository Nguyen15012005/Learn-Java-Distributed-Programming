package infrastructure.persistence_repository.impl;

import core.entity.Appointment;
import infrastructure.config_database.JPAUtil;
import infrastructure.persistence_repository.AppointmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AppointmentRepositoryImpl implements AppointmentRepository {
    @Override
    public boolean addAppointment(Appointment appointment){
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {

            tr.begin();
            em.persist(appointment);
            tr.commit();
            return true;
        }
        catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
        finally {
            em.close();
        }
        return false;
    }

    @Override
    public List<Object[]> getAppointmentDetails(){

        try (EntityManager em = JPAUtil.getEntityManager()){
            String jpql = """
                    select a.doctor.id, a.doctor.fullName, a.patient.id, a.patient.fullName, a.appointmentTime, a.status
                    from Appointment a
                    """;
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);

            return query.getResultList();

        }
    }

    @Override
    public List<Object[]> getDoctorWorkload(){

        try (EntityManager em = JPAUtil.getEntityManager()){
            String jpql = """
                    select a.doctor.id, a.doctor.fullName, Date(a.appointmentTime), count (a)
                    from Appointment a
                    group by a.doctor.id, a.doctor.fullName, Date(a.appointmentTime)
                    having count (a) >= 2
                    """;
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);

            return query.getResultList();

        }
    }
}
