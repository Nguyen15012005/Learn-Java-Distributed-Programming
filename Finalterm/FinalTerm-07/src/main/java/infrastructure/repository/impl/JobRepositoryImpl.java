package infrastructure.repository.impl;

import core.domain.JobStatus;
import core.entity.Job;
import infrastructure.config.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JobRepositoryImpl implements infrastructure.repository.JobRepository {
    @Override
    public Map<Job, Long> countPerJobByCompany(String companyName) {
        try (EntityManager em = JPAUtil.getEntityManager()){
            String jpql = """
                    select j, count(a)
                    from Job j
                    left join j.applications a
                    where j.company.name = :companyName
                    group by j
                    order by count (a) desc
                    """;

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("companyName", companyName);

            return query.getResultList()
                    .stream()
                    .collect(Collectors
                            .toMap(
                                    objects -> (Job) objects[0],
                                    objects -> (Long) objects[1],
                                    (o1, o2)-> o1,
                                    LinkedHashMap::new
                            )
                    );
        }

    }
//    public static void main(String[] args) {
//        JobRepositoryImpl jobRepository = new JobRepositoryImpl();
//        jobRepository.countPerJobByCompany("TechSoft").forEach((k, v) -> {
//            System.out.println(k.getTitle() + " | " + v);
//        });
//    }
}
