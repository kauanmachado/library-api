package com.kauanmachado.libraryapi.repository;

import com.kauanmachado.libraryapi.model.Autor;
import com.kauanmachado.libraryapi.model.GeneroLivro;
import com.kauanmachado.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("213213");
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("História de Kauan");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2 ));

        Autor autor = new Autor();
        autor.setNome("Cláudio");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1987, 1, 31));

        livro.setAutor(autor);
        repository.save(livro);
    }

    @Test
    void salvarTest(){
       Livro livro = new Livro();
       livro.setIsbn("213213");
       livro.setGenero(GeneroLivro.CIENCIA);
       livro.setTitulo("Misterios da Natureza");
       livro.setPreco(BigDecimal.valueOf(35));
       livro.setDataPublicacao(LocalDate.of(1999, 1, 2 ));

       Autor autor = autorRepository.findById(UUID.fromString("c979b4fe-4ae8-4395-a522-eb14c02d317b")).orElse(null);
       livro.setAutor(autor);

       repository.save(livro); }

    @Test
    void atualizarAutorLivro(){
        var livroParaAtualizar = repository
                .findById(UUID.fromString("6b3e6f17-5f65-436c-badf-dbfb1a405695"))
                .orElse(null);
        Autor maria = autorRepository
                .findById(UUID.fromString("e9f9ab88-9871-4d08-bb81-dba074de860c"))
                .orElse(null);

        livroParaAtualizar.setAutor(maria);

        repository.save(livroParaAtualizar);

    }

    @Test
    void deletar(){
        UUID id = UUID.fromString("ee9778a7-a6d0-4e4e-aa23-75bc6579bcd8");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest(){
        UUID id = UUID.fromString("6b3e6f17-5f65-436c-badf-dbfb1a405695");
        Livro livro = repository.findById(id).orElse(null);

        System.out.println(livro.getTitulo());
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisaPorTituloTest(){
        List<Livro> lista = repository.findByTitulo("História de Kauan");
        lista.forEach(System.out::println);

    }
    @Test
    void pesquisaPorIsbnTest(){
        Optional<Livro> livro = repository.findByIsbn("22768-6713");
        livro.ifPresent(System.out::println);
    }

    @Test
    void pesquisaPorTituloPrecoTest(){
        var preco = BigDecimal.valueOf(76.00);
        List<Livro> lista = repository.findByTituloAndPreco("Incendio na Lua", preco);
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL(){
        var resultado = repository.listarTodosOrdenadoPorTitulo();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParamTest(){
        var resultado = repository.findByGenero(GeneroLivro.BIOGRAFIA, "dataPublicacao");
        resultado.forEach(System.out::println);
    }

    @Test
    void deletePorGeneroTest(){
        repository.deleteByGenero(GeneroLivro.CIENCIA);
    }

    @Test
    void updateDataPublicacaoTest(){
        repository.updateDataPublicacao(LocalDate.of(2001, 5, 3));
    }

}