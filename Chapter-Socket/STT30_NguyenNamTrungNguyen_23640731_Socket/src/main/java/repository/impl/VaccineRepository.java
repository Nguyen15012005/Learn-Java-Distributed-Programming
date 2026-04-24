package repository.impl;

import entity.Vaccine;
import repository.IVaccineRepository;

import java.util.List;

public class VaccineRepository implements IVaccineRepository {
    private final RepositoryTemplate template;

    public VaccineRepository() {
        this.template = new RepositoryTemplate();
    }
    @Override
    public List<Object[]> countRecordsByVaccines(){

        String query = """
                select r.vaccine, count(*) as n 
                from Record r 
                where r. status = 'COMPLETED'
                group by r.vaccine 
                order by r.vaccine.vaccineName
                """;

        return template.execute(em -> em.createQuery(query, Object[].class).getResultList());
    }

    @Override
    public Vaccine findById(String vaccineId) {
        return template.execute(em -> em.find(Vaccine.class, vaccineId));
    }

    public static void main(String[] args) {
        IVaccineRepository vaccineRepository = new VaccineRepository();
        List<Object[]> results = vaccineRepository.countRecordsByVaccines();

        for (Object[] result : results) {
            Vaccine vaccine = (Vaccine) result[0];
            Long count = (Long) result[1];
            System.out.println("Vaccine: " + vaccine + ", Count: " + count);
        }
    }
}
