package repository.impl;

import entity.*;
import entity.Record;
import repository.IRecordRepository;

import java.time.LocalDate;

public class RecordRepository implements IRecordRepository {
    private final RepositoryTemplate template;

    public RecordRepository() {
        this.template = new RepositoryTemplate();
    }

    @Override
    public boolean createNewRecord(Record record) {
        return template.execute(em -> {
            em.persist(record);
            return true;
        });
    }

    @Override
    public Record findById(Long recordId) {
        return template.execute(em -> em.find(Record.class, recordId));
    }

    @Override
    public boolean updateScheduledRecord(Long recordId, DoseStatus doseStatus) {
        Record record = findById(recordId);
        if(record != null && record.getStatus() == DoseStatus.SCHEDULED) {
            record.setStatus(doseStatus);
            record.setInjectionDate(LocalDate.now());

            return template.execute(em -> {
                em.merge(record);
                return true;
            });
        }
        return false;
    }
}
