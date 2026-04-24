package service.impl;

import dto.VaccineDTO;
import entity.Vaccine;
import lombok.AllArgsConstructor;
import mapper.DataMapper;
import repository.IVaccineRepository;
import repository.impl.VaccineRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@AllArgsConstructor
public class VaccineService implements service.IVaccineService {
    private final IVaccineRepository vaccineRepository = new VaccineRepository();

    //+ countRecordsByVaccines(): Map<Vaccine, Integer>
    @Override
    public Map<VaccineDTO, Integer> countRecordsByVaccines() {
        List<Object[]> list = vaccineRepository.countRecordsByVaccines();
        return list.stream()
                .map(arr -> {
                    Vaccine vaccine = (Vaccine) arr[0];
                    VaccineDTO vaccineDTO = DataMapper.map(vaccine, VaccineDTO.class);

                    Integer count = ((Number) arr[1]).intValue();
                    return Map.entry(vaccineDTO, count);
                }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

}
