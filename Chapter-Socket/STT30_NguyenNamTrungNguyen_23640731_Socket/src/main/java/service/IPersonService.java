package service;

import dto.PersonDTO;
import entity.Person;

import java.util.List;

public interface IPersonService {
    List<PersonDTO> listObesePeople();
}
