package com.client.SpringCloundConfigClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/test-controller")
public class ApplicationController {
    @Autowired
    private MelitaTaskService melitaTaskService;

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable(name = "customerId") final String customerId){
       return melitaTaskService.getCustomer(customerId);
    }

    @PostMapping
    public Integer addCustomer(@RequestBody final Customer customer){
       return melitaTaskService.addCustomer(customer);
    }

    @PostMapping("/{customerId}/{packageName}")
    public void attachProduct(@PathVariable(name = "customerId") final String customerId, @PathVariable(name = "packageName") final String customerPackage, @RequestBody final String address) throws JsonProcessingException {
         melitaTaskService.attachProduct(customerId, customerPackage, address);
    }


}
