package repository;

import entity.Vaccine;

import java.util.List;

public interface IVaccineRepository {
    List<Object[]> countRecordsByVaccines();
    Vaccine findById(String vaccineId);
}
