package service.implement;

import dao.CompanyDao;
import model.Company;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * @author TrungNguyen
 * @created 4/12/2026
 * @description
 */
public class CompanyServiceImpl extends UnicastRemoteObject implements service.CompanyService {

    private final CompanyDao companyDao;

    public CompanyServiceImpl() throws RemoteException{
        companyDao = new CompanyDao();
    }

    @Override
    public List<Company> loadAll() throws RemoteException {
        return companyDao.loadAll();
    }

}