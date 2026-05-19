package infrastructure.repository.impl;

import core.entity.Doctor;

import java.util.List;

public class DoctorRepositoryImpl extends GenericRepositoryImpl<Doctor, String> implements infrastructure.repository.DoctorRepository {

    @Override
    public List<Object[]> getDoctorWorkload(){
        return doInTransaction(em -> {
            String jpql = """
                    select d.id, d.fullName, Date(a.id.appointmentTime), count (a)
                    from Doctor d
                    join d.appointments a
                    group by  d.id, d.fullName, Date(a.id.appointmentTime)
                    having count (a) >= 2
                    """;
            return em.createQuery(jpql, Object[].class).getResultList();
        });
    }

}
