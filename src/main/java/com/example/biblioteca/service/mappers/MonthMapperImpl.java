package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.MonthDTO;
import com.example.biblioteca.model.entity.Month;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MonthMapperImpl implements IMonthMapper{

        @Override
        public Month dtoToEntity(MonthDTO monthDTO) {
                Month month = new Month();
                month.setId(monthDTO.getId());
                month.setName(monthDTO.getName());
                return month;
        }

        @Override
        public MonthDTO entityToDto(Month month) {
                MonthDTO monthDTO = new MonthDTO();
                monthDTO.setId(month.getId());
                monthDTO.setName(month.getName());
                return monthDTO;
        }

        @Override
        public List<MonthDTO> entityToDtoList(List<Month> months) {
                return null;
        }
}
