package service;

import model.OnsiteCourse;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface OnlineSiteService extends Remote {
    //   9. Tìm khóa học tại chỗ (`OnsiteCourse`) có ngày học (`days`) chứa `dayKeyword`
    List<OnsiteCourse> findOnsiteCourseByDaysContaining(String dayKeyword) throws RemoteException;
}
