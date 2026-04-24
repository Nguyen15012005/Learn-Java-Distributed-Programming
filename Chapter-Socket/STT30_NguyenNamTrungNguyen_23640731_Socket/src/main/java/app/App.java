package app;

import dto.PersonDTO;
import dto.RecordDTO;
import dto.VaccineDTO;
import entity.DoseStatus;
import repository.IPersonRepository;
import repository.IRecordRepository;
import repository.IVaccineRepository;
import repository.impl.PersonRepository;
import repository.impl.RecordRepository;
import repository.impl.VaccineRepository;
import service.IPersonService;
import service.IRecordService;
import service.IVaccineService;
import service.impl.PersonService;
import service.impl.RecordService;
import service.impl.VaccineService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        IPersonRepository personRepository = new PersonRepository();
        IVaccineRepository vaccineRepository = new VaccineRepository();
        IRecordRepository recordRepository = new RecordRepository();

        IPersonService personService = new PersonService();
        IVaccineService vaccineService = new VaccineService();
        IRecordService recordService = new RecordService();

        RecordDTO recordDTO = RecordDTO.builder()
                .personId("P006")
                .vaccineId("V002")
                .injectionDate(LocalDate.now())
                .doseNumber(1)
                .status(DoseStatus.SCHEDULED)
                .build();

        boolean created = recordService.createNewRecord(recordDTO);
        System.out.println("3a: Create new Record");
        System.out.println("Record created: " + created);

        boolean updated = recordService.updateScheduledRecord(16l, DoseStatus.COMPLETED);
        System.out.println("3b: Update Record");
        System.out.println("Record updated: " + updated);

        System.out.println("3c: List of people with BMI >= 25");
        List<PersonDTO> obesePeople = personService.listObesePeople();
        obesePeople.forEach(System.out::println);

        System.out.println("3d: Statistics of completed records by vaccine");
        Map<VaccineDTO, Integer> result = vaccineService.countRecordsByVaccines();
        result.forEach((vaccineDTO, count) -> System.out.println(vaccineDTO + ": " + count));
    }
}