package com.parking.parkingReflector.service.authService;

public interface AuthService {
    String sendOtp(String countryCode, String phoneNumber);
    boolean verifyOtp(String countryCode, String phoneNumber, String otp);
}
