package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Instructor;
import model.Student;
import util.JPAUtil;

import java.time.LocalDateTime;
import java.util.List;

public class InstructorDao {
    //6. Tìm giảng viên (`Instructor`) có thời điểm phân công văn phòng trước `timestamp`
    public List<Instructor> findInstructorByOfficeAssignmentTimestampBefore(LocalDateTime timestamp){
        try (EntityManager em = JPAUtil.getEntityManager()) {
            String jpql =
                    """
                    select i
                    from Instructor i
                    join i.officeAssignment oa
                    where oa.timestamp < :timestamp
                    """;

//                    """
//                    select i
//                    from Instructor i
//                    where i.officeAssignment.timestamp < :timestamp
//                    """;

            TypedQuery<Instructor> query = em.createQuery(jpql, Instructor.class);
            query.setParameter("timestamp", timestamp);

            return query.getResultList();
        }
    }
}
