package pn.eric.microservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pn.eric.microservice.model.Person;
import pn.eric.microservice.model.Person;
import pn.eric.microservice.service.AsyncService;
import pn.eric.microservice.service.PersonService;
import pn.eric.microservice.service.PersonService;

import java.util.Collection;
import java.util.concurrent.Future;

/**
 * Created by eric on 16/7/5.
 */
@RestController
@RequestMapping(value="/api")
public class PersonController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonService personService;
    @Autowired
    AsyncService asyncService;

    @RequestMapping(
            value = "/persons",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Person>> getPersons(){
        Collection<Person> persons = personService.findAll();
        return new ResponseEntity<Collection<Person>>(persons,
                HttpStatus.OK);
    }

    @RequestMapping(value="/persons/{id}",
            method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPerson(@PathVariable("id") Long id) {
        Person greeting = personService.findOne(id);
        if(greeting == null){
            return  new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<Person>(greeting, HttpStatus.OK);
    }


    @RequestMapping(value="/persons",
            method= RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> createPerson(@RequestBody Person customer) {
        Person saveGreting  = personService.create(customer);
        return  new ResponseEntity<Person>(saveGreting, HttpStatus.OK);
    }



    @RequestMapping(value="/persons/{id}",
            method= RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> updatePerson(@RequestBody Person customer) {
        Person updatePerson = personService.update(customer);
        if(updatePerson ==null){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity<Person>(updatePerson, HttpStatus.OK);
    }

    @RequestMapping(value="/persons/{id}",
            method= RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> deletePerson(@PathVariable Long id,@RequestBody Person greeting) {
        personService.delete(id);
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }



    @RequestMapping(value="/persons/{id}/async",
            method= RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> asyncOperation(@PathVariable("id") Long id, @RequestParam(value = "wait",defaultValue = "false") boolean waitforAsyncResult) {

        logger.info(">person async");
        Person person = null;
        try{
            person = personService.findOne(id);
            if(person == null){
                logger.info("<person async");
                return  new ResponseEntity(HttpStatus.NO_CONTENT);
            }

            if(waitforAsyncResult){
                Future<Boolean> asyncResponse = asyncService.sendAsyncWithResult(person);
                boolean operted = asyncResponse.get();
                logger.info("- async operation done? {} ", operted);
            }else{
                asyncService.sendAsync(person);
            }
        }catch (Exception e){
            logger.error("A problem occurred the person opera.", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("<person async");
        return  new ResponseEntity<Person>(person, HttpStatus.OK);
    }
}
