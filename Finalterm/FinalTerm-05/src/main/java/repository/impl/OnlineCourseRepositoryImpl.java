package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.OnlineCourse;
import util.JPAUtil;

import java.util.List;

public class OnlineCourseRepositoryImpl implements repository.OnlineCourseRepository {
//    3. Tìm khóa học trực tuyến (`OnlineCourse`) có URL chứa `urlKeyword` và tín chỉ ≥ `minCredits`
@Override
public List<OnlineCourse> findOnlineCourseByUrlContainingAndCreditsGreaterThanEqual(String urlKeyword, int minCredits){

        try (EntityManager em = JPAUtil.getEntityManager()) {
            String jpql =
                    """
                    select onlcouse
                    from OnlineCourse onlcouse
                    where onlcouse.url like concat("%", :urlKeyword, "%")
                    and onlcouse.credit >= :minCredits
                    """;

            TypedQuery<OnlineCourse> query = em.createQuery(jpql, OnlineCourse.class);
            query.setParameter("urlKeyword", urlKeyword);
            query.setParameter("minCredits", minCredits);

            return query.getResultList();
        }
    }
}
