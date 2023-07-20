package com.client.SpringCloundConfigClient;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class InstallationDetails {
    private LocalDateTime installationTimeslot;
    private String installationAddress;
}
