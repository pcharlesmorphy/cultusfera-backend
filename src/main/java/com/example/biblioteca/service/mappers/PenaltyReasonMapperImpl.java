package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.PenaltyReasonDTO;
import com.example.biblioteca.model.entity.PenaltyReason;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PenaltyReasonMapperImpl implements IPenaltyReasonMapper{
    @Override
    public PenaltyReason dtoToEntity(PenaltyReasonDTO reasonDTO) {
        PenaltyReason reason = new PenaltyReason();
        reason.setId(reasonDTO.getId());
        reason.setReason(reasonDTO.getReason());

        return reason;
    }

    @Override
    public PenaltyReasonDTO entityToDto(PenaltyReason reason) {
        PenaltyReasonDTO reasonDTO = new PenaltyReasonDTO();
        reasonDTO.setId(reason.getId());
        reasonDTO.setReason(reason.getReason());
        return reasonDTO;
    }

    @Override
    public List<PenaltyReasonDTO> entityToDtoList(List<PenaltyReason> reasons) {
        List<PenaltyReasonDTO> reasonsDTO = new ArrayList<PenaltyReasonDTO>();
        PenaltyReasonDTO reasonDTO = new PenaltyReasonDTO();
        for (PenaltyReason pr:reasons){
            reasonDTO = entityToDto(pr);
            reasonsDTO.add(reasonDTO);
        }
        return reasonsDTO;
    }

    @Override
    public List<PenaltyReason> dtoListToEntity(List<PenaltyReasonDTO> reasonsDTO) {
        List<PenaltyReason> reasons = new ArrayList<PenaltyReason>();
        PenaltyReason reason = new PenaltyReason();
        for (PenaltyReasonDTO pr:reasonsDTO){
            reason = dtoToEntity(pr);
            reasons.add(reason);
        }
        return reasons;
    }
}
