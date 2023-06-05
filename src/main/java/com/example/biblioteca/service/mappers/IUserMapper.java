package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.DirectorDTO;
import com.example.biblioteca.model.dto.UserDTO;
import com.example.biblioteca.model.entity.Director;
import com.example.biblioteca.model.entity.User;

import java.util.List;

public interface IUserMapper {
      User dtoToEntity(UserDTO userDTO);
      UserDTO entityToDto(User user);
      List<UserDTO> entityToDtoList(List<User> users);
      List<User> dtoListToEntity(List<UserDTO> usersDTO);

}
