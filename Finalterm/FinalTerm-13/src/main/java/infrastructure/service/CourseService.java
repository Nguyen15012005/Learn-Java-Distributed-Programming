package infrastructure.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CourseService extends Remote {
    List<Object[]> getOpenCoursesByGenre(String genreName) throws RemoteException;
}
