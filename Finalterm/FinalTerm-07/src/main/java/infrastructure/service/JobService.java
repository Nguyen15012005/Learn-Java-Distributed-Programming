package infrastructure.service;

import core.entity.Job;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface JobService extends Remote {
    Map<Job, Long> countPerJobByCompany(String companyName) throws RemoteException;
}
