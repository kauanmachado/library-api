package com.kauanmachado.libraryapi.service;

import com.kauanmachado.libraryapi.model.Autor;
import com.kauanmachado.libraryapi.model.GeneroLivro;
import com.kauanmachado.libraryapi.model.Livro;
import com.kauanmachado.libraryapi.repository.AutorRepository;
import com.kauanmachado.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = livroRepository
            .findById(UUID.fromString("6b3e6f17-5f65-436c-badf-dbfb1a405695"))
            .orElse(null);
        livro.setDataPublicacao(LocalDate.of(2003, 10, 23));

        livroRepository.save(livro);
    }

    @Transactional
    public void executar(){
        Autor autor = new Autor();
        autor.setNome("Francisco");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1987, 1, 31));

        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setIsbn("213213");
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Biografia da Francisca");
        livro.setPreco(BigDecimal.valueOf(120));
        livro.setDataPublicacao(LocalDate.of(1990, 1, 2 ));
        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("Francisco")){
            throw new RuntimeException("Rollback!!");
        }
    }
}
