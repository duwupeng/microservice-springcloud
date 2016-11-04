package pn.eric.microservice.service;

import pn.eric.microservice.model.Person;

import java.util.Collection;

/**
 * Created by eric on 16/7/5.
 */
public interface PersonService {
    Collection<Person> findAll();
    Person findOne(Long id);
    Person create(Person greeting);
    Person update(Person greeting);
    void delete(Long id);
    void evictCache();
}
