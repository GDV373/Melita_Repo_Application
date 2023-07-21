package com.client.SpringCloundConfigClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@Import({MelitaTaskService.class, TimeSlotConfig.class})
class MelitaTaskServiceTest {

    @MockBean
    private RabbitSender rabbitSender;

    @Autowired
    private MelitaTaskService melitaTaskService;


    @Test
    void test_createCustomer() throws JsonProcessingException {
        final Customer customer = new Customer(123, "name", "surname", 29, 77123456, "Some Street");
        melitaTaskService.addCustomer(customer);

        Assertions.assertThat(melitaTaskService.getCustomer("123"))
                .isNotNull()
                .isEqualTo(customer);

    }

    @Test
    void test_attachProduct() throws JsonProcessingException {
        final Customer customer = new Customer(123, "name", "surname", 29, 77123456, "Some Street");
        melitaTaskService.addCustomer(customer);

        melitaTaskService.attachProduct("123", "package1", "some address");

        Assertions.assertThat(melitaTaskService.getCustomer("123").getCustomerPackage())
                .isEqualTo(new Package("package1", "some address", 2L));

        Mockito.verify(rabbitSender, Mockito.times(1)).sendMessage(any());
    }

}