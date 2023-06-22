package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.UserDTO;
import com.example.biblioteca.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl implements IUserMapper{

    @Autowired
    UserRoleMapperImpl userRoleMapper;

    @Autowired
    TransactionMapperImpl transactionMapper;

    @Autowired
    PenaltyMapperImpl penaltyMapper;

    @Override
    public User dtoToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurnames(userDTO.getSurnames());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setNif(userDTO.getNif());
        user.setAddress(userDTO.getAddress());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        if (userDTO.getTransactions() != null) {
            user.setTransactions(transactionMapper.dtoListToEntity(userDTO.getTransactions()));
        }
        user.setRegistrationDate(userDTO.getRegistrationDate());
        user.setRole(userRoleMapper.dtoToEntity(userDTO.getRole()));

        user.setSuspended(userDTO.isSuspended());
        return user;
    }

    @Override
    public UserDTO entityToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurnames(user.getSurnames());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setNif(user.getNif());
        userDTO.setAddress(user.getAddress());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRegistrationDate(user.getRegistrationDate());
        if (user.getTransactions() != null) {
            userDTO.setTransactions(transactionMapper.entityToDtoList(user.getTransactions()));
        }

        userDTO.setSuspended(user.isSuspended());
        userDTO.setRole(userRoleMapper.entityToDto(user.getRole()));
        return userDTO;
    }

    @Override
    public List<UserDTO> entityToDtoList(List<User> users) {
        List<UserDTO> usersDTO = new ArrayList<UserDTO>();
        UserDTO userDTO = new UserDTO();
        for (User u:users){
            userDTO = entityToDto(u);
            usersDTO.add(userDTO);
        }
        return usersDTO;
    }

    @Override
    public List<User> dtoListToEntity(List<UserDTO> usersDTO) {
        List<User> users = new ArrayList<User>();
        User user = new User();
        for (UserDTO u:usersDTO){
            user = dtoToEntity(u);
            users.add(user);
        }
        return users;
    }
}
