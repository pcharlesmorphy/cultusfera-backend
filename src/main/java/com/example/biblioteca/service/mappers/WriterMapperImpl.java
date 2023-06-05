package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.WriterDTO;
import com.example.biblioteca.model.entity.Writer;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class WriterMapperImpl implements IWriterMapper {
    
    @Override
    public Writer dtoToEntity(WriterDTO writerDTO) {
        Writer writer = new Writer();
        writer.setId(writerDTO.getId());
        writer.setName(writerDTO.getName());
        writer.setSurnames(writerDTO.getSurnames());
        return writer;

    }

    @Override
    public WriterDTO entityToDto(Writer writer) {

        WriterDTO escritordto = new WriterDTO();
        escritordto.setId(writer.getId());
        escritordto.setName(writer.getName());
        escritordto.setSurnames(writer.getSurnames());
        return escritordto;
    }

    @Override
    public List<WriterDTO> entityToDtoList(List<Writer> writers) {

        List<WriterDTO> writersdto = new ArrayList<WriterDTO>();
        WriterDTO writerdto = new WriterDTO();

        for (Writer w:writers){
            writerdto = entityToDto(w);
            writersdto.add(writerdto);
        }
        return writersdto;
    }

    @Override
    public List<Writer> dtoListToEntity(List<WriterDTO> writersDTO) {

        List<Writer> writers = new ArrayList<Writer>();
        Writer writer = new Writer();
        for (WriterDTO w:writersDTO){
            writer = dtoToEntity(w);
            writers.add(writer);
        }
        return writers;
    }
}
