package infrastructure.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CandidateService extends Remote {
    List<Object[]> findBySkillInOpenJobs(String skill) throws RemoteException;
}
