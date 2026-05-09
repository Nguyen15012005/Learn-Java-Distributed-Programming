package service;

import model.Department;
import model.Staff;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface StaffService extends Remote {
    //    1.Tìm Staff có tên giống với keyword nhất
    List<Staff> findStaffByNameKeyword(String keyword) throws RemoteException;

    //    3. Tìm Staff có tuổi nằm trong từ và đến
    List<Staff> findStaffByAgeBetween(int minAge, int maxAge) throws RemoteException;

    //    6. Tìm số nhân viên theo phòng ban
    Map<Department, Long> countStaffByDepartment() throws RemoteException;

    //    7. Tìm Staff không có proj
    List<Staff> findStaffWithoutProject() throws RemoteException;

    //    8. Tìm Staff theo phoneNumber
    List<Staff> findStaffByPhone(String phone) throws RemoteException;

    //    10. Staff tham gia project có ngân sách lớn nhất
    List<Staff> findStaffInProjectWithMaxBudget() throws RemoteException;

    //    12. Staff không tham gia bất kỳ project nào có ngân sách < xxx
    List<Staff> findStaffNotJoinLowBudgetProject(double budget) throws RemoteException;
}
