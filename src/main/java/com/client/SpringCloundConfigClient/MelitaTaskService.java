package com.client.SpringCloundConfigClient;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    @Autowired
    private RabbitSender rabbitSender;

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

    public void attachProduct(final String customerId, final String customerPackageId, final String address) throws JsonProcessingException {
        final Package customerPackage = new Package(customerPackageId, address, timeSlotConfig.getDaysInFuture());
        getCustomer(customerId).attachPackage(customerPackage);
        rabbitSender.sendMessage(new NewOrderEvent(customerId, customerPackage));
    }

}

