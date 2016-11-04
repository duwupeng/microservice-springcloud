package pn.eric.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pn.eric.microservice.model.Person;

/**
 * @author Eric on 2016/7/11 16:04
 */
@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
}
