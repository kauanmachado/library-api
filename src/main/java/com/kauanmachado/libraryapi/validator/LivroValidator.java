package com.kauanmachado.libraryapi.validator;

import com.kauanmachado.libraryapi.exceptions.CampoInvalidoException;
import com.kauanmachado.libraryapi.exceptions.RegistroDuplicadoException;
import com.kauanmachado.libraryapi.model.Livro;
import com.kauanmachado.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LivroValidator {

    private final LivroRepository repository;

    private static final int ANO_EXIGENCIA_PRECO = 2020;

    public void validar(Livro livro){
        if(existsLivroComIsbn(livro)){
            throw new RegistroDuplicadoException("ISBN já cadastrado!");
        }

        if(isPrecoObrigatorioNulo(livro)){
            throw new CampoInvalidoException("preco", "Para livros com ano de publicação apartir de 2020 o preço é obrigatório!");
        }
    }
    private boolean existsLivroComIsbn(Livro livro){
        Optional<Livro> livroEncontrado = repository.findByIsbn(livro.getIsbn());

        if(livro.getId() == null){
            return livroEncontrado.isPresent();
        }

        return livroEncontrado
                .map(Livro::getId)
                .stream()
                .anyMatch(id -> !id.equals(livro.getId()));
    }

    private boolean isPrecoObrigatorioNulo(Livro livro){
        return livro.getPreco() == null &&
                livro.getDataPublicacao().getYear() >= ANO_EXIGENCIA_PRECO;
    }
}
