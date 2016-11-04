package pn.eric.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pn.eric.microservice.model.Person;
import pn.eric.microservice.repository.PersonRepository;

import java.util.Collection;

/**
 * @author Eric on 2016-07-11
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class PersonServiceBean implements PersonService {

    @Autowired
    private PersonRepository personRepository;


    @Override
    public Collection<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    @Cacheable(value = "persons",
    key="#id")
    public Person findOne(Long id) {
        return personRepository.findOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    @CachePut(value = "persons",
            key="#result.id")
    public Person create(Person person) {
        if(person.getId()!=null){
            return null;
        }
        Person savedPerson = personRepository.save(person);

        //Illustrate tx rollback
        if(savedPerson.getId()==15l){
            throw new RuntimeException("Roll me");
        }
        return savedPerson;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    @CachePut(value = "persons",
            key="#person.id")
    public Person update(Person person) {
        Person personPersisted = findOne(person.getId());
        if(personPersisted==null){
            return null;
        }
        Person personUpdated = personRepository.save(person);
        return personUpdated;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    @CacheEvict(value = "persons",
            key="#id")
    public void delete(Long id) {
        personRepository.delete(id);
    }

    @Override
    @CacheEvict(value = "persons",
            allEntries = true)
    public void evictCache() {
    }
}


