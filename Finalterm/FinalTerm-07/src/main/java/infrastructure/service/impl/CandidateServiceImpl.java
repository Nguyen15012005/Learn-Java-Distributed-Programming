package infrastructure.service.impl;

import infrastructure.repository.CandidateRepository;
import infrastructure.repository.impl.CandidateRepositoryImpl;
import infrastructure.service.CandidateService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CandidateServiceImpl extends UnicastRemoteObject implements CandidateService {

    public final CandidateRepository candidateRepository;

    public CandidateServiceImpl() throws RemoteException {
        candidateRepository = new CandidateRepositoryImpl();
    }

    @Override
    public List<Object[]> findBySkillInOpenJobs(String skill) throws RemoteException{
        return candidateRepository.findBySkillInOpenJobs(skill);
    }

}
