package pn.eric.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pn.eric.microservice.model.Customer;
import pn.eric.microservice.model.Customer;
import pn.eric.microservice.service.CustomerService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by eric on 16/7/5.
 */
@RestController
@RequestMapping(value="/api")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @RequestMapping(
            value = "/customers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Customer>> getCustomers(){
        Collection<Customer> customers = customerService.findAll();
        return new ResponseEntity<Collection<Customer>>(customers,
                HttpStatus.OK);
    }

    @RequestMapping(value="/customers/{id}",
            method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        Customer greeting = customerService.findOne(id);
        if(greeting == null){
            return  new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<Customer>(greeting, HttpStatus.OK);
    }


    @RequestMapping(value="/customers",
            method= RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer saveGreting  = customerService.create(customer);
        return  new ResponseEntity<Customer>(saveGreting, HttpStatus.OK);
    }



    @RequestMapping(value="/customers/{id}",
            method= RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        Customer updateCustomer = customerService.update(customer);
        if(updateCustomer ==null){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity<Customer>(updateCustomer, HttpStatus.OK);
    }

    @RequestMapping(value="/customers/{id}",
            method= RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id,@RequestBody Customer greeting) {
        customerService.delete(id);
        return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
    }



    @RequestMapping(value="/static", method= RequestMethod.GET)
    public String sayUser() {
        return "users static...";
    }
}
