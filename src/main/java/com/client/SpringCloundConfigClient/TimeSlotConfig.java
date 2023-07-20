package com.client.SpringCloundConfigClient;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class TimeSlotConfig {

    @NotNull
    @Value("${timeSlot.daysInFuture}")
    private Long daysInFuture;
}
