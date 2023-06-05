package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.AuthorDTO;
import com.example.biblioteca.model.entity.Author;
import com.example.biblioteca.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorMapperImpl implements IAuthorMapper {
    @Autowired
    private RoleMapperImpl roleMapper;

    @Override
    public Author dtoToEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        author.setSurnames(authorDTO.getSurnames());
        //author.setRole(roleMapper.dtoToEntity(authorDTO.getRole()));
        return author;

    }

    @Override
    public AuthorDTO entityToDto(Author author) {

        AuthorDTO authordto = new AuthorDTO();
        authordto.setId(author.getId());
        authordto.setName(author.getName());
        authordto.setSurnames(author.getSurnames());
        //authordto.setRole(roleMapper.entityToDto(author.getRole()));
        return authordto;
    }

    @Override
    public List<AuthorDTO> entityToDtoList(List<Author> authors) {

        List<AuthorDTO> authorsdto = new ArrayList<AuthorDTO>();
        AuthorDTO autordto = new AuthorDTO();

        for (Author a:authors){
            autordto = entityToDto(a);
            authorsdto.add(autordto);
        }
        return authorsdto;
    }

    @Override
    public List<Author> dtoListToEntity(List<AuthorDTO> authorsDTO) {

        List<Author> authors = new ArrayList<Author>();
        Author author = new Author();
        for (AuthorDTO a:authorsDTO){
            author = dtoToEntity(a);
            authors.add(author);
        }
        return authors;
    }
}
