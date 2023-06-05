package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.StatusCopyDTO;
import com.example.biblioteca.model.entity.StatusCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatusCopyMapperImpl implements IStatusCopyMapper{

    @Override
    public StatusCopy dtoToEntity(StatusCopyDTO statusDTO) {
        StatusCopy statusCopy = new StatusCopy();
        statusCopy.setId(statusDTO.getId());
        statusCopy.setStatus(statusDTO.getStatus());
        return statusCopy;
    }

    @Override
    public StatusCopyDTO entityToDto(StatusCopy status) {
        StatusCopyDTO statusCopyDTO = new StatusCopyDTO();
        statusCopyDTO.setId(status.getId());
        statusCopyDTO.setStatus(status.getStatus());
        return statusCopyDTO;
    }

    @Override
    public List<StatusCopyDTO> entityToDtoList(List<StatusCopy> status) {
        List<StatusCopyDTO> statusListDTO = new ArrayList<StatusCopyDTO>();
        StatusCopyDTO statusDTO = new StatusCopyDTO();
        for (StatusCopy s:status){
            statusDTO = entityToDto(s);
            statusListDTO.add(statusDTO);
        }
        return statusListDTO;
    }
}
