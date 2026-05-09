package service.implement;

import dao.DepartmentDao;
import model.Department;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DepartmentServiceImpl extends UnicastRemoteObject implements service.DepartmentService {
    public final DepartmentDao departmentDao;

    public DepartmentServiceImpl() throws RemoteException {
        departmentDao = new DepartmentDao();
    }

//     5. Tìm phòng ban có hơn 3 Staff
@Override
public List<Department> findDepartmentHasMoreThan3Staffs() throws RemoteException{
        return departmentDao.findDepartmentHasMoreThan3Staffs();
    }

    //    11. Department có số staff >= số staff của 1 phòng ban bất kỳ có ID
    @Override
    public List<Department> findDepartmentHasMoreStaffThanDepartment(String deptId) throws RemoteException{
        return departmentDao.findDepartmentHasMoreStaffThanDepartment(deptId);
    }

    //    13. Department có tuổi trung bình staff > xxx
    @Override
    public List<Department> findDepartmentWithAvgAgeGreaterThan(int age) throws RemoteException{
       return departmentDao.findDepartmentWithAvgAgeGreaterThan(age);
    }
}
