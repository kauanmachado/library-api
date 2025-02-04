package com.kauanmachado.libraryapi.controller.mappers;

import com.kauanmachado.libraryapi.controller.dto.UsuarioDTO;
import com.kauanmachado.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}
