package service.impl;

import dto.CourseDTO;
import mapper.CourseMapper;
import repository.CourseRepository;
import repository.impl.CourseRepositoryImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CourseServiceImpl extends UnicastRemoteObject implements service.CourseService {
    public final CourseRepository courseRepository;

    public CourseServiceImpl() throws RemoteException {
        courseRepository = new CourseRepositoryImpl();
    }

    @Override
    public List<CourseDTO> findCoursesByCreditsBetween(int minCredits, int maxCredits) throws RemoteException{
        return courseRepository.findCoursesByCreditsBetween(minCredits,maxCredits).stream().map(CourseMapper::toDTO).toList();
    }

    @Override
    public List<CourseDTO> findCourseByDepartmentNameContaining(String deptName)throws RemoteException{
        return courseRepository.findCourseByDepartmentNameContaining(deptName).stream().map(CourseMapper::toDTO).toList();

    }

    @Override
    public Map<CourseDTO, Long> countStudentsByCourse()throws RemoteException{
        return courseRepository.countStudentsByCourse().entrySet().stream().collect(Collectors.toMap(entry -> CourseMapper.toDTO(entry.getKey()),Map.Entry::getValue));
    }
}
