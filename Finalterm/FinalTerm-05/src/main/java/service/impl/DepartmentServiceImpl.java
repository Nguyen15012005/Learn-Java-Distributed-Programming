package service.impl;

import repository.DepartmentRepository;
import repository.impl.DepartmentRepositoryImpl;
import model.Department;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

public class DepartmentServiceImpl extends UnicastRemoteObject implements service.DepartmentService {
    public final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl() throws RemoteException {
        departmentRepository = new DepartmentRepositoryImpl();
    }

    //    4. Thống kê số khóa học theo khoa
    @Override
    public Map<Department, Long> countCourseByDepartment()throws RemoteException{
        return departmentRepository.countCourseByDepartment();
    }

//    8. Tìm phòng ban (`Department`) có ngân sách lớn nhất
@Override
public List<Department> findDepartmentWithMaxBudget()throws RemoteException{
        return departmentRepository.findDepartmentWithMaxBudget();
    }
}
