package service;

import model.Department;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DepartmentService extends Remote {
    //     5. Tìm phòng ban có hơn 3 Staff
    List<Department> findDepartmentHasMoreThan3Staffs() throws RemoteException;

    //    11. Department có số staff >= số staff của 1 phòng ban bất kỳ có ID
    List<Department> findDepartmentHasMoreStaffThanDepartment(String deptId) throws RemoteException;

    //    13. Department có tuổi trung bình staff > xxx
    List<Department> findDepartmentWithAvgAgeGreaterThan(int age) throws RemoteException;
}
