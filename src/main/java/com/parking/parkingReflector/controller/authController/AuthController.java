package com.parking.parkingReflector.controller.authController;

import com.parking.parkingReflector.service.authService.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/otp")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@RequestParam String countryCode, @RequestParam String phoneNumber) {
        String response = authService.sendOtp(countryCode, phoneNumber);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestParam String countryCode, @RequestParam String phoneNumber, @RequestParam String otp) {
        boolean isVerified = authService.verifyOtp(countryCode, phoneNumber, otp);
        if (isVerified) {
            return ResponseEntity.ok("OTP verified successfully.");
        } else {
            return ResponseEntity.status(400).body("Invalid OTP. Please try again.");
        }
    }

}
