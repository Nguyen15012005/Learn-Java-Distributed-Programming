package service.impl;

import model.OnsiteCourse;
import repository.OnlineSiteRepository;
import repository.impl.OnlineSiteRepositoryImpl;
import service.OnlineSiteService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class OnlineSiteServiceImpl extends UnicastRemoteObject implements service.OnlineSiteService {

    public final OnlineSiteRepository onlineSiteRepository;

    public OnlineSiteServiceImpl() throws RemoteException {
        onlineSiteRepository = new OnlineSiteRepositoryImpl();
    }

    //   9. Tìm khóa học tại chỗ (`OnsiteCourse`) có ngày học (`days`) chứa `dayKeyword`
    @Override
    public List<OnsiteCourse> findOnsiteCourseByDaysContaining(String dayKeyword) throws RemoteException{
        return onlineSiteRepository.findOnsiteCourseByDaysContaining(dayKeyword);
    }
}
