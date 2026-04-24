package service.implement;

import dao.CandidateDao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * @author TrungNguyen
 * @created 4/12/2026
 * @description
 */
public class CandidateServiceImpl extends UnicastRemoteObject implements service.CandidateService {

    private final CandidateDao candidateDao;

    public CandidateServiceImpl() throws RemoteException {
        candidateDao = new CandidateDao();
    }

    @Override
    public List<Object[]> findBySkillInOpenJobs(String skill)  throws RemoteException {
        return candidateDao.findBySkillInOpenJobs(skill);
    }
}
