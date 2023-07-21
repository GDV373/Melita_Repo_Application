package com.client.SpringCloundConfigClient;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class TimeSlotConfig {

    @Value("${timeSlot.daysInFuture:2}")
    private Long daysInFuture;
}
