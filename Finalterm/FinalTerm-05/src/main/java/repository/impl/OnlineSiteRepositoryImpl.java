package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.OnsiteCourse;
import util.JPAUtil;

import java.util.List;

public class OnlineSiteRepositoryImpl implements repository.OnlineSiteRepository {
//   9. Tìm khóa học tại chỗ (`OnsiteCourse`) có ngày học (`days`) chứa `dayKeyword`
@Override
public List<OnsiteCourse> findOnsiteCourseByDaysContaining(String dayKeyword){

        try (EntityManager em = JPAUtil.getEntityManager()) {
            String jpql =
                    """
                    select o
                    from OnsiteCourse o
                    where o.days like concat('%', :dayKeyword , '%')
                    """;

            TypedQuery<OnsiteCourse> query = em.createQuery(jpql, OnsiteCourse.class);
            query.setParameter("dayKeyword", dayKeyword);

            return query.getResultList();
        }
    }
}
