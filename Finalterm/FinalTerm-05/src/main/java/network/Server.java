package network;

import service.*;
import service.impl.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();
        LocateRegistry.createRegistry(731);

//        Tạo các lớp service
        CourseService courseService = new CourseServiceImpl();
        DepartmentService departmentService = new DepartmentServiceImpl();
        InstructorService instructorService = new InstructorServiceImpl();
        OnlineCourseService onlineCourseService = new OnlineCourseServiceImpl();
        OnlineSiteService onlineSiteService = new OnlineSiteServiceImpl();
        StudentService studentService = new StudentServiceImpl();

        context.bind("rmi://TRUNGNGUYEN:731/courseService", courseService);
        context.bind("rmi://TRUNGNGUYEN:731/departmentService", departmentService);
        context.bind("rmi://TRUNGNGUYEN:731/instructorService", instructorService);
        context.bind("rmi://TRUNGNGUYEN:731/onlineCourseService", onlineCourseService);
        context.bind("rmi://TRUNGNGUYEN:731/onlineSiteService", onlineSiteService);
        context.bind("rmi://TRUNGNGUYEN:731/studentService", studentService);

        System.out.println("Server Started:");
    }
}
