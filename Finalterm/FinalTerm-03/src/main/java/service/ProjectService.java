package service;

import model.Project;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ProjectService extends Remote {
    List<Project> findProjectByMinBudget(double minBudget) throws RemoteException;

    //    4. Tìm Project mà có Staff
    List<Project> findProjectHasStaffs() throws RemoteException;

    //    9. Project có số nhân viên > trung bình số nhân viên tất cả project
    List<Project> findProjectsHasMoreStaffThanAverage() throws RemoteException;
}
