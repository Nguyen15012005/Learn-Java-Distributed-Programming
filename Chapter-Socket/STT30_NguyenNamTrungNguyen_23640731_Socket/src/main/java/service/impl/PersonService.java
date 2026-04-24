package service.impl;

import dto.PersonDTO;
import entity.Person;
import lombok.AllArgsConstructor;
import mapper.DataMapper;
import repository.IPersonRepository;
import repository.impl.PersonRepository;
import service.IPersonService;

import java.util.List;

@AllArgsConstructor
public class PersonService implements IPersonService {
    private final IPersonRepository personRepository = new PersonRepository();

    @Override
    public List<PersonDTO> listObesePeople() {
        List<Person> people = personRepository.listObesePeople();
        return DataMapper.mapList(people, PersonDTO.class);
    }
}
