package infrastructure.repository;

import java.util.List;

public interface CandidateRepository {
    List<Object[]> findBySkillInOpenJobs(String skill);
}
