package infrastructure.service.impl;

import core.entity.Job;
import infrastructure.config.JPAUtil;
import infrastructure.repository.JobRepository;
import infrastructure.repository.impl.JobRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JobServiceImpl extends UnicastRemoteObject implements infrastructure.service.JobService {

    public final JobRepository jobRepository;

    public JobServiceImpl() throws RemoteException {
        jobRepository = new JobRepositoryImpl();
    }

    @Override
    public Map<Job, Long> countPerJobByCompany(String companyName) throws RemoteException{
        return jobRepository.countPerJobByCompany(companyName);
    }
}
