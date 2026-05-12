package service.impl;

import model.Student;
import repository.StudentRepository;
import repository.impl.StudentRepositoryImpl;
import service.StudentService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.List;

public class StudentServiceImpl extends UnicastRemoteObject implements service.StudentService {

    public final StudentRepository studentRepository;

    public StudentServiceImpl() throws RemoteException {
        studentRepository = new StudentRepositoryImpl();
    }

    //    5. Tìm sinh viên (`Student`) có ngày nhập học nằm giữa `startDate` và `endDate`
    @Override
    public List<Student> findStudentByEnrollmentDateBetween(LocalDateTime startDate, LocalDateTime endDate) throws RemoteException{

        return studentRepository.findStudentByEnrollmentDateBetween(startDate,endDate);
    }
}
