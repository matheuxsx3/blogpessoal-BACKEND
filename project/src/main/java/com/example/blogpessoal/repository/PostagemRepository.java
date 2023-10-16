package com.example.blogpessoal.repository;

import com.example.blogpessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostagemRepository extends JpaRepository<Postagem,Long> {
    List<Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);



}
