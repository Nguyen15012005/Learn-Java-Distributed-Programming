package dao;

import common.AbstractGenericDAO;
import model.Application;

/**
 * @author TrungNguyen
 * @created 4/12/2026
 * @description
 */
public class ApplicationDao extends AbstractGenericDAO<Application, Long> {
    public ApplicationDao() {
        super(Application.class);
    }
}
