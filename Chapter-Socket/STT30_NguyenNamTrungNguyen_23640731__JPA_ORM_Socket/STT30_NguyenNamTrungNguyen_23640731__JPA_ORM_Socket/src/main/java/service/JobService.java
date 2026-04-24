package service;

import model.Job;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * @author TrungNguyen
 * @created 4/12/2026
 * @description
 */
public interface JobService extends Remote {
    Map<Job, Long> countPerJobByCompany(String companyName)  throws RemoteException;
}
