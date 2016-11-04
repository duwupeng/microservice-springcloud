package pn.eric.microservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pn.eric.microservice.model.Customer;
import pn.eric.microservice.model.Person;
import pn.eric.microservice.util.AsyncResponse;

import javax.xml.ws.Response;
import java.util.concurrent.Future;

/**
 * @author Eric on
 */
@Service
public class AsyncServiceBean implements AsyncService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Boolean send(Person person) {
        logger.info(">send");
        Boolean success = Boolean.FALSE;

        long pause = 5000;

        try{
            Thread.sleep(pause);
        }catch(Exception e){
            // nothing
        }
        logger.info("Processing time was {} seconds",pause/1000);
        success = Boolean.TRUE;
        logger.info("<send");
        return success;

    }

    @Async
    @Override
    public void sendAsync(Person person) {
        logger.info(">sendAsync");
        try{
            send(person);
        }catch(Exception e){
            logger.info("Exception caught sending asynchronous customer",e);
        }
        logger.info("<sendAsync");
    }
    @Async
    @Override
    public Future<Boolean> sendAsyncWithResult(Person person) {
        logger.info(">sendAsyncWithResult");
        AsyncResponse<Boolean> response = new AsyncResponse<Boolean>();
        try{
            Boolean success = send(person);
            response.complete(success);
        }catch (Exception e){
            logger.warn("Exception caught sending asynchronous ..",e);

        }
        logger.info("<sendAsyncWithResult");

        return response;
    }
}
