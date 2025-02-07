package com.kauanmachado.libraryapi.controller.mappers;

import com.kauanmachado.libraryapi.controller.dto.ClientDTO;
import com.kauanmachado.libraryapi.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientDTO dto);
}
