import dao.CandidateDao;
import dao.JobDao;
import service.CandidateService;
import service.JobService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

/**
 * @author TrungNguyen
 * @created 4/12/2026
 * @description
 */
public class Client {
    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("TRUNGNGUYEN", 8080);

        CandidateService candidateService = (CandidateService) registry.lookup("candidateService");
        JobService jobService = (JobService) registry.lookup("jobService");


        candidateService.findBySkillInOpenJobs("Java").forEach(row -> {
            System.out.println(Arrays.toString(row));
        });

        jobService.countPerJobByCompany("TechSoft").forEach((k, v) -> {
            System.out.println(k.getTitle() + "," + v);
        });


    }
}
