package com.example.biblioteca.service.mappers;


import com.example.biblioteca.model.dto.PenaltyDTO;
import com.example.biblioteca.model.entity.Penalty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PenaltyMapperImpl implements IPenaltyMapper{

    @Autowired
    PenaltyReasonMapperImpl penaltyReasonMapper;
    @Autowired
    UserMapperImpl userMapper;
    @Autowired
    LoanMapperImpl loanMapper;

    @Override
    public Penalty dtoToEntity(PenaltyDTO penaltyDTO) {
        Penalty penalty = new Penalty();
        penalty.setId(penaltyDTO.getId());
        penalty.setStartDate(penaltyDTO.getStartDate());
        penalty.setEndDate(penaltyDTO.getEndDate());
        penalty.setReason(penaltyReasonMapper.dtoToEntity(penaltyDTO.getReason()));
        penalty.setStatus(penaltyDTO.isStatus());
        penalty.setComments(penaltyDTO.getComments());
        penalty.setUser(userMapper.dtoToEntity(penaltyDTO.getUser()));
        penalty.setLoan(loanMapper.dtoToEntity(penaltyDTO.getLoan()));

        return penalty;
    }

    @Override
    public PenaltyDTO entityToDto(Penalty penalty) {
        PenaltyDTO penaltyDTO = new PenaltyDTO();
        penaltyDTO.setId(penalty.getId());
        penaltyDTO.setStartDate(penalty.getStartDate());
        penaltyDTO.setEndDate(penalty.getEndDate());
        penaltyDTO.setReason(penaltyReasonMapper.entityToDto(penalty.getReason()));
        penaltyDTO.setStatus(penalty.isStatus());
        penaltyDTO.setComments(penalty.getComments());
        penaltyDTO.setUser(userMapper.entityToDto(penalty.getUser()));
        penaltyDTO.setLoan(loanMapper.entityToDto(penalty.getLoan()));

        return penaltyDTO;
    }

    @Override
    public List<PenaltyDTO> entityToDtoList(List<Penalty> penalties) {
        List<PenaltyDTO> penaltiesDTO = new ArrayList<PenaltyDTO>();
        PenaltyDTO penaltyDTO = new PenaltyDTO();
        for (Penalty p:penalties){
            penaltyDTO = entityToDto(p);
            penaltiesDTO.add(penaltyDTO);
        }
        return penaltiesDTO;
    }

    @Override
    public List<Penalty> dtoListToEntity(List<PenaltyDTO> penaltiesDTO) {
        List<Penalty> penalties = new ArrayList<Penalty>();
        Penalty penalty = new Penalty();
        for (PenaltyDTO p:penaltiesDTO){
            penalty = dtoToEntity(p);
            penalties.add(penalty);
        }
        return penalties;
    }
}


