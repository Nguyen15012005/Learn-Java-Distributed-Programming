import service.DepartmentService;
import service.ProjectService;
import service.StaffService;
import service.implement.DepartmentServiceImpl;
import service.implement.ProjectServiceImpl;
import service.implement.StaffServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class Service {
    public static void main(String[] args) throws Exception{
        Context context = new InitialContext();
        LocateRegistry.createRegistry(8080);

//        Tạo các lớp Service

        StaffService staffService = new StaffServiceImpl();
        DepartmentService departmentService = new DepartmentServiceImpl();
        ProjectService projectService = new ProjectServiceImpl();

        context.bind("rmi://TRUNGNGUYEN:8080/staffService", staffService);
        context.bind("rmi://TRUNGNGUYEN:8080/departmentService", departmentService);
        context.bind("rmi://TRUNGNGUYEN:8080/projectService", projectService);

        System.out.println("Service RMI has started");
    }
}
