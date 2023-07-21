package com.client.SpringCloundConfigClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void clearDb(){
        melitaTaskService.clearDb();
    }


    @Test
    void test_createCustomer() {
        final Customer customer = new Customer(0, "name", "surname", 29, 77123456, "Some Street");
        melitaTaskService.addCustomer(customer);

        Assertions.assertThat(melitaTaskService.getCustomer("0"))
                .isNotNull()
                .isEqualTo(customer);

    }

    @Test
    void test_attachProduct() throws JsonProcessingException {
        final Customer customer = new Customer(0, "name", "surname", 29, 77123456, "Some Street");
        final Package aPackage = new Package("package1", "some address", 2L);
        melitaTaskService.addCustomer(customer);

        melitaTaskService.attachProduct("0", "package1", "some address");

        Assertions.assertThat(melitaTaskService.getCustomer("0").getCustomerPackage()).satisfies(customerPackage -> {
                    Assertions.assertThat(customerPackage.getMobilePackage()).isEqualTo(aPackage.getMobilePackage());
                    Assertions.assertThat(customerPackage.getInternetPackage()).isEqualTo(aPackage.getInternetPackage());
                    Assertions.assertThat(customerPackage.getTelevisionPackage()).isEqualTo(aPackage.getTelevisionPackage());
                    Assertions.assertThat(customerPackage.getTelephonyPackage()).isEqualTo(aPackage.getTelephonyPackage());
                    Assertions.assertThat(customerPackage.getInstallationDetails()).satisfies(installationDetails ->
                            Assertions.assertThat(installationDetails.getInstallationAddress())
                                    .isEqualTo(aPackage.getInstallationDetails().getInstallationAddress()));
                });

        Mockito.verify(rabbitSender, Mockito.times(1)).sendMessage(any());
    }

    @Test
    void test_getCustomerWIthInvalidId() {
        Assertions.assertThatThrownBy(() -> melitaTaskService.getCustomer("99")).isInstanceOf(IllegalStateException.class);
    }

}