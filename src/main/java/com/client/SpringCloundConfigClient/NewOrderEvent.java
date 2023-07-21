package com.client.SpringCloundConfigClient;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class NewOrderEvent implements Serializable {
    private static final long serialVersionUID = -2293083728597696063L;
    private String customerId;
    private Package aPackage;
}
