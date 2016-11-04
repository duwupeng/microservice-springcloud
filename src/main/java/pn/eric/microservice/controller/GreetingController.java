package pn.eric.microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pn.eric.microservice.model.Customer;
import pn.eric.microservice.model.Greeting;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eric on 16/7/5.
 */
@RestController
public class GreetingController {

    private static BigInteger nextId;
    private static Map<BigInteger,Greeting> greetingMap;

    static {
        Greeting gt1 = new Greeting();
        gt1.setText("hlw1");
        save(gt1);


        Greeting gt2 = new Greeting();
        gt2.setText("hlw2");
        save(gt2);
    }

    private static Greeting save(Greeting greeting){
        if(greetingMap == null){
            greetingMap = new HashMap<BigInteger, Greeting>();
            nextId = BigInteger.ONE;
        }
        if(greeting.getId()!=null){
            Greeting oldGreeing = greetingMap.get(greeting.getId());
            if(oldGreeing == null){
                return null;
            }
            greetingMap.remove(greeting.getId());
            greetingMap.put(greeting.getId(),greeting);
            return greeting;
        }
        greeting.setId(nextId);
        nextId = nextId.add(BigInteger.ONE);
        greetingMap.put(greeting.getId(),greeting);
        return  greeting;
    }

    private static boolean delete(BigInteger id){
        Greeting deleteGreeting = greetingMap.remove(id);
        if(deleteGreeting == null){
            return false;
        }
        return true;
    }
    @RequestMapping(
            value = "/api/greetings",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Greeting>> getGreetings(){
        Collection<Greeting> greetings = greetingMap.values();
        return new ResponseEntity<Collection<Greeting>>(greetings,
                HttpStatus.OK);
    }

    @RequestMapping(value="/api/greetings/{id}",
                    method= RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> getGreeting(@PathVariable("id") BigInteger id) {
        Greeting greeting = greetingMap.get(id);
        if(greeting == null){
            return  new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
    }


    @RequestMapping(value="/api/greetings",
            method= RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) {
        Greeting saveGreting  = save(greeting);
        return  new ResponseEntity<Greeting>(saveGreting, HttpStatus.OK);
    }



    @RequestMapping(value="/api/greetings/{id}",
            method= RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting greeting) {
          Greeting updateGreeting = save(greeting);
        if(updateGreeting ==null){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity<Greeting>(updateGreeting, HttpStatus.OK);
    }

    @RequestMapping(value="/api/greetings/{id}",
            method= RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> deleteGreeting(@PathVariable BigInteger id,@RequestBody Greeting greeting) {
        boolean deleted = delete(id);
        if(!deleted){
            return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
    }
}
