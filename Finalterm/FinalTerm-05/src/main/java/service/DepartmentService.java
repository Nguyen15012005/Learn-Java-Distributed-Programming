package service;

import model.Department;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface DepartmentService extends Remote {
    //    4. Thống kê số khóa học theo khoa
    Map<Department, Long> countCourseByDepartment() throws RemoteException;

    //    8. Tìm phòng ban (`Department`) có ngân sách lớn nhất
    List<Department> findDepartmentWithMaxBudget() throws RemoteException;
}
