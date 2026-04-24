package dao;

import common.AbstractGenericDAO;
import model.Skill;

/**
 * @author TrungNguyen
 * @created 4/12/2026
 * @description
 */
public class SkillDao extends AbstractGenericDAO<Skill, String> {

    public SkillDao() {
        super(Skill.class);
    }
}
