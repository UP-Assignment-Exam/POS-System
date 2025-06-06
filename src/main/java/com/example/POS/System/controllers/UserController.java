package com.example.POS.System.controllers;

import com.example.POS.System.DTO.UserDTO;
import com.example.POS.System.services.UserServices;
import com.example.POS.System.utils.GlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServices userServices;

    @GetMapping()
    public ResponseEntity<GlobalResponse<List<UserDTO>>> getAllUsers() {
        return userServices.getList();
    }

    @PostMapping()
    public ResponseEntity<GlobalResponse<UserDTO>> createUser(@RequestBody UserDTO user) {
        return userServices.addUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse<UserDTO>> updateUser(@PathVariable Integer id, @RequestBody UserDTO user) {
        return userServices.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<UserDTO>> deleteUser(@PathVariable Integer id) {
        return userServices.removeUser(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<UserDTO>> getUser(@PathVariable Integer id) {
        return userServices.getUser(id);
    }
}
