package com.client.SpringCloundConfigClient;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Package implements Serializable {

    private static final long serialVersionUID = -2293083728597696066L;

    enum Telephony {
        BASIC_TELEPHONY,
        UNLIMITED_TELEPHONY
    }

    enum Internet {
        HALF_GIGABIT_INTERNET,
        GIGABIT_INTERNET
    }

    enum Mobile {
        PREPAID,
        POSTPAID
    }

    enum Television {
        STARTER,
        LHD,
        XLHD
    }

    @Setter(AccessLevel.NONE)
    private Telephony telephonyPackage;
    @Setter(AccessLevel.NONE)
    private Internet internetPackage;
    @Setter(AccessLevel.NONE)
    private Mobile mobilePackage;
    @Setter(AccessLevel.NONE)
    private Television televisionPackage;
    @Setter(AccessLevel.NONE)
    private InstallationDetails installationDetails;

    public Package(final String packageName, final String address, final Long daysInFuture){
        this.installationDetails = new InstallationDetails(LocalDateTime.now().plusDays(daysInFuture).toString(), address);
        if(packageName.equals("package1")) {
            this.telephonyPackage = Telephony.UNLIMITED_TELEPHONY;
            this.internetPackage = Internet.GIGABIT_INTERNET;
            this.mobilePackage = Mobile.POSTPAID;
            this.televisionPackage = Television.XLHD;
        }

        if(packageName.equals("package2")) {
            this.telephonyPackage = Telephony.BASIC_TELEPHONY;
            this.internetPackage = Internet.HALF_GIGABIT_INTERNET;
            this.mobilePackage = Mobile.PREPAID;
            this.televisionPackage = Television.STARTER;
        }

        if(packageName.equals("package3")) {
            this.telephonyPackage = Telephony.BASIC_TELEPHONY;
            this.internetPackage = Internet.HALF_GIGABIT_INTERNET;
            this.televisionPackage = Television.STARTER;
        }
    }

}
