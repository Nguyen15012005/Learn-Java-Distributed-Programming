package service.impl;

import dao.OnlineCourseDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.OnlineCourse;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class OnlineCourseServiceImpl extends UnicastRemoteObject implements service.OnlineCourseService {
    public final OnlineCourseDao onlineCourseDao;

    public OnlineCourseServiceImpl() throws RemoteException {
        onlineCourseDao = new OnlineCourseDao();
    }

    //    3. Tìm khóa học trực tuyến (`OnlineCourse`) có URL chứa `urlKeyword` và tín chỉ ≥ `minCredits`
    @Override
    public List<OnlineCourse> findOnlineCourseByUrlContainingAndCreditsGreaterThanEqual(String urlKeyword, int minCredits) throws RemoteException {
        return onlineCourseDao.findOnlineCourseByUrlContainingAndCreditsGreaterThanEqual(urlKeyword,minCredits);
    }
}
