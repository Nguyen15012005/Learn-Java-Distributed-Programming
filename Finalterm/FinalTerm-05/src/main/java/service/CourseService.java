package service;

import dto.CourseDTO;
import model.Course;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface CourseService extends Remote {
    List<CourseDTO> findCoursesByCreditsBetween(int minCredits, int maxCredits) throws RemoteException;

    List<CourseDTO> findCourseByDepartmentNameContaining(String deptName) throws RemoteException;

    Map<CourseDTO, Long> countStudentsByCourse() throws RemoteException;
}
