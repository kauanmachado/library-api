package com.kauanmachado.libraryapi.repository;

import com.kauanmachado.libraryapi.model.Autor;
import com.kauanmachado.libraryapi.model.GeneroLivro;
import com.kauanmachado.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1967, 1, 31));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("64416e61-f219-4238-b60f-01a8a94f534e");
        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1968, 2, 12));

            repository.save(autorEncontrado);
        }

    }

    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        System.out.println("Lista de autores: " + lista);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("64416e61-f219-4238-b60f-01a8a94f534e");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("0d8f5ede-77b3-4e78-8bb8-2b63edb57a4a");
        var ana = repository.findById(id).get();
        repository.delete(ana);
    }

    @Test
    public void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1977, 4, 28));

        Livro livro = new Livro();
        livro.setIsbn("22768-6713");
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("História de Dalai Lama");
        livro.setPreco(BigDecimal.valueOf(340));
        livro.setDataPublicacao(LocalDate.of(1996, 1, 21 ));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("29808-6713");
        livro2.setGenero(GeneroLivro.FICCAO);
        livro2.setTitulo("Incendio na Lua");
        livro2.setPreco(BigDecimal.valueOf(76));
        livro2.setDataPublicacao(LocalDate.of(2023, 8, 10 ));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);


        repository.save(autor);
        livroRepository.saveAll(autor.getLivros());

    }

    @Test
    void listarLivrosAutorTest(){
        var id = UUID.fromString("6e7f7137-865b-4e1b-9062-12d70e70388e");
        var autor = repository.findById(id).get();

        List<Livro> livrosLista =livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }
}
