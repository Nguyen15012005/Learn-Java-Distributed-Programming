package service.implement;

import dao.ProjectDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Project;
import util.EntityManagerUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ProjectServiceImpl extends UnicastRemoteObject implements service.ProjectService {
    public final ProjectDao projectDao;

    public ProjectServiceImpl() throws RemoteException {
        projectDao = new ProjectDao();
    }

    @Override
    public List<Project> findProjectByMinBudget(double minBudget) throws RemoteException{
        return projectDao.findProjectByMinBudget(minBudget);
    }

    //    4. Tìm Project mà có Staff
    @Override
    public List<Project> findProjectHasStaffs() throws RemoteException{
        return projectDao.findProjectHasStaffs();
    }

    //    9. Project có số nhân viên > trung bình số nhân viên tất cả project
    @Override
    public List<Project> findProjectsHasMoreStaffThanAverage() throws RemoteException{
        return projectDao.findProjectsHasMoreStaffThanAverage();
    }
}
