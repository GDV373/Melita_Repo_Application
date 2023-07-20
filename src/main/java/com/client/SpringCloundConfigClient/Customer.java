package com.client.SpringCloundConfigClient;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
public class Customer {
    private final Integer customerId;

    private final String name;
    private final String surname;
    private final Integer age;

    private final Integer mobileNum;

    private final String address;

    @Setter(AccessLevel.NONE)
    private Package customerPackage;

    public void attachPackage(final Package customerPackage){
        this.customerPackage = customerPackage;
    }


}
