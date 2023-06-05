package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.RoleDTO;
import com.example.biblioteca.model.entity.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleMapperImpl implements IRoleMapper{
    @Override
    public Role dtoToEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setType(roleDTO.getType());
        return role;
    }

    @Override
    public RoleDTO entityToDto(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setType(role.getType());
        return roleDTO;
    }

    @Override
    public List<RoleDTO> entityToDtoList(List<Role> role) {
        return null;
    }
}
