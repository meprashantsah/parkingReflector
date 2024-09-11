package com.parking.parkingReflector.config;

import com.twilio.Twilio;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.phoneNumber}")
    private String phoneNumber;

    @PostConstruct
    public void initTwilio() {
        Twilio.init(accountSid, authToken);  // Initialize Twilio with account SID and auth token
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
