package infrastructure.repository.impl;

import core.domain.JobStatus;
import infrastructure.config.JPAUtil;
import infrastructure.repository.CandidateRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CandidateRepositoryImpl implements infrastructure.repository.CandidateRepository {
    @Override
    public List<Object[]> findBySkillInOpenJobs(String skill) {
        try (EntityManager em = JPAUtil.getEntityManager()){
            String jpql = """
                    select c, j.title, a.appliedDate
                    from Candidate c
                    join c.skills s
                    join c.applications a
                    join a.job j
                    where s.name like :skill and j.status = :status
                    """;

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("skill", skill);
            query.setParameter("status", JobStatus.OPEN);

            return query.getResultList();
        }
    }

//    public static void main(String[] args) {
//        CandidateRepository candidateRepository = new CandidateRepositoryImpl();
//        candidateRepository.findBySkillInOpenJobs("Java").forEach(obj -> {
//            System.out.println("Ung viên có kỹ năng theo yêu cầu :");
//            System.out.println(obj[0] + "\n" + obj[1] + "\n" + obj[2]);
//        });
//    }
}
