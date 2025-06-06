package com.example.POS.System.services;

import com.example.POS.System.DTO.RoleDTO;
import com.example.POS.System.utils.GlobalResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleServices {
    public ResponseEntity<GlobalResponse<List<RoleDTO>>> getList();
    public ResponseEntity<GlobalResponse<RoleDTO>> getRole(Integer id);
    public ResponseEntity<GlobalResponse<RoleDTO>> addRole(RoleDTO role);
    public ResponseEntity<GlobalResponse<RoleDTO>> removeRole(Integer id);
    public ResponseEntity<GlobalResponse<RoleDTO>> updateRole(Integer id, RoleDTO role);
}
