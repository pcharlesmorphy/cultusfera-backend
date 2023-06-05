package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.frontend.FrontendUserDTO;
import com.example.biblioteca.model.entity.User;

import java.util.List;

public interface IFrontendUserMapper {
    User dtoToEntity(FrontendUserDTO userDTO);
    FrontendUserDTO entityToDto(User user);
    List<FrontendUserDTO> entityToDtoList(List<User> users);
    List<User> dtoListToEntity(List<FrontendUserDTO> usersDTO);
}
