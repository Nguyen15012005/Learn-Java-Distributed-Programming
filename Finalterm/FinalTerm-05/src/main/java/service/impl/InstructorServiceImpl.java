package service.impl;

import repository.InstructorRepository;
import repository.impl.InstructorRepositoryImpl;
import model.Instructor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.List;

public class InstructorServiceImpl extends UnicastRemoteObject implements service.InstructorService {

    public final InstructorRepository instructorRepository;

    public InstructorServiceImpl() throws RemoteException {
        instructorRepository = new InstructorRepositoryImpl();
    }
    //6. Tìm giảng viên (`Instructor`) có thời điểm phân công văn phòng trước `timestamp`
    @Override
    public List<Instructor> findInstructorByOfficeAssignmentTimestampBefore(LocalDateTime timestamp) throws RemoteException{
        return instructorRepository.findInstructorByOfficeAssignmentTimestampBefore(timestamp);
    }
}
