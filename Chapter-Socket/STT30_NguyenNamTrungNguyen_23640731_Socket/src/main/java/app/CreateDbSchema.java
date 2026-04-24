package app;

import db.JPAUtil;
import jakarta.persistence.EntityManager;


public class CreateDbSchema {
    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

    }
}
