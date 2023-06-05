package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.DirectorDTO;
import com.example.biblioteca.model.dto.UserRoleDTO;
import com.example.biblioteca.model.entity.Director;
import com.example.biblioteca.model.entity.UserRole;

import java.util.List;

public interface IUserRoleMapper {
    UserRole dtoToEntity (UserRoleDTO roleDTO);
    UserRoleDTO entityToDto (UserRole role);
    List<UserRoleDTO> entityToDtoList (List<UserRole> roles);
}
