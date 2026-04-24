package service.impl;

import dto.RecordDTO;
import entity.DoseStatus;
import entity.Person;
import entity.Vaccine;
import entity.Record;
import lombok.AllArgsConstructor;
import mapper.DataMapper;
import repository.IPersonRepository;
import repository.IRecordRepository;
import repository.IVaccineRepository;
import repository.impl.PersonRepository;
import repository.impl.RecordRepository;
import repository.impl.VaccineRepository;
import service.IRecordService;

import java.time.LocalDate;

@AllArgsConstructor
public class RecordService implements IRecordService {
    private final IPersonRepository personRepository = new PersonRepository();
    private final IVaccineRepository vaccineRepository = new VaccineRepository();
    private final IRecordRepository recordRepository = new RecordRepository();

    @Override
    public boolean createNewRecord(RecordDTO recordDTO) {
        // Số mũi tiêm phải là số nguyên dương > 0
        if (recordDTO.getDoseNumber() <= 0) {
            return false;
        }

        // Ngày tiêm phải sau hoặc là ngày hiện tại
        if (recordDTO.getInjectionDate().isBefore(LocalDate.now())) {
            return false;
        }

        // Khởi tạo trạng thái mũi tiêm là SCHEDULED
        recordDTO.setStatus(DoseStatus.SCHEDULED);

        Person person = personRepository.findById(recordDTO.getPersonId());
        if (person == null) {
            return false;
        }

        Vaccine vaccine = vaccineRepository.findById(recordDTO.getVaccineId());
        if (vaccine == null) {
            return false;
        }

        Record record = DataMapper.map(recordDTO, Record.class);
        record.setVaccine(vaccine);
        record.setPerson(person);
        return recordRepository.createNewRecord(record);
    }

    @Override
    public boolean updateScheduledRecord(Long recordId, DoseStatus doseStatus) {
        // Tìm một lượt tiêm theo mã số
        Record record = recordRepository.findById(recordId);
        if (record == null) {
            return false;
        }

        // Kiểm tra trạng thái của một lượt tiêm đã lên lịch SCHEDULED
        if (record.getStatus() != DoseStatus.SCHEDULED) {
            return false;
        }

        return recordRepository.updateScheduledRecord(recordId, doseStatus);
    }

}
