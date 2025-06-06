package com.example.POS.System.servicesImp;

import com.example.POS.System.DTO.RoleDTO;
import com.example.POS.System.exceptions.Custom.NotFoundException;
import com.example.POS.System.utils.Utils;
import com.example.POS.System.models.Role;
import com.example.POS.System.repositories.GlobalRepository;
import com.example.POS.System.services.RoleServices;
import com.example.POS.System.utils.GlobalResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServicesImp implements RoleServices {

    @Autowired
    private GlobalRepository db;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<GlobalResponse<List<RoleDTO>>> getList() {
        List<Role> roles = db.roles.findAll();
        List<RoleDTO> roleDTOs = modelMapper.map(roles, new TypeToken<List<RoleDTO>>() {
        }.getType());

        return Utils.Success("Successfully", roleDTOs);
    }

    @Override
    public ResponseEntity<GlobalResponse<RoleDTO>> getRole(Integer id) {
        Role role = db.roles.findById(id).orElseThrow(() -> new NotFoundException("Role Not Found!"));

        RoleDTO roleDTOs = modelMapper.map(role, RoleDTO.class);

        return Utils.Success("Successfully", roleDTOs);
    }

    @Override
    public ResponseEntity<GlobalResponse<RoleDTO>> addRole(RoleDTO roleDTO) {

        Role role = modelMapper.map(roleDTO, Role.class);

        Role result = db.roles.save(role);

        roleDTO.setId(result.getId());

        return Utils.CreateSuccess("Created Successfully", roleDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<RoleDTO>> removeRole(Integer id) {
        Role role = db.roles.findById(id).orElseThrow(() -> new NotFoundException("Role Not Found!"));

        db.roles.deleteById(role.getId());

        RoleDTO roleDTO = modelMapper.map(role, RoleDTO.class);

        return Utils.Success("Deleted successfully", roleDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<RoleDTO>> updateRole(Integer id, RoleDTO roleDTO) {
        Role role = db.roles.findById(id).orElseThrow(() -> new NotFoundException("Role Not Found!"));

        BeanUtils.copyProperties(roleDTO, role, Utils.getNullPropertyNames(roleDTO));

        Role updatedRole = db.roles.save(role);

        roleDTO = modelMapper.map(updatedRole, RoleDTO.class);

        return Utils.Success("Updated Successfully", roleDTO);
    }
}
