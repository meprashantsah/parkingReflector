package com.parking.parkingReflector.service.authService;


import com.parking.parkingReflector.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {

    private final TwilioConfig twilioConfig;
    private final Map<String, String> otpStorage = new HashMap<>();

    @Autowired
    public AuthServiceImpl(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    @Override
    public String sendOtp(String countryCode, String phoneNumber) {
        String otp = generateOtp();
        String fullPhoneNumber = countryCode + phoneNumber;
        otpStorage.put(fullPhoneNumber, otp);

        Message.creator(
                new PhoneNumber(fullPhoneNumber),
                new PhoneNumber(twilioConfig.getPhoneNumber()),
                "Your OTP is: " + otp
        ).create();

        return "OTP sent successfully to " + fullPhoneNumber;
    }

    @Override
    public boolean verifyOtp(String countryCode, String phoneNumber, String otp) {
        String fullPhoneNumber = countryCode + phoneNumber;
        String storedOtp = otpStorage.get(fullPhoneNumber);
        if (storedOtp != null && storedOtp.equals(otp)) {
            otpStorage.remove(fullPhoneNumber);  // OTP verified, remove from storage
            return true;
        }
        return false;
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);  // Generate a 6-digit OTP
        return String.valueOf(otp);
    }
}
