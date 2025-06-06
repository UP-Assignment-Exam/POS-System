package com.example.POS.System.services;

import com.example.POS.System.DTO.UserDTO;
import com.example.POS.System.utils.GlobalResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServices {
    public ResponseEntity<GlobalResponse<List<UserDTO>>> getList();
    public ResponseEntity<GlobalResponse<UserDTO>> getUser(Integer id);
    public ResponseEntity<GlobalResponse<UserDTO>> addUser(UserDTO user);
    public ResponseEntity<GlobalResponse<UserDTO>> removeUser(Integer id);
    public ResponseEntity<GlobalResponse<UserDTO>> updateUser(Integer id, UserDTO user);
}
