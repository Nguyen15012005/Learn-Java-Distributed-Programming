package service;

import model.OnlineCourse;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface OnlineCourseService extends Remote {
    //    3. Tìm khóa học trực tuyến (`OnlineCourse`) có URL chứa `urlKeyword` và tín chỉ ≥ `minCredits`
    List<OnlineCourse> findOnlineCourseByUrlContainingAndCreditsGreaterThanEqual(String urlKeyword, int minCredits) throws RemoteException;
}
