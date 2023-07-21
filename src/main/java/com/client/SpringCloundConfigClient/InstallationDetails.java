package com.client.SpringCloundConfigClient;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class InstallationDetails implements Serializable {
    private static final long serialVersionUID = -2293083728597696069L;
    private String installationTimeslot;
    private String installationAddress;
}
