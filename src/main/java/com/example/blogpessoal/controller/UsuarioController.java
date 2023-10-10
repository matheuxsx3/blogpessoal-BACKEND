package com.example.blogpessoal.controller;

import com.example.blogpessoal.model.Usuario;
import com.example.blogpessoal.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuario() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/nomeUsuario/{nomeUsuario}")
    public ResponseEntity<List<Usuario>> getUsuarioByNomeUsuario(@PathVariable String nomeUsuario) {
        return ResponseEntity.ok(usuarioRepository.findAllByNomeContainingIgnoreCase(nomeUsuario));
    }

    @PostMapping
    public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioRepository.save(usuario));
    }

    @PutMapping
    public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioRepository.findById(usuario.getId())
                .map(resposta->ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuario)))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @DeleteMapping("/deletar/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}