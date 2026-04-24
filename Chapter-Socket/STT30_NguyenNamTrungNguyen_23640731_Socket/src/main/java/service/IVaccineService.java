package service;

import dto.VaccineDTO;
import entity.Vaccine;

import java.util.Map;

public interface IVaccineService {
    Map<VaccineDTO, Integer> countRecordsByVaccines();
}
