package dao;

import common.AbstractGenericDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Candidate;
import util.JPAUtil;

import java.util.List;

/**
 * @author TrungNguyen
 * @created 4/12/2026
 * @description
 */
public class CandidateDao extends AbstractGenericDAO<Candidate, Long> {

    public CandidateDao() {
        super(Candidate.class);
    }

    //    Tìm danh sách các ứng viên có kỹ năng theo yêu cầu ứng tuyển và
    //    đã ứng tuyển vào các công việc đang ở trạng thái OPEN.
    //     => (Kết quả trả về gồm: Ứng viên, tiêu đề công việc và ngày ứng tuyển)
    public List<Object[]> findBySkillInOpenJobs(String skill){
        try(EntityManager em = JPAUtil.getEntityManager()){
            String jpql = """
                SELECT c, j.title, a.appliedDate
                   FROM Application a
                   JOIN a.candidate c
                   JOIN a.job j
                   JOIN c.skills s
                   WHERE s.name = :skill
                   AND j.status = 'OPEN'
            """;

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("skill", skill);
            return query.getResultList();
        }
    }
}
