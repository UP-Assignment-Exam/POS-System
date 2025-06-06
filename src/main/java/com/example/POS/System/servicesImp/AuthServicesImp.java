package com.example.POS.System.servicesImp;

import com.example.POS.System.DTO.ResetPasswordDTO;
import com.example.POS.System.DTO.UserDTO;
import com.example.POS.System.exceptions.Custom.BadRequestException;
import com.example.POS.System.exceptions.Custom.NotFoundException;
import com.example.POS.System.utils.EmailService;
import com.example.POS.System.utils.Utils;
import com.example.POS.System.models.Role;
import com.example.POS.System.models.User;
import com.example.POS.System.models.VerifySecretCode;
import com.example.POS.System.repositories.GlobalRepository;
import com.example.POS.System.DTO.LoginRequest;
import com.example.POS.System.DTO.LoginResponse;
import com.example.POS.System.security.JWTGenerator;
import com.example.POS.System.services.AuthServices;
import com.example.POS.System.utils.GlobalResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServicesImp implements AuthServices {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JWTGenerator jwtGenerator;

    @Autowired
    private GlobalRepository db;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<GlobalResponse<LoginResponse>> Login(LoginRequest loginRequest) {
        try {
            User user = db.users.findOneByEmail(loginRequest.getEmail()).orElseThrow(() -> new BadRequestException("Email not Found!"));

            if (!Utils.ComparePassword(user.getPassword(), loginRequest.getPassword())) {
                return Utils.BadRequest("Incorrect Password");
            }

            UserDTO userDTO = modelMapper.map(user, UserDTO.class);

            LoginResponse response = new LoginResponse();
            response.setRoleName(user.getRole().getName());
            response.setUser(userDTO);
            response.setAccessToken(jwtGenerator.generateToken(user));

            return Utils.Success("Login Successfully", response);
        } catch (AuthenticationException ex) {
            return Utils.BadRequest("Incorrect Email or Password");
        }
    }

    @Override
    public ResponseEntity<GlobalResponse<UserDTO>> Register(UserDTO registerRequest) {
        Role role = db.roles.findById(registerRequest.getRoleId()).orElseThrow(() -> new NotFoundException("Role not found"));

        if (db.users.existsByEmail(registerRequest.getEmail())) {
            return Utils.BadRequest("Email Already Exists");
        }

        User user = modelMapper.map(registerRequest, User.class);
        user.setRole(role);
        user.setPassword(Utils.encryptPassword(registerRequest.getPassword()));

        User savedUser = db.users.save(user);

        registerRequest.setId(savedUser.getId());

        return Utils.Success("Created Successfully", registerRequest);
    }

    @Override
    public ResponseEntity<GlobalResponse<String>> ForgotPassword(UserDTO request) {
        User user = db.users.findOneByEmail(request.getEmail()).orElseThrow(() -> new NotFoundException("Email not found"));

        VerifySecretCode verifySecretCode = modelMapper.map(user, VerifySecretCode.class);

        verifySecretCode.setId(null);
        verifySecretCode.setCode(Utils.generateSecretCode());
        Optional<VerifySecretCode> oneByEmailAndStatus = db.verifySecretCodes.findOneByEmailAndStatus(request.getEmail(), true);

        if (oneByEmailAndStatus.isPresent()) {
            oneByEmailAndStatus.get().setStatus(false);
            oneByEmailAndStatus.get().setIsResent(true);
            db.verifySecretCodes.save(oneByEmailAndStatus.get());
        }

        db.verifySecretCodes.save(verifySecretCode);
        //TODO Send Email
        emailService.sendOtpMessage(request.getEmail(), "Your OTP Code", verifySecretCode.getCode());


        return Utils.Success("Email sent Successfully", null);
    }

    @Override
    public ResponseEntity<GlobalResponse<String>> ChangePassword(ResetPasswordDTO request) {
        VerifySecretCode data = db.verifySecretCodes.findOneByEmailAndCodeAndStatus(request.getEmail(), request.getSecretCode(), true).orElseThrow(() -> new BadRequestException("Emails Not Found"));

        User user = db.users.findOneByEmail(request.getEmail()).orElseThrow(() -> new BadRequestException("Email not found"));

        if (request.getPassword() == null) {
            return Utils.Success("Correct Secret Key Successfully!", null);
        }

        if (Utils.ComparePassword(user.getPassword(), request.getPassword())) {
            throw new BadRequestException("New Password must not be the same as old password");
        }
        user.setPassword(Utils.encryptPassword(request.getPassword()));

        db.users.save(user);
        data.setStatus(false);
        db.verifySecretCodes.save(data);

        return Utils.Success("Reset Password Successfully!", null);
    }

}
