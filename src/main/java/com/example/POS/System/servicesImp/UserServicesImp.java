package com.example.POS.System.servicesImp;

import com.example.POS.System.DTO.UserDTO;
import com.example.POS.System.exceptions.Custom.NotFoundException;
import com.example.POS.System.utils.Utils;
import com.example.POS.System.models.Role;
import com.example.POS.System.models.User;
import com.example.POS.System.repositories.GlobalRepository;
import com.example.POS.System.services.UserServices;
import com.example.POS.System.utils.GlobalResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImp implements UserServices {

    @Autowired
    private GlobalRepository db;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<GlobalResponse<List<UserDTO>>> getList() {
        List<User> users = db.users.findAll();

        List<UserDTO> usersDTO = modelMapper.map(users, new TypeToken<List<UserDTO>>() {
        }.getType());

        return Utils.Success("Successfully", usersDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<UserDTO>> getUser(Integer id) {
        User user = db.users.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return Utils.Success("Successfully", userDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<UserDTO>> addUser(UserDTO userDTO) {
        db.roles.findById(userDTO.getRoleId()).orElseThrow(() -> new NotFoundException("Role not found"));

        User user = modelMapper.map(userDTO, User.class);

        if (userDTO.getPassword() != null) {
            user.setPassword(Utils.encryptPassword(userDTO.getPassword()));
        }

        User savedUser = db.users.save(user);

        userDTO.setId(savedUser.getId());

        return Utils.CreateSuccess("Created Successfully", userDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<UserDTO>> removeUser(Integer id) {
        User user = db.users.findById(id).orElseThrow(() -> new NotFoundException("User Not Found"));

        db.users.deleteById(user.getId());

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return Utils.Success("Deleted successfully", userDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<UserDTO>> updateUser(Integer id, UserDTO userDTO) {
        User existingUser = db.users.findById(id).orElseThrow(() -> new NotFoundException("User Not Found"));
        if (userDTO.getRoleId() != null) {
            Role role = db.roles.findById(userDTO.getRoleId()).orElseThrow(() -> new NotFoundException("Role Not Found"));
            existingUser.setRole(role);
        }

        BeanUtils.copyProperties(userDTO, existingUser, Utils.getNullPropertyNames(userDTO));

        if (userDTO.getPassword() != null) {
            existingUser.setPassword(Utils.encryptPassword(userDTO.getPassword()));
        }

        if (db.users.existsByEmail(userDTO.getEmail())) {
            return Utils.BadRequest("Email Already Exists");
        }

        User user = db.users.save(existingUser);

        userDTO = modelMapper.map(user, UserDTO.class);

        return Utils.Success("Updated Successfully", userDTO);
    }
}
