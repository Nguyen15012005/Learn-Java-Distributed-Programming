package repository;

import entity.Appointment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

import java.util.List;

public class AppointmentRepository {

    public boolean addAppointment(Appointment appointment){
        try(EntityManager em = JPAUtil.getEntityManager()){
            em.getTransaction().begin();
            if (appointment.getPatient().getId() == null || appointment.getDoctor().getId() == null){
                em.getTransaction().rollback();
                return false;
            }
            em.persist(appointment);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
//    Liệt kê danh sách lịch khám gồm: Mã số bác sĩ, tên bác sĩ, mã số bệnh nhân, tên bệnh nhân, thời
//    gian khám và trạng thái
    public List<Object[]> getAppointmentDetails(){
        try (EntityManager em = JPAUtil.getEntityManager()){
            String jpql = """
                    select a.doctor.id, a.doctor.fullName, a.patient.id, a.patient.fullName, a.appointmentID.appointmentTime, a.status
                    from Appointment a
                    group by a.doctor.id, a.doctor.fullName, a.patient.id, a.patient.fullName, a.appointmentID.appointmentTime, a.status
                    """;

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            return query.getResultList();
        }
    }

//
}
