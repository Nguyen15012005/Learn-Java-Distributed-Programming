package service.impl;

import dao.CourseDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Course;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CourseServiceImpl extends UnicastRemoteObject implements service.CourseService {
    public final CourseDao courseDao;

    public CourseServiceImpl() throws RemoteException {
        courseDao = new CourseDao();
    }

    @Override
    public List<Course> findCoursesByCreditsBetween(int minCredits, int maxCredits) throws RemoteException{
        return courseDao.findCoursesByCreditsBetween(minCredits,maxCredits);
    }

    @Override
    public List<Course> findCourseByDepartmentNameContaining(String deptName)throws RemoteException{
        return courseDao.findCourseByDepartmentNameContaining(deptName);

    }

    @Override
    public Map<Course, Long> countStudentsByCourse()throws RemoteException{
        return courseDao.countStudentsByCourse();
    }
}
