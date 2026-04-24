package repository;

import entity.DoseStatus;
import entity.Record;

public interface IRecordRepository {
    boolean createNewRecord(Record record);

    Record findById(Long recordId);

    boolean updateScheduledRecord(Long recordId, DoseStatus doseStatus);
}
