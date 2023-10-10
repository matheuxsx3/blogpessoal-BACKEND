package com.example.blogpessoal.repository;

import com.example.blogpessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    List<Usuario> findAllByNomeContainingIgnoreCase(@Param("nome")String nome);
}
