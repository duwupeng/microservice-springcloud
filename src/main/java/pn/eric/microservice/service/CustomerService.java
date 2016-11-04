package pn.eric.microservice.service;

import pn.eric.microservice.model.Customer;
import pn.eric.microservice.model.Greeting;

import java.util.Collection;

/**
 * Created by eric on 16/7/5.
 */
public interface CustomerService {
    Collection<Customer> findAll();
    Customer findOne(Long id);
    Customer create(Customer greeting);
    Customer update(Customer greeting);
    void delete(Long id);
}
