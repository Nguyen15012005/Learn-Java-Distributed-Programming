package service.impl;

import dao.InstructorDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Instructor;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.List;

public class InstructorServiceImpl extends UnicastRemoteObject implements service.InstructorService {

    public final InstructorDao instructorDao;

    public InstructorServiceImpl() throws RemoteException {
        instructorDao = new InstructorDao();
    }
    //6. Tìm giảng viên (`Instructor`) có thời điểm phân công văn phòng trước `timestamp`
    @Override
    public List<Instructor> findInstructorByOfficeAssignmentTimestampBefore(LocalDateTime timestamp) throws RemoteException{
        return instructorDao.findInstructorByOfficeAssignmentTimestampBefore(timestamp);
    }
}
