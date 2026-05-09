import service.DepartmentService;
import service.ProjectService;
import service.StaffService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("TRUNGNGUYEN",8080);

        StaffService staffService = (StaffService) registry.lookup("staffService");
        DepartmentService departmentService = (DepartmentService) registry.lookup("departmentService");
        ProjectService projectService = (ProjectService) registry.lookup("projectService");


        staffService.findStaffByNameKeyword("a").forEach(System.out::println);
    }
}
