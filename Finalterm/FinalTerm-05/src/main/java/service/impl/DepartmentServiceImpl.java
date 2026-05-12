package service.impl;

import dao.DepartmentDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Department;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DepartmentServiceImpl extends UnicastRemoteObject implements service.DepartmentService {
    public final DepartmentDao departmentDao;

    public DepartmentServiceImpl() throws RemoteException {
        departmentDao = new DepartmentDao();
    }

    //    4. Thống kê số khóa học theo khoa
    @Override
    public Map<Department, Long> countCourseByDepartment()throws RemoteException{
        return departmentDao.countCourseByDepartment();
    }

//    8. Tìm phòng ban (`Department`) có ngân sách lớn nhất
@Override
public List<Department> findDepartmentWithMaxBudget()throws RemoteException{
        return departmentDao.findDepartmentWithMaxBudget();
    }
}
