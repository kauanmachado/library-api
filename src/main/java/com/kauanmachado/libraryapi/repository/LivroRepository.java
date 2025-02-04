package com.kauanmachado.libraryapi.repository;

import com.kauanmachado.libraryapi.model.Autor;
import com.kauanmachado.libraryapi.model.GeneroLivro;
import com.kauanmachado.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID>, JpaSpecificationExecutor {


    // QUERY METHOD

    boolean existsByAutor(Autor autor);

    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTitulo(String titulo);

    Optional<Livro> findByIsbn(String isbn);

    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    @Query(" select l from Livro as l order by l.titulo ")
    List<Livro> listarTodosOrdenadoPorTitulo();

    @Query(" select l from Livro l where l.genero = :genero order by :ordenacao")
    List<Livro> findByGenero(@Param("genero") GeneroLivro generoLivro, @Param("ordenacao") String nomePropriedade);

    @Modifying
    @Transactional
    @Query(" delete from Livro where genero = ?1")
    void deleteByGenero(GeneroLivro genero);

    @Modifying
    @Transactional
    @Query(" update Livro set dataPublicacao = ?1")
    void updateDataPublicacao(LocalDate novaData);

}
