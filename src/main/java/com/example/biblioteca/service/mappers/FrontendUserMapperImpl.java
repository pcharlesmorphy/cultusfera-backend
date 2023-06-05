package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.frontend.FrontendUserDTO;
import com.example.biblioteca.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FrontendUserMapperImpl implements IFrontendUserMapper{
    @Autowired
    UserRoleMapperImpl userRoleMapper;

    @Autowired
    TransactionMapperImpl transactionMapper;

    @Override
    public User dtoToEntity(FrontendUserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurnames(userDTO.getSurnames());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setNif(userDTO.getNif());
        user.setAddress(userDTO.getAddress());
        user.setUsername(userDTO.getUsername());
        user.setTransactions(transactionMapper.dtoListToEntity(userDTO.getTransactions()));
        user.setRegistrationDate(userDTO.getRegistrationDate());
        user.setRole(userRoleMapper.dtoToEntity(userDTO.getRole()));
        return user;
    }

    @Override
    public FrontendUserDTO entityToDto(User user) {
        FrontendUserDTO userDTO = new FrontendUserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurnames(user.getSurnames());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setNif(user.getNif());
        userDTO.setAddress(user.getAddress());
        userDTO.setUsername(user.getUsername());
        userDTO.setRegistrationDate(user.getRegistrationDate());
        userDTO.setTransactions(transactionMapper.entityToDtoList(user.getTransactions()));
        userDTO.setRole(userRoleMapper.entityToDto(user.getRole()));
        return userDTO;
    }

    @Override
    public List<FrontendUserDTO> entityToDtoList(List<User> users) {
        List<FrontendUserDTO> usersDTO = new ArrayList<FrontendUserDTO>();
        FrontendUserDTO userDTO = new FrontendUserDTO();
        for (User u:users){
            userDTO = entityToDto(u);
            usersDTO.add(userDTO);
        }
        return usersDTO;
    }

    @Override
    public List<User> dtoListToEntity(List<FrontendUserDTO> usersDTO) {
        List<User> users = new ArrayList<User>();
        User user = new User();
        for (FrontendUserDTO u:usersDTO){
            user = dtoToEntity(u);
            users.add(user);
        }
        return users;
    }
}
