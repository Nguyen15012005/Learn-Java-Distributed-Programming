package repository;

import entity.Person;

import java.util.List;

public interface IPersonRepository {
    List<Person> listObesePeople();
    Person findById(String personId);
}
