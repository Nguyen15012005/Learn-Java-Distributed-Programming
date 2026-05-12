package service;

import model.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

public interface StudentService extends Remote {
    //    5. Tìm sinh viên (`Student`) có ngày nhập học nằm giữa `startDate` và `endDate`
    List<Student> findStudentByEnrollmentDateBetween(LocalDateTime startDate, LocalDateTime endDate) throws RemoteException;
}
