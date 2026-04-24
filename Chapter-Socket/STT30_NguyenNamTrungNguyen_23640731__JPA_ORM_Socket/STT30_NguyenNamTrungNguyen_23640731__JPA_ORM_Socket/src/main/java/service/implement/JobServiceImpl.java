package service.implement;

import dao.CandidateDao;
import dao.JobDao;
import model.Job;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

/**
 * @author TrungNguyen
 * @created 4/12/2026
 * @description
 */
public class JobServiceImpl extends UnicastRemoteObject implements service.JobService {

    private final JobDao jobDao;

    public JobServiceImpl() throws RemoteException {
        jobDao = new JobDao();
    }

    @Override
    public Map<Job, Long> countPerJobByCompany(String companyName) throws RemoteException {
        return jobDao.countPerJobByCompany(companyName);
    }
}
