package dao;

import common.AbstractGenericDAO;
import model.Company;

/**
 * @author TrungNguyen
 * @created 4/12/2026
 * @description
 */
public class CompanyDao extends AbstractGenericDAO<Company, Long> {
    public CompanyDao() {
        super(Company.class);
    }
}
