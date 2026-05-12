package network;

import service.CourseService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("TRUNGNGUYEN", 731);

        System.out.println("Client connect to server success");

        System.out.println("==============================================================");
        System.out.println("Client Test Chuc Nang Cua Course");
        CourseService courseService = (CourseService) registry.lookup("courseService");
        courseService.findCoursesByCreditsBetween(2, 3).forEach(System.out::println);
        courseService.findCourseByDepartmentNameContaining("Math").forEach(System.out::println);
        courseService.countStudentsByCourse().forEach((k,v)-> System.out.println(k.getTitle()  + ":" + v));
    }
}
