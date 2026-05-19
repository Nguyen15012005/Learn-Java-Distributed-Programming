package infrastructure.service.impl;

import infrastructure.repository.CourseRepository;
import infrastructure.repository.impl.CourseRepositoryImpl;
import infrastructure.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CourseServiceImpl extends UnicastRemoteObject implements infrastructure.service.CourseService {

    private final CourseRepository courseRepository = new CourseRepositoryImpl();

    protected CourseServiceImpl() throws RemoteException {
    }

    @Override
    public List<Object[]> getOpenCoursesByGenre(String genreName) throws RemoteException {
       return courseRepository.getOpenCoursesByGenre(genreName);
    }


}
