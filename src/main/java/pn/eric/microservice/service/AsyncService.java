package pn.eric.microservice.service;

import pn.eric.microservice.model.Customer;
import pn.eric.microservice.model.Person;

import java.util.concurrent.Future;

/**
 * @author Eric on
 */
public interface AsyncService {

    Boolean send(Person person);
    void sendAsync(Person person);
    Future<Boolean>  sendAsyncWithResult(Person person);
}
