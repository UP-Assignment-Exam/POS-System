package com.example.POS.System.controllers;


import com.example.POS.System.DTO.ResetPasswordDTO;
import com.example.POS.System.DTO.UserDTO;
import com.example.POS.System.DTO.LoginRequest;
import com.example.POS.System.DTO.LoginResponse;
import com.example.POS.System.services.AuthServices;
import com.example.POS.System.utils.GlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthServices authServices;

    @PostMapping("/login")
    public ResponseEntity<GlobalResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        return authServices.Login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<GlobalResponse<UserDTO>> register(@RequestBody UserDTO request) {
        return authServices.Register(request);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<GlobalResponse<String>> forgotPassword(@RequestBody UserDTO request) {
        return authServices.ForgotPassword(request);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<GlobalResponse<String>> changePassword(@RequestBody ResetPasswordDTO request) {
        return authServices.ChangePassword(request);
    }
}
