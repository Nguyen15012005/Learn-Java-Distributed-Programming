package service.impl;

import repository.OnlineCourseRepository;
import repository.impl.OnlineCourseRepositoryImpl;
import model.OnlineCourse;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class OnlineCourseServiceImpl extends UnicastRemoteObject implements service.OnlineCourseService {
    public final OnlineCourseRepository onlineCourseRepository;

    public OnlineCourseServiceImpl() throws RemoteException {
        onlineCourseRepository = new OnlineCourseRepositoryImpl();
    }

    //    3. Tìm khóa học trực tuyến (`OnlineCourse`) có URL chứa `urlKeyword` và tín chỉ ≥ `minCredits`
    @Override
    public List<OnlineCourse> findOnlineCourseByUrlContainingAndCreditsGreaterThanEqual(String urlKeyword, int minCredits) throws RemoteException {
        return onlineCourseRepository.findOnlineCourseByUrlContainingAndCreditsGreaterThanEqual(urlKeyword,minCredits);
    }
}
