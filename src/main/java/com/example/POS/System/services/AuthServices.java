package com.example.POS.System.services;

import com.example.POS.System.DTO.ResetPasswordDTO;
import com.example.POS.System.DTO.UserDTO;
import com.example.POS.System.DTO.LoginRequest;
import com.example.POS.System.DTO.LoginResponse;
import com.example.POS.System.utils.GlobalResponse;
import org.springframework.http.ResponseEntity;

public interface AuthServices {
    public ResponseEntity<GlobalResponse<LoginResponse>> Login(LoginRequest loginRequest);
    public ResponseEntity<GlobalResponse<UserDTO>> Register(UserDTO request);
    public ResponseEntity<GlobalResponse<String>> ForgotPassword(UserDTO request);
    public ResponseEntity<GlobalResponse<String>> ChangePassword(ResetPasswordDTO request);
}
