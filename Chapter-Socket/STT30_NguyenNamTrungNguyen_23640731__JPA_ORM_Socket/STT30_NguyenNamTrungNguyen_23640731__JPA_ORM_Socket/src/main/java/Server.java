import service.CandidateService;
import service.CompanyService;
import service.JobService;
import service.implement.CandidateServiceImpl;
import service.implement.CompanyServiceImpl;
import service.implement.JobServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

/**
 * @author TrungNguyen
 * @created 4/12/2026
 * @description
 */
public class Server {
    public static void main(String[] args) throws Exception{
        Context context = new InitialContext();
        LocateRegistry.createRegistry(8080);

//        Tạo các lớp service

        CandidateService candidateService = new CandidateServiceImpl();
        JobService jobService = new JobServiceImpl();
        CompanyService companyService = new CompanyServiceImpl() {
        };

        context.bind("rmi://TRUNGNGUYEN:8080/candidateService", candidateService);
        context.bind("rmi://TRUNGNGUYEN:8080/jobService", jobService);
        context.bind("rmi://TRUNGNGUYEN:8080/companyService", companyService);

        System.out.println("Server RMI has started");
    }
}
