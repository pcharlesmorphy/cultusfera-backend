package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.DirectorDTO;
import com.example.biblioteca.model.dto.UserRoleDTO;
import com.example.biblioteca.model.entity.Director;
import com.example.biblioteca.model.entity.UserRole;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRoleMapperImpl implements IUserRoleMapper{

    @Override
    public UserRole dtoToEntity(UserRoleDTO roleDTO) {
        UserRole role = new UserRole();
        role.setId(roleDTO.getId());
        role.setType(roleDTO.getType());
        return role;
    }

    @Override
    public UserRoleDTO entityToDto(UserRole role) {
        UserRoleDTO roleDTO = new UserRoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setType(role.getType());
        return roleDTO;
    }

    @Override
    public List<UserRoleDTO> entityToDtoList(List<UserRole> roles) {
        List<UserRoleDTO> rolesDTO = new ArrayList<UserRoleDTO>();
        UserRoleDTO roleDTO = new UserRoleDTO();
        for (UserRole ur:roles){
            roleDTO = entityToDto(ur);
            rolesDTO.add(roleDTO);
        }
        return rolesDTO;
    }
}
