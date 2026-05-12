package service;

import model.Instructor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

public interface InstructorService extends Remote {
    //6. Tìm giảng viên (`Instructor`) có thời điểm phân công văn phòng trước `timestamp`
    List<Instructor> findInstructorByOfficeAssignmentTimestampBefore(LocalDateTime timestamp) throws RemoteException;
}
