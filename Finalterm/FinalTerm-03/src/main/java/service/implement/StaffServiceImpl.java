package service.implement;

import dao.StaffDao;
import model.Department;
import model.Staff;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

public class StaffServiceImpl extends UnicastRemoteObject implements service.StaffService {
    public final StaffDao staffDao;

    public StaffServiceImpl() throws RemoteException {
        staffDao = new StaffDao();
    }

    //    1.Tìm Staff có tên giống với keyword nhất
    @Override
    public List<Staff> findStaffByNameKeyword(String keyword) throws RemoteException{
        return staffDao.findStaffByNameKeyword(keyword);
    }

    //    3. Tìm Staff có tuổi nằm trong từ và đến
    @Override
    public List<Staff> findStaffByAgeBetween(int minAge, int maxAge) throws RemoteException{
        return staffDao.findStaffByAgeBetween(minAge, maxAge);
    }

    //    6. Tìm số nhân viên theo phòng ban
    @Override
    public Map<Department, Long> countStaffByDepartment() throws RemoteException{
        return staffDao.countStaffByDepartment();
    }

    //    7. Tìm Staff không có proj
    @Override
    public List<Staff> findStaffWithoutProject() throws RemoteException{
        return staffDao.findStaffWithoutProject();
    }

    //    8. Tìm Staff theo phoneNumber
    @Override
    public List<Staff> findStaffByPhone(String phone) throws RemoteException{
        return staffDao.findStaffByPhone(phone);
    }

    //    10. Staff tham gia project có ngân sách lớn nhất
    @Override
    public List<Staff> findStaffInProjectWithMaxBudget() throws RemoteException{
        return staffDao.findStaffInProjectWithMaxBudget();
    }

    //    12. Staff không tham gia bất kỳ project nào có ngân sách < xxx
    @Override
    public List<Staff> findStaffNotJoinLowBudgetProject(double budget) throws RemoteException{
        return staffDao.findStaffNotJoinLowBudgetProject(budget);
    }
}
