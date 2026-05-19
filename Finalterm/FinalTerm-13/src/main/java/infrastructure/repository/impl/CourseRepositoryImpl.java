package infrastructure.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import infrastructure.util.JPAUtil;

import java.util.List;

public class CourseRepositoryImpl implements infrastructure.repository.CourseRepository {
    @Override
    public List<Object[]> getOpenCoursesByGenre(String genreName) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            String jpql = """
                    select c.name, c.instructor.fullName, c.genre.name, c.tuitionFee, count (e.student)
                    from Course c
                    join c.enrollments e
                    where c.genre.name = :genreName and c.status = "OPEN" and e.status in ("REGISTERED", "COMPLETED")
                    group by c.name, c.instructor.fullName, c.genre.name, c.tuitionFee
                    """;

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class).setParameter("genreName", genreName);
            return query.getResultList();
        }
    }

//   public static void main(String[] args) {
//        CourseRepositoryImpl.getOpenCoursesByGenre("Classic").forEach(obj -> System.out.println(
//                        "Ten Khoa hoc: " + (String) obj[0] +
//                                " | Ten giang vien: " + (String) obj[1] +
//                                " | Ten loai nhac: " + (String) obj[2] +
//                                " | Hoc phi: " + (Double) obj[3] +
//                                " | So luong hoc vien: " + (Long) obj[4]
//                )
//        );
//    }
}
