package service;

import dto.RecordDTO;
import entity.DoseStatus;
import entity.Record;
import entity.Vaccine;

import java.util.Map;

public interface IRecordService {
    boolean createNewRecord(RecordDTO record);
    boolean updateScheduledRecord(Long recordId, DoseStatus doseStatus);
}
