package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.RoleDTO;
import com.example.biblioteca.model.entity.Role;

import java.util.List;

public interface IRoleMapper {
    Role dtoToEntity (RoleDTO roleDTO);
    RoleDTO entityToDto (Role role);
    List<RoleDTO> entityToDtoList (List<Role> role);
}
