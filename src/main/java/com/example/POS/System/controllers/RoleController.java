package com.example.POS.System.controllers;

import com.example.POS.System.DTO.RoleDTO;
import com.example.POS.System.services.RoleServices;
import com.example.POS.System.utils.GlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleServices roleServices;

    @GetMapping()
    public ResponseEntity<GlobalResponse<List<RoleDTO>>> getAllRoles() {
        return roleServices.getList();
    }

    @PostMapping()
    public ResponseEntity<GlobalResponse<RoleDTO>> createRole(@RequestBody RoleDTO role) {
        return roleServices.addRole(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse<RoleDTO>> updateRole(@PathVariable Integer id, @RequestBody RoleDTO role) {
        return roleServices.updateRole(id, role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<RoleDTO>> deleteRole(@PathVariable Integer id) {
        return roleServices.removeRole(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<RoleDTO>> getRole(@PathVariable Integer id) {
        return roleServices.getRole(id);
    }
}
