package com.kauanmachado.libraryapi.controller.mappers;

import com.kauanmachado.libraryapi.controller.dto.AutorDTO;
import com.kauanmachado.libraryapi.model.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    Autor toEntity(AutorDTO autor);

    AutorDTO toDTO(Autor autor);
}
