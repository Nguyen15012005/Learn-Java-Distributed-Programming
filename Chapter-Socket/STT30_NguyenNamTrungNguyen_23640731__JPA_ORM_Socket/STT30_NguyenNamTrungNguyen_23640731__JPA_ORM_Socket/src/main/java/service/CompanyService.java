package service;

import model.Company;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author TrungNguyen
 * @created 4/12/2026
 * @description
 */
public interface CompanyService extends Remote {
    List<Company> loadAll() throws RemoteException;
}
