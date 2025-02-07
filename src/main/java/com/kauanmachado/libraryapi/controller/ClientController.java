package com.kauanmachado.libraryapi.controller;

import com.kauanmachado.libraryapi.controller.dto.ClientDTO;
import com.kauanmachado.libraryapi.controller.mappers.ClientMapper;
import com.kauanmachado.libraryapi.model.Client;
import com.kauanmachado.libraryapi.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;
    private final ClientMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('GERENTE')")
    public void salvar(@RequestBody @Valid ClientDTO dto){
        var client = mapper.toEntity(dto);
        service.salvar(client);
    }
}
