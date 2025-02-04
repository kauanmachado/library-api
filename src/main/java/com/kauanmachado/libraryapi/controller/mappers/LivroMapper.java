package com.kauanmachado.libraryapi.controller.mappers;

import com.kauanmachado.libraryapi.controller.dto.CadastroLivroDTO;
import com.kauanmachado.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import com.kauanmachado.libraryapi.model.Livro;
import com.kauanmachado.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AutorMapper.class)
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null) )")
    public abstract Livro toEntity(CadastroLivroDTO dto);

    public abstract ResultadoPesquisaLivroDTO toDTO(Livro livro);
}
