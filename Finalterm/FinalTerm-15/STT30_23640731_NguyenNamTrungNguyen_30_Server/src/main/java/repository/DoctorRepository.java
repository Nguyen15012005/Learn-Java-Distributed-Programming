package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

import java.util.List;

public class DoctorRepository {
    public List<Object[]> getDoctorWorkload(){
        try (EntityManager em = JPAUtil.getEntityManager()){
            String jpql = """
                    select a.doctor.id, a.doctor.fullName, Date(a.id.appointmentTime), count (a)
                    from Appointment a
                    group by a.doctor.id, a.doctor.fullName, Date(a.id.appointmentTime)
                    having count (a) >= 2
                    """;

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            return query.getResultList();
        }
    }
}
