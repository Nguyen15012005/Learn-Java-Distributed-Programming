package service;

import model.Course;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface CourseService extends Remote {
    List<Course> findCoursesByCreditsBetween(int minCredits, int maxCredits) throws RemoteException;

    List<Course> findCourseByDepartmentNameContaining(String deptName) throws RemoteException;

    Map<Course, Long> countStudentsByCourse() throws RemoteException;
}
