import dao.CandidateDao;
import dao.JobDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        EntityManager em = Persistence
                .createEntityManagerFactory("mariadb-pu")
                .createEntityManager();

        CandidateDao candidateDao = new CandidateDao();
//
//        candidateDao.findBySkillInOpenJobs("Java").forEach(row -> {
//            System.out.println(Arrays.toString(row));
//        });
//
//        JobDao jobDao = new JobDao();
//        jobDao.countPerJobByCompany("TechSoft").forEach((k, v) -> {
//            System.out.println(k.getTitle() + "," + v);
//        });

    }

}
