package service.impl;

import model.OnsiteCourse;
import service.OnlineSiteService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class OnlineSiteServiceImpl extends UnicastRemoteObject implements service.OnlineSiteService {

    public final OnlineSiteService onlineSiteDao;

    public OnlineSiteServiceImpl() throws RemoteException {
        onlineSiteDao = new OnlineSiteServiceImpl();
    }

    //   9. Tìm khóa học tại chỗ (`OnsiteCourse`) có ngày học (`days`) chứa `dayKeyword`
    @Override
    public List<OnsiteCourse> findOnsiteCourseByDaysContaining(String dayKeyword) throws RemoteException{
        return onlineSiteDao.findOnsiteCourseByDaysContaining(dayKeyword);
    }
}
