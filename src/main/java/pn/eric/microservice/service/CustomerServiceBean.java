package pn.eric.microservice.service;

import org.springframework.stereotype.Service;
import pn.eric.microservice.model.Customer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Eric on 2016-07-11
 */
@Service
public class CustomerServiceBean implements CustomerService {

    private static Long nextId;
    private static Map<Long,Customer> customerMap;

    static {
        Customer gt1 = new Customer(1,"eric1");
        save(gt1);


        Customer gt2 = new Customer(2,"eric2");
        save(gt2);
    }

    private static Customer save(Customer customer){
        if(customerMap == null){
            customerMap = new HashMap<Long, Customer>();
            nextId = new Long(1);
        }
        if(customer.getId()!=null){
            Customer oldCustomer= customerMap.get(customer.getId());
            if(oldCustomer == null){
                return null;
            }
            customerMap.remove(customer.getId());
            customerMap.put(customer.getId(),customer);
            return customer;
        }
        customer.setId(nextId);
        nextId+=1;
        customerMap.put(customer.getId(),customer);
        return  customer;
    }

    private static boolean remove(Long id){
        Customer deleteCustomer = customerMap.remove(id);
        if(deleteCustomer == null){
            return false;
        }
        return true;
    }


    @Override
    public Collection<Customer> findAll() {
        return customerMap.values();
    }

    @Override
    public Customer findOne(Long id) {
        return customerMap.get(id);
    }

    @Override
    public Customer create(Customer customer) {
        return save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return save(customer);
    }

    @Override
    public void delete(Long id) {
        remove(id);
    }
}
