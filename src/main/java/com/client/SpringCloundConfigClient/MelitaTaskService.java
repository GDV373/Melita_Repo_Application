package com.client.SpringCloundConfigClient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class MelitaTaskService {

    final Map<Integer, Customer> customerDb = new HashMap<>();

    @Autowired
    private TimeSlotConfig timeSlotConfig;

    public Integer addCustomer(final Customer customer){
        final Customer newCustomer = new Customer(customerDb.values().size(),
                customer.getName(),
                customer.getSurname(),
                customer.getAge(),
                customer.getMobileNum(),
                customer.getAddress());
        customerDb.put(newCustomer.getCustomerId(), newCustomer);
        return newCustomer.getCustomerId();
    }

    public Customer getCustomer(final String customerId) {
        try{
            return customerDb.get(Integer.parseInt(customerId));
        } catch (final NullPointerException npe){
            log.error("I FAILED");
            throw npe;
        }
    }

    public void attachProduct(final String customerId, final String customerPackage, final String address){
        getCustomer(customerId).attachPackage(new Package(customerPackage, address, timeSlotConfig.getDaysInFuture()));
    }

}
