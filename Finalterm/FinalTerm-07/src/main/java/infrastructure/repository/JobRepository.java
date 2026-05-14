package infrastructure.repository;

import core.entity.Job;

import java.util.Map;

public interface JobRepository {
    Map<Job, Long> countPerJobByCompany(String companyName);
}
