package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author TrungNguyen
 * @created 4/12/2026
 * @description
 */
public interface CandidateService extends Remote {
    List<Object[]> findBySkillInOpenJobs(String skill)  throws RemoteException;
}
