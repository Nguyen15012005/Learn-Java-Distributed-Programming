package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Student;
import util.JPAUtil;

import java.time.LocalDateTime;
import java.util.List;

public class StudentRepositoryImpl implements repository.StudentRepository {
//    5. Tìm sinh viên (`Student`) có ngày nhập học nằm giữa `startDate` và `endDate`
@Override
public List<Student> findStudentByEnrollmentDateBetween(LocalDateTime startDate, LocalDateTime endDate){
        try (EntityManager em = JPAUtil.getEntityManager()) {
            String jpql =
                    """
                    SELECT s
                    FROM Student s
                    WHERE s.enrollmentDate BETWEEN :startDate AND :endDate
                    """;

            TypedQuery<Student> query = em.createQuery(jpql, Student.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);

            return query.getResultList();
        }
    }
}
