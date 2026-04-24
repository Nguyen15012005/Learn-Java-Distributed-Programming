package repository.impl;

import entity.Person;
import repository.IPersonRepository;

import java.util.List;

public class PersonRepository implements IPersonRepository {
    private final RepositoryTemplate template;

    public PersonRepository() {
        this.template = new RepositoryTemplate();
    }

    @Override
    public List<Person> listObesePeople() {
        String jpql = """
                    SELECT p FROM Person p
                    WHERE (p.weight / (p.height * p.height)) >= 25
            """;
        return template.execute(em -> em.createQuery(jpql, Person.class).getResultList());
    }

    @Override
    public Person findById(String personId) {
        return template.execute(em -> em.find(Person.class, personId));
    }
}
